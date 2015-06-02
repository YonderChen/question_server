/**
 * 
 */
package com.foal.question.jersey.resource.tools;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.math.NumberUtils;

import com.foal.question.config.Constant;
import com.foal.question.config.QuestionException;
import com.foal.question.jersey.resource.tools.APIConstants.DataKeys;
import com.foal.question.util.GsonTools;
import com.foal.question.util.MD5Tools;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * 处理类传入参数
 * @author jackyli515
 *
 */
public class Param {
	private JsonObject paramJo;
	private short command;
	private HttpServletRequest request;
	private String version;
	
	private List<FileItem> fileItemList;

	public static Param getParam(HttpServletRequest request) throws Exception {
		MultipartFormParam param = ResourceTools.getMultipartFormParam(request);
		String root = param.getField(DataKeys.Root, "");
		String sign = param.getField(DataKeys.Sign, "");
		String version = param.getField(DataKeys.Version, "");
		short command = NumberUtils.toShort(param.getField(DataKeys.Command, ""), (short)0);
		String newSign = root + "&" + Constant.KEY_OF_SIGN;
		if (!sign.equals(MD5Tools.hashToMD5(newSign))) {
			throw new QuestionException(QuestionException.UnKnowError, "签名错误");
		}
		List<FileItem> fileItemList = param.getFileItemList();
		return new Param(command, request, GsonTools.parseJsonObject(root), version, fileItemList);
	}
	private Param(short command,HttpServletRequest request,JsonObject paramJo, String version, List<FileItem> fileItemList){
		this.command = command;
		this.request = request;
		this.paramJo = paramJo;
		this.version = version;
		this.fileItemList = fileItemList;
	}
	public String toJson(){
		return paramJo.toString();
	}
	public String get(String key){
		return get(key,"");
	}
	public String get(String key,String defaultVal){
		JsonElement obj = this.paramJo.get(key);		
		if(obj == null || JsonNull.INSTANCE == obj){
			return defaultVal;
		}
		return obj.getAsString();
	}
	
	public int getInt(String key){
		return getInt(key,0);
	}
	public int getInt(String key,int defaultVal){
		JsonElement obj = this.paramJo.get(key);
		if(obj == null || JsonNull.INSTANCE == obj){
			return defaultVal;
		}
		return obj.getAsInt();
	}
	public long getLong(String key){
		return getLong(key,0);
	}
	public long getLong(String key,long defaultVal){
		JsonElement obj = this.paramJo.get(key);
		if(obj == null || JsonNull.INSTANCE == obj){
			return defaultVal;
		}
		return obj.getAsLong();
	}
	public float getFloat(String key) {
		return getFloat(key, 0);
	}
	public float getFloat(String key,long defaultVal){
		JsonElement obj = this.paramJo.get(key);
		if(obj == null || JsonNull.INSTANCE == obj){
			return defaultVal;
		}
		return obj.getAsFloat();
	}
	
	public JsonArray getJsonArray(String key){
		if(paramJo.get(key) == null){
			return new JsonArray();
		}
		return paramJo.get(key).getAsJsonArray();
	}
	public short getCommand() {
		return this.command;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public String getVersion() {
		return version;
	}
	public List<FileItem> getFileItemList() {
		return fileItemList;
	}
	
	@Override
	public String toString() {
		return paramJo.toString();
	}
}
