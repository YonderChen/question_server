package com.foal.liuliang.config;


public class Constant {
	public static final String PRO_CTX_KEY = "ctx";
	public static String PRO_CTX_VALUE;

	public static final long FILE_MAX_SIZE = 1024 * 1024 * 10;

	public static final String CONTEXT_WEB_URL_KEY = "webUrl";

	public static String TOMCAT_SERVICE_ADDRESS;
	
	public static String CONTEXT_WEB_URL;

	// 以下从数据库取
	public static String INIT_PASSWORD;
	
	public static final String INIT_PASSWORD_KEY = "initPassword";
	
	public static final String UPLOAD_IMAGE_PATH = "/app_upload/image/";
	public static final String UPLOAD_VOICE_PATH = "/app_upload/voice/";
	public static final String UPLOAD_TEMP_PATH = "/app_upload/temp/";
	
	public static final String ADMIN_ID = "402881e846e4b3910146e4b8ce6c0004";
	
	public static final String ROLE_ID_USER_ADMIN = "100001";
	public static final String ROLE_ID_USER_SHOP = "100002";
	
	public static class Status {
		public static final int Create = 0;
		public static final int Success = 1;
		public static final int CheckFail = -1;
	}
	
	public static class BindPlat {
		public static final String Taobao = "taobao";
		public static final String Tmall = "tmall";
		public static final String Jd = "jd";
	}
}
