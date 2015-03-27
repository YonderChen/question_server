package com.foal.question.jersey.resource.tools;

import com.foal.question.jersey.resource.tools.APIConstants.DataKeys;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author jackyli515
 * 
 */
public class ResultMap {
	private JsonObject retJo;

	public static ResultMap getResultMap() {
		return new ResultMap();
	}

	private ResultMap() {
		this.retJo = new JsonObject();

	}

	public void setResult(int ret) {
		this.retJo.addProperty(DataKeys.Result, ret);
	}

	public void setResult(int ret, String msg) {
		this.retJo.addProperty(DataKeys.Result, ret);
		this.retJo.addProperty(DataKeys.Message, msg);
	}

	public void add(String key, JsonObject value) {
		this.retJo.add(key, value);
	}
	
	public void add(String key, JsonArray value) {
		this.retJo.add(key, value);
	}

	public void add(String key, Boolean value) {
		retJo.addProperty(key, value);
	}

	public void add(String key, Character value) {
		retJo.addProperty(key, value);
	}

	public void add(String key, Number value) {
		retJo.addProperty(key, value);
	}

	public void add(String key, String value) {
		retJo.addProperty(key, value);
	}

	public String toJson() {
		return retJo.toString();
	}
}
