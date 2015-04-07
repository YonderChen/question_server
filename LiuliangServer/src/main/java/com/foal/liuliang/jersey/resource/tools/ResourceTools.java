package com.foal.liuliang.jersey.resource.tools;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.foal.liuliang.config.Constant;
import com.foal.liuliang.util.StringTools;

@SuppressWarnings("unchecked")
public class ResourceTools {

	public static String getFileSuffix(String fileName) {
		int index = fileName.indexOf(".");
		if (index < 0) {
			return null;
		}
		return fileName.substring(index, fileName.length());
	}
	
	public static String[] getImageSuffixs() {
		return new String[]{".jpg", ".jpeg", ".png", ".bmp", ".gif"};
	}
	
	public static String[] getVoiceSuffixs() {
		return new String[]{".wav", ".mp3", ".aif", ".rm", ".wmv"};
	}

	public static boolean checkSuffix(String suffix, String[] suffixs) {
		for (String suf : suffixs) {
			if (StringTools.equalsIgnoreCase(suffix, suf)) {
				return true;
			}
		}
		return false;
	}
	
	private static String getUploadFileName(String suffix) {
		return UUID.randomUUID() + suffix;
	}
	/**
	 * 上传单个文件
	 * @param request
	 * @param originalFileName	原始文件名
	 * @param basePath	基础相对目录
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(List<FileItem> fileItems, String[] fileSuffixs, String basePath) throws Exception {
		for (FileItem fileItem : fileItems) {
			String fileName = fileItem.getName();
			if (StringTools.isNotBlank(fileName) && fileItem.getSize() != 0) {
				String fileSuffix = getFileSuffix(fileName);
				if (checkSuffix(fileSuffix, fileSuffixs)) {
					String newFileName = getUploadFileName(fileSuffix);
					String fileDirPath = Constant.TOMCAT_SERVICE_ADDRESS + basePath;
					File fileDir = new File(fileDirPath);
					if (!fileDir.isDirectory()) {
						fileDir.mkdirs();
					}
					File newFile = new File(fileDir, newFileName);
					fileItem.write(newFile);
					return basePath + newFileName;
				}
			}
		}
		return "";
	}
	
	public static MultipartFormParam getMultipartFormParam(HttpServletRequest request) throws FileUploadException {
		MultipartFormParam param = new MultipartFormParam();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File tmpDir = new File(Constant.TOMCAT_SERVICE_ADDRESS + Constant.UPLOAD_TEMP_PATH);
		if (!tmpDir.isDirectory()) {
			tmpDir.mkdirs();
		}
		factory.setRepository(tmpDir);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(Constant.FILE_MAX_SIZE);
		List items = new ArrayList();
		items = upload.parseRequest(request);

		Iterator<?> it = items.iterator();
		while (it.hasNext()) {
			FileItem fieldItem = (FileItem) it.next();
			if (fieldItem.isFormField()) {
				try {
					param.setField(fieldItem.getFieldName(), fieldItem.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					param.setField(fieldItem.getFieldName(), fieldItem.getString());
				}
			} else {
				param.getFileItemList().add(fieldItem);
			}
		}
		return param;
	}
	
}
