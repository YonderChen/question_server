package com.wangyin.wepaypc.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wangyin.wepaypc.model.BasePayOrderInfo;
import com.wangyin.wepaypc.util.RSACoder;
import com.wangyin.wepaypc.util.SHAUtil;
import com.wangyin.wepaypc.util.Sha256Util;
import com.wangyin.wepaypc.util.SignUtil;

/**
 * 该类提供给商户，以便商户便捷接入
 * 
 * @author wyzhaoqian
 * 
 */
public class SignHelper {
	private static Logger logger = Logger.getLogger(SignHelper.class);

	public static String getSign(BasePayOrderInfo clientPayOrderInfo, String key) throws Exception {
		List<String> unSignedKeyList = new ArrayList<String>();
		unSignedKeyList.add("merchantSign");
		unSignedKeyList.add("version");
		unSignedKeyList.add("successCallbackUrl");
		unSignedKeyList.add("forPayLayerUrl");
		
		String strSourceData = SignUtil.signString(clientPayOrderInfo, unSignedKeyList);
		logger.info("source:"+strSourceData);
		
		 //摘要
		byte[] sha256SourceSignByte = Sha256Util.encrypt(strSourceData.getBytes("UTF-8"));
        //私钥对摘要进行加密
        byte[] newsks = RSACoder.encryptByPrivateKey(sha256SourceSignByte, key);
        String result = RSACoder.encryptBASE64(newsks);
		

		logger.info("sign:"+result);
		return result;

	}

