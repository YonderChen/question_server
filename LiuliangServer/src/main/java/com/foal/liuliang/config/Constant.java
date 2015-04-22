package com.foal.liuliang.config;

import java.util.HashMap;
import java.util.Map;


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
	
	public static class ScoreRecordType {
		public static final int Buy = 1;//购买
		public static final int Cost = 2;//消费
	}

	public static int OneVisitCostScore = 3;//每个流浪花费积分
	public static final Map<String, Integer> PageStayCostScoreMap = new HashMap<String, Integer>();//页面停留时间优化花费积分
	static {
		PageStayCostScoreMap.put("0", 0);
		PageStayCostScoreMap.put("1", 40);
		PageStayCostScoreMap.put("2", 50);
	}
	public static final Map<String, Integer> VisitTimeCostScoreMap = new HashMap<String, Integer>();//流量访问时间优化花费积分
	static {
		VisitTimeCostScoreMap.put("0", 0);
		VisitTimeCostScoreMap.put("1", 50);
		VisitTimeCostScoreMap.put("2", 60);
	}
	public static final Map<String, Integer> PriceScoreMap = new HashMap<String, Integer>();//购买积分，价格-积分
	static {
		PriceScoreMap.put("1000", 10000);
		PriceScoreMap.put("2000", 21818);
		PriceScoreMap.put("5000", 60000);
		PriceScoreMap.put("10000", 133333);
		PriceScoreMap.put("20000", 300000);
		PriceScoreMap.put("30000", 450000);
	}
	public static final Map<String, Integer> VIPTimePriceMap = new HashMap<String, Integer>();//VIP续费月数，价格
	static {
		VIPTimePriceMap.put("6", 120);
		VIPTimePriceMap.put("9", 180);
		VIPTimePriceMap.put("12", 216);
		VIPTimePriceMap.put("24", 398);
	}
	public static int QuickVerifyCostScore = 50;//优先审单花费积分
	public static int QuickExecuteCostScore = 50;//优先执行花费积分

	public static final String OneVisitCostScoreKey = "oneVisitCostScore";
	public static final String PageStayCostScoreMapKey = "pageStayCostScoreMap";
	public static final String PageStayCostScore1Key = "pageStayCostScore1";
	public static final String PageStayCostScore2Key = "pageStayCostScore2";
	public static final String VisitTimeCostScoreMapKey = "visitTimeCostScoreMap";
	public static final String VisitTimeCostScore1Key = "visitTimeCostScore1";
	public static final String VisitTimeCostScore2Key = "visitTimeCostScore2";
	public static final String QuickVerifyCostScoreKey = "quickVerifyCostScore";
	public static final String QuickExecuteCostScoreKey = "quickExecuteCostScore";
	public static final String PriceScoreMapKey = "priceScoreMap";//购买积分价格参数，价格/积分
	public static final String VIPTimePriceMapKey = "vipTimePriceMap";//VIP续费月数价格参数，月数/价格
	public static final String PriceScore50Key = "priceScore50";//购买积分价格参数，50
	public static final String PriceScore100Key = "priceScore100";//购买积分价格参数，100
	public static final String PriceScore500Key = "priceScore500";//购买积分价格参数，500
	
}
