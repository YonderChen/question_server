package com.wangyin.wepaypc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.misc.BASE64Decoder;

import com.wangyin.wepaypc.model.BaseResponseDto;
import com.wangyin.wepaypc.model.QueryResultTradeEntity;
import com.wangyin.wepaypc.model.TradeQueryEntity;
import com.wangyin.wepaypc.model.TradeQueryReqDto;
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
public class QueryOrderAction{
	
	@RequestMapping(value = "/queryIndex.htm")
    public String queryIndex(HttpServletRequest httpServletRequest) {
		String merchantNum=PropertyUtils.getProperty("wepay.merchant.num");
        httpServletRequest.setAttribute("merchantNum", merchantNum);
        return "queryIndex";
    }
	
	@RequestMapping(value = "/queryOrder.htm", method = RequestMethod.POST)
    public String paySign(TradeQueryReqDto tradeQueryReqDto, HttpServletRequest httpServletRequest) {

        String decrypData="";
        String tradeJsonData = "{\"tradeNum\": \"" + tradeQueryReqDto.getTradeNum() + "\"}";
        try {
            //1.对交易信息进行3DES加密
        	String deskey=PropertyUtils.getProperty("wepay.merchant.desKey");
            String threeDesData = TDESUtil.encrypt2HexStr(RSACoder.decryptBASE64(deskey), tradeJsonData);

            //2.对3DES加密的数据进行签名
            String priKey=PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
            String sha256Data = SHAUtil.Encrypt(threeDesData, null);
            byte[] rsaResult = RSACoder.encryptByPrivateKey(sha256Data.getBytes(), priKey);
            String merchantSign = RSACoder.encryptBASE64(rsaResult);

            //3.构造最终交易查询请求json
            TradeQueryEntity queryTradeDTO = new TradeQueryEntity();
            queryTradeDTO.setVersion(tradeQueryReqDto.getVersion());
            queryTradeDTO.setMerchantNum(tradeQueryReqDto.getMerchantNum());
            queryTradeDTO.setMerchantSign(FormatUtil.stringBlank(merchantSign));
            queryTradeDTO.setData(threeDesData);

            String json = JsonUtil.write2JsonStr(queryTradeDTO);

            //4.发送请求
            String queryUrl=PropertyUtils.getProperty("wepay.server.query.url");
            String resultJsonData = HttpsClientUtil.sendRequest(queryUrl, json);

            //5.验签返回数据
            @SuppressWarnings("unchecked")
			BaseResponseDto<Map<String, Object>> result = (BaseResponseDto<Map<String, Object>>) JsonUtil.json2Object(resultJsonData, BaseResponseDto.class);

            //查询状态 成功
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
                        decrypData = TDESUtil.decrypt4HexStr(RSACoder.decryptBASE64(deskey), data);

                        //注意 结果为List集合
                        List<Map<String,Object>> resultList=JsonUtil.jsonArray2List(decrypData);

                        List<QueryResultTradeEntity> viewList=new ArrayList<QueryResultTradeEntity>();
                        for(Map<String ,Object> m:resultList ){
                            QueryResultTradeEntity qrte=new QueryResultTradeEntity();
                            qrte.setTradeCurrency(m.get("tradeCurrency")+"");
                            qrte.setTradeDate(m.get("tradeDate") + "");
                            qrte.setTradeTime(m.get("tradeTime") + "");
                            qrte.setTradeAmount(Integer.parseInt(m.get("tradeAmount") + ""));
                            qrte.setTradeNote(m.get("tradeNote") + "");
                            qrte.setTradeNum(m.get("tradeNum") + "");
                            qrte.setTradeStatus(m.get("tradeStatus")+"");
                            viewList.add(qrte);
                        }
                        httpServletRequest.setAttribute("viewList", viewList);
                        //错误消息
                        if(resultList.size()<1){
                            httpServletRequest.setAttribute("errorMsg", decrypData);
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
            //执行查询 失败
            else{
                httpServletRequest.setAttribute("errorMsg", result.getResultMsg());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "queryResult";
    }
}


