package com.foal.question.jersey.resource.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

public class MultipartFormParam {
	
	private Map<String, String> fieldParam = new HashMap<String, String>();
	
	private List<FileItem> fileItemList = new ArrayList<FileItem>();

	public Map<String, String> getFieldParam() {
		return fieldParam;
	}

	public void setFieldParam(Map<String, String> fieldParam) {
		this.fieldParam = fieldParam;
	}

	public List<FileItem> getFileItemList() {
		return fileItemList;
	}

	public void setFileItemList(List<FileItem> fileItemList) {
		this.fileItemList = fileItemList;
	}

}