	public static String getUrl(BasePayOrderInfo clientPayOrderInfo, String oriUrl, String key) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(oriUrl);
		sb.append("?version=" + urlEncode(clientPayOrderInfo.getVersion()));
		sb.append("&token=" + urlEncode(clientPayOrderInfo.getToken()));
		sb.append("&merchantNum=" + urlEncode(clientPayOrderInfo.getMerchantNum()));
		sb.append("&merchantRemark=" + urlEncode(clientPayOrderInfo.getMerchantRemark()));
		sb.append("&tradeNum=" + urlEncode(clientPayOrderInfo.getTradeNum()));
		sb.append("&tradeName=" + urlEncode(clientPayOrderInfo.getTradeName()));
		sb.append("&tradeDescription=" + urlEncode(clientPayOrderInfo.getTradeDescription()));
		sb.append("&tradeTime=" + urlEncode(clientPayOrderInfo.getTradeTime()));
		sb.append("&tradeAmount=" + urlEncode(clientPayOrderInfo.getTradeAmount()));
		sb.append("&currency=" + urlEncode(clientPayOrderInfo.getCurrency()));
		sb.append("&notifyUrl=" + urlEncode(clientPayOrderInfo.getNotifyUrl()));
		sb.append("&successCallbackUrl=" + urlEncode(clientPayOrderInfo.getSuccessCallbackUrl()));
		sb.append("&forPayLayerUrl=" + urlEncode(clientPayOrderInfo.getForPayLayerUrl()));
		sb.append("&ip=" + urlEncode(clientPayOrderInfo.getIp()));
		sb.append("&merchantSign=" + urlEncode(getSign(clientPayOrderInfo, key)));
		logger.info("url:"+sb.toString());
		return sb.toString();
	}

	public static String urlEncode(String input) {
		try {
			if (input == null || input.length() == 0) {
				return "";
			}
			return URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}
	
	public static void main(String[] args) throws Exception{
		String key ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXf6twUqul1TATO+5nA66p2wjnRd+g96IXpfV6Sf8WXxwizGj+L19LQYRBXpZHmRh82prJ48d0FcHboCiN8pKutnuZrrKYhvORysOc5bVli0hcCn1TfYDoUWJ1UhjUQloqZKWjUz6LV9QY6bIZ1W4+Hmw6HK1bfFwUq0WzIGkJNAgMBAAECgYBlIFQeev9tP+M86TnMjBB9f/sO2wGpCIM5slIbO6n/3By3IZ7+pmsitOrDg3h0X22t/V1C7yzMkDGwa+T3Rl7ogwc4UNVj0ZQorOTx3OEPx3nP1yT3zmJ9djKaHKAmee4XmhQHdqqIuMT2XQaqatBzcsnP+Jnw/WVOsIJIqMeFAQJBAP9yq4hE+UfM/YSXZ5JR33k9RolUUq8S/elmeJIDo/3N2qDmzLjOr9iEZHxioc8JOxubtZ0BxA+NdfKz4v0BSpkCQQC2RIrAPRj9vOk6GfT9W1hbJ4GdnzTb+4vp3RDQQ3x9JGXzWFlg8xJT1rNgM8R95Gkxn3KGnYHJQTLlCsIy2FnVAkAWXolM3pVhxz6wHL4SHx9Ns6L4payz7hrUFIgcaTs0H5G0o2FsEZVuhXFzPwPiaHGHomQOAriTkBSzEzOeaj2JAkEAtYUFefZfETQ2QbrgFgIGuKFboJKRnhOif8G9oOvU6vx43CS8vqTVN9G2yrRDl+0GJnlZIV9zhe78tMZGKUT2EQJAHQawBKGlXlMe49Fo24yOy5DvKeohobjYqzJAtbqaAH7iIQTpOZx91zUcL/yG4dWS6r+wGO7Z1RKpupOJLKG3lA==";
		String strSourceData="currency=CNY&ip=10.45.251.153&merchantNum=22294531&merchantRemark=生产环境-测试商户号&notifyUrl=http://localhost/mclient-php/wangyin/wepay/join/demo/api/WebAsynNotificationCtrl.php&token=&tradeAmount=1&tradeDescription=交易描述&tradeName=商品名称&tradeNum=101230123&tradeTime=2014-12-25 14:08:03";
		
		 //摘要
        String sha256SourceSignString = SHAUtil.Encrypt(strSourceData,null);
        //私钥对摘要进行加密
        byte[] newsks = RSACoder.encryptByPrivateKey(sha256SourceSignString.getBytes("UTF-8"), key);
        String result = RSACoder.encryptBASE64(newsks);
		
        System.out.println(("sign:"+result));
//		BasePayOrderInfo basePayOrderInfo =  new BasePayOrderInfo();
//		basePayOrderInfo.setCurrency("CNY");
//		basePayOrderInfo.setIp("10.110.210.47");
//		basePayOrderInfo.setJdPin("");
//		basePayOrderInfo.setMerchantNum("22317590");
//		basePayOrderInfo.setMerchantRemark("1");
//		basePayOrderInfo.setNotifyUrl("http://gw.jd.com/payment/notify_chinabankVir.action");
//		basePayOrderInfo.setOuterMerchantOrderNo("40970401395");
//		basePayOrderInfo.setToken("");
//		basePayOrderInfo.setTradeAmount("1");
//		basePayOrderInfo.setTradeDescription("0");
//		basePayOrderInfo.setTradeName("商品A");
//		basePayOrderInfo.setTradeNum("141029264001870658425");
//		basePayOrderInfo.setTradeTime("2014-10-29 17:25:20");
//		/**
//		 * 
//		 * 
//		 * 
//		 * 
//		 * */
//		
//		
//		
//		String sign = SignHelper.getSign(basePayOrderInfo, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI+OdmbDy8RtY3IyTPg9r5sQhe1uKtssLsURxyrpa0GMIimSCrFDJ6GAnn/JxU4mLzwVPeGVI/sY9PN1/QONoCLBQ9E1FfZvTvppKPKP7BWt1cQjrPh7hIFurA9q/AJ3pJsW3CMomWaCVwejR4Nz8jD3jo68InF3yCaJ9OU3FLdTAgMBAAECgYBp4ElE650KZx8UJzMLVvt/4wTTow/qi8CGyeDZrkPTmRXNEQ/fwsak32aGmvpw88qchpIYINXjqHloYhnUGA0E07mIRRbILSkLQlCajgVtWe9oh7nASFGpBdW/jKFrBpFldozGGhSRtehHIzni1V10ooNEnZpnEkprCA7WijNmuQJBAO1+r8NkGu2VXuV5Fjb4vG7mMpFciOZz+muUQK/DN4O9yBbBeXONUkFhXSz/aJBPwpZKlxXCGsnFsuYEeVu9No8CQQCavfxh05ll0/BVd110KHrVYAo0y3ME7fIR/NNn8SEipSo7XS0zcQS3i58phsw/VDmYs/7ZLdLUB5UKxNLgU3T9AkEAytu6cBBSu/spmqLKKdxev+9a5DUBLq+ECF4Svs7l3V6+yUkrX1soFnZ+6w+ilhm64TsHQGuTDCQVQkoyCv1c2wJAfka1s3tCvhcjFAuxhr4V5xRVn9m6xfYLSeSA/FyJBsWz3ffekBEVoVbeDrxC5xcrXVLdkItVddOuK7iMwaU5XQJAWpT+huR3L9JPR6I7b481sat0YuCIU6rLekhAN+jezKKSyGRh869MDasqCE8Eqp3HMsSVDy7rR/wJVMWtgroyMw==");
//		
//		System.out.println("sign:"+sign);
				
	}
	
}
