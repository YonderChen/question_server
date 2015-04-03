package com.foal.question.jersey.resource.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.math.NumberUtils;

public class MultipartFormParam {
	
	private Map<String, String> fieldParam = new HashMap<String, String>();
	
	private List<FileItem> fileItemList = new ArrayList<FileItem>();

	public List<FileItem> getFileItemList() {
		return fileItemList;
	}

	public void setFileItemList(List<FileItem> fileItemList) {
		this.fileItemList = fileItemList;
	}

	public String getField(String fieldName, String defaultValue) {
		String fieldValue = fieldParam.get(fieldName);
		if (fieldValue == null) {
			return defaultValue;
		}
		return fieldValue;
	}

	public int getIntField(String fieldName, int defaultValue) {
		String fieldValue = fieldParam.get(fieldName);
		if (fieldValue == null) {
			return defaultValue;
		}
		return NumberUtils.toInt(fieldValue, defaultValue);
	}

	public void setField(String fieldName, String fieldValue) {
		fieldParam.put(fieldName, fieldValue);
	}
	
}
