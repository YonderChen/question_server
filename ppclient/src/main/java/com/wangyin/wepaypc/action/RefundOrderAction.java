package com.wangyin.wepaypc.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.misc.BASE64Decoder;

import com.wangyin.wepaypc.model.BaseResponseDto;
import com.wangyin.wepaypc.model.RefundResultTradeEntity;
import com.wangyin.wepaypc.model.TradeRefundEntity;
import com.wangyin.wepaypc.model.TradeRefundReqDto;
import com.wangyin.wepaypc.util.ByteUtil;
import com.wangyin.wepaypc.util.FormatUtil;
import com.wangyin.wepaypc.util.HttpsClientUtil;
import com.wangyin.wepaypc.util.JsonUtil;
import com.wangyin.wepaypc.util.PropertyUtils;
import com.wangyin.wepaypc.util.RSACoder;
import com.wangyin.wepaypc.util.SHAUtil;
import com.wangyin.wepaypc.util.Sha256Util;
import com.wangyin.wepaypc.util.TDESUtil;

/**
 * 交易查询
 */
@Controller
public class RefundOrderAction{
	
	@RequestMapping(value = "/refundIndex.htm")
    public String queryIndex(HttpServletRequest httpServletRequest) {
		String merchantNum=PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchantNum", merchantNum);
        httpServletRequest.setAttribute("nowTime", new Date());
        return "refundIndex";
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/refundOrder.htm", method = RequestMethod.POST)
    public String paySign(TradeRefundReqDto tradeRefundReqDto, HttpServletRequest httpServletRequest) {

        String tradeJsonData = "{\"tradeNum\": \""+tradeRefundReqDto.getTradeNum() +"\",\"oTradeNum\": \""+tradeRefundReqDto.getoTradeNum()+"\",\"tradeAmount\":\""+tradeRefundReqDto.getTradeAmount()+"\",\"tradeCurrency\": \""+tradeRefundReqDto.getTradeCurrency()+"\",\"tradeDate\": \""+tradeRefundReqDto.getTradeDate()+"\",\"tradeTime\": \""+tradeRefundReqDto.getTradeTime()+"\",\"tradeNotice\": \""+tradeRefundReqDto.getTradeNotice()+"\",\"tradeNote\": \""+tradeRefundReqDto.getTradeNote()+"\"}";
        try {
            //1.对退款信息进行3DES加密
        	String deskey=PropertyUtils.getProperty("wepay.merchant.desKey");
            String threeDesData = TDESUtil.encrypt2HexStr(RSACoder.decryptBASE64(deskey), tradeJsonData);

            //2.对3DES加密的数据进行签名
            String sha256Data = SHAUtil.Encrypt(threeDesData, null);
            String priKey=PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
            byte[] rsaResult = RSACoder.encryptByPrivateKey(sha256Data.getBytes(), priKey);
            String merchantSign = RSACoder.encryptBASE64(rsaResult);

            //3.构造最终退款请求json
            TradeRefundEntity tradeRefundEntity = new TradeRefundEntity();
            tradeRefundEntity.setVersion(tradeRefundReqDto.getVersion());
            tradeRefundEntity.setMerchantNum(tradeRefundReqDto.getMerchantNum());
            tradeRefundEntity.setMerchantSign(FormatUtil.stringBlank(merchantSign));
            tradeRefundEntity.setData(threeDesData);

            String json = JsonUtil.write2JsonStr(tradeRefundEntity);

            //4.发送请求
            String refundUrl=PropertyUtils.getProperty("wepay.server.refund.url");
            String resultJsonData = HttpsClientUtil.sendRequest(refundUrl, json);

            //5.验签返回数据
            BaseResponseDto<Map<String, Object>> result = (BaseResponseDto<Map<String, Object>>) JsonUtil.json2Object(resultJsonData, BaseResponseDto.class);

            //执行状态 成功
            if (result.getResultCode() == 0) {
                Map<String, Object> mapResult =  result.getResultData();
                //有返回数据
                if (null != mapResult) {
                    String data = mapResult.get("data").toString();
                    String sign = mapResult.get("sign").toString();
                    //1.解密签名内容
                    byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
                    String wyPubKey=PropertyUtils.getProperty("wepay.wangyin.rsaPublicKey");
                    byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
                    String decryptStr = ByteUtil.byte2HexString(decryptArr);

                    //2.对data进行sha256摘要加密
                    String sha256SourceSignString = ByteUtil.byte2HexLowerCase(Sha256Util.encrypt(data.getBytes("UTF-8")));

                    //3.比对结果
                    if (decryptStr.equals(sha256SourceSignString)) {
                        /**
                         * 验签通过
                         */
                        //解密data
                        String decrypData = TDESUtil.decrypt4HexStr(RSACoder.decryptBASE64(deskey), data);

                        //退款结果实体
                        RefundResultTradeEntity resultData=(RefundResultTradeEntity)JsonUtil.json2Object(decrypData,RefundResultTradeEntity.class);

                        //错误消息
                        if(null==resultData){
                            httpServletRequest.setAttribute("errorMsg", decrypData);
                        }
                        else{
                            httpServletRequest.setAttribute("resultData", resultData);
                        }
                    } else {
                        /**
                         * 验签失败  不受信任的响应数据
                         * 终止
                         */
                        return "";

                    }
                }
            }
            //执行退款 失败
            else{
                httpServletRequest.setAttribute("errorMsg", result.getResultMsg());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "refundResult";
    }
}


