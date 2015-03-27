package com.foal.question.util;

import java.util.UUID;

/**
 * @author cyd
 * @date 2015-3-27
 */
public class FileTools {

	public static String getFileSuffix(String fileName) {
		int index = fileName.indexOf(".");
		if (index < 0) {
			return null;
		}
		return fileName.substring(index, fileName.length());
	}
	
	public static boolean isImageSuffix(String suffix) {
		if (StringTools.equalsIgnoreCase(".jpg", suffix) 
				|| StringTools.equalsIgnoreCase(".jpeg", suffix) 
				|| StringTools.equalsIgnoreCase(".png", suffix) 
				|| StringTools.equalsIgnoreCase(".bmp", suffix)
				|| StringTools.equalsIgnoreCase(".gif", suffix)) {
			return true;
		}
		return false;
	}

	public static String getUploadFileName(String suffix) {
		return UUID.randomUUID() + suffix;
	}
}
