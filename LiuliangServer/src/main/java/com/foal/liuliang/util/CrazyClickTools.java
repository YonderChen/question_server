package com.foal.liuliang.util;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

public class CrazyClickTools {
	
	private static final String appkey = "test"; 
	private static final String appsecret = "a9d11a189099ac9b483ab982e849e939"; 
	private static final String baseUrl = "http://api.sandbox.aymoo.com/api/";
	
	public static String getSign(String method, Map<String, String> params, String appsecret) {
        List<String> scortKeys = getSortParamKey(params);
        String signString = method.trim();
        for (int i = 0; i < scortKeys.size(); i++) {
        	signString += scortKeys.get(i);
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
    
    public static Map<String, String> getBaseParam() {
    	Map<String, String> baseParam = new HashMap<String, String>();
    	baseParam.put("appkey", appkey);
    	return baseParam;
    }
    
    public static JsonObject request(String method, Map<String, String> params) {
    	String sign = getSign(method, params, appsecret);
    	params.put("sign", sign);
    	params.put("timestamp", String.valueOf(System.currentTimeMillis()));
    	String url = baseUrl + method;
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
}
