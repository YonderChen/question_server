package com.foal.liuliang.util;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foal.liuliang.config.Constant;
import com.google.gson.JsonObject;

public class CrazyClickTools {
	
	public static class Method {
		public static final String tbpc_add = "tbpc/add";
		public static final String tbpc_modify = "tbpc/modify";
		public static final String tbmobi_add = "tbmobi/add";
		public static final String tbmobi_modify = "tbmobi/modify";
		public static final String jdpc_add = "jdpc/add";
		public static final String jdpc_modify = "jdpc/modify";
		
		public static final String statistics_getclicks = "statistics/getclicks";
	}
	
	private static String getSign(String method, Map<String, String> params, String appsecret) {
        List<String> scortKeys = getSortParamKey(params);
        String signString = method.trim();
        for (int i = 0; i < scortKeys.size(); i++) {
        	signString += params.get(scortKeys.get(i));
		}
        signString += MD5Tools.hashToMD5(appsecret);
        String sign = MD5Tools.hashToMD5(signString);
        return sign.toLowerCase();
    }

    private static List<String> getSortParamKey(Map<String, String> params) {
		List<String> keys = new ArrayList<String>();
		keys.addAll(params.keySet());
		Collections.sort(keys);
		return keys;
    }
    
    public static JsonObject request(String method, Map<String, String> params) {
    	params.put("appkey", Constant.CrazyClickAppkey);
    	params.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
    	String sign = getSign(method, params, Constant.CrazyClickAppsecret);
    	params.put("sign", sign);
    	String url = Constant.CrazyClickBaseUrl + method;
    	JsonObject jo = new JsonObject();
    	String response = "";
    	try {
			response = HttpTools.sendGet(url, params);
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		jo = GsonTools.parseJsonObject(response);
		return jo;
    }
    
	public static void main(String[] args) {

		Constant.CrazyClickAppkey = "test"; 
		Constant.CrazyClickAppsecret = "a9d11a189099ac9b483ab982e849e939"; 
		Constant.CrazyClickBaseUrl = "http://api.sandbox.aymoo.com/api/";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("kwd", "T恤");
		params.put("nid", "43038352468");
		params.put("shop_type", "b");
		params.put("times", "20");
		params.put("sleep_time", "50");
		params.put("click_start", "0");
		params.put("click_end", "23");
		params.put("begin_time", "2015-05-31");
		params.put("end_time", "2015-06-30");
//		params.put("path1", "0");
//		params.put("path2", "80");
//		params.put("path3", "20");
		JsonObject res = CrazyClickTools.request("jdpc/add", params);
		System.out.println(res.toString());
	}
}
