package com.foal.liuliang.service;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.googlecode.flyway.core.Flyway;

@Service(value = "globalConfigService")
public class GlobalConfigService extends DaoSupport {
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(GlobalConfigService.class);

	@Autowired
	private SystemParamService systemParamService;
	
	public void runDbMigrate() {
		File file = new File(Constant.TOMCAT_SERVICE_ADDRESS + "/WEB-INF/classes/config/sql");
		if(!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
		Flyway flyway = new Flyway();
		flyway.setDataSource(this.jdbcTemplate.getDataSource());
		flyway.setInitOnMigrate(true);
		flyway.setLocations("filesystem:"+file.getAbsolutePath());
		flyway.setValidateOnMigrate(false);
		flyway.setOutOfOrder(false);
		flyway.migrate();
	}

	public void initSystemParam(ServletContext context) {
		Constant.INIT_PASSWORD = systemParamService.getStringSystemParam(Constant.INIT_PASSWORD_KEY);
		initBusinessParam(context);
	}

	public void initBusinessParam(ServletContext context) {
		Constant.OneVisitCostScore = systemParamService.getIntSystemParam(Constant.OneVisitCostScoreKey);//每个流浪花费积分
		Constant.PageStayCostScoreMap.put("0", 0);
		Constant.PageStayCostScoreMap.put("1", systemParamService.getIntSystemParam(Constant.PageStayCostScore1Key));
		Constant.PageStayCostScoreMap.put("2", systemParamService.getIntSystemParam(Constant.PageStayCostScore2Key));
		Constant.VisitTimeCostScoreMap.put("0", 0);
		Constant.VisitTimeCostScoreMap.put("1", systemParamService.getIntSystemParam(Constant.VisitTimeCostScore1Key));
		Constant.VisitTimeCostScoreMap.put("2", systemParamService.getIntSystemParam(Constant.VisitTimeCostScore2Key));
		Constant.QuickVerifyCostScore = systemParamService.getIntSystemParam(Constant.QuickVerifyCostScoreKey);//优先审单花费积分
		Constant.QuickExecuteCostScore = systemParamService.getIntSystemParam(Constant.QuickExecuteCostScoreKey);//优先执行花费积分
		
		context.setAttribute(Constant.OneVisitCostScoreKey, Constant.OneVisitCostScore);
		context.setAttribute(Constant.PageStayCostScoreMapKey, Constant.PageStayCostScoreMap);
		context.setAttribute(Constant.VisitTimeCostScoreMapKey, Constant.VisitTimeCostScoreMap);
		context.setAttribute(Constant.QuickVerifyCostScoreKey, Constant.QuickVerifyCostScore);
		context.setAttribute(Constant.QuickExecuteCostScoreKey, Constant.QuickExecuteCostScore);
		context.setAttribute(Constant.PriceScoreMapKey, Constant.PriceScoreMap);
		context.setAttribute(Constant.VIPTimePriceMapKey, Constant.VIPTimePriceMapKey);
	}
	
}
