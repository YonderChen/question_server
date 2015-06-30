package com.foal.question.service;

import java.io.File;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.foal.question.config.Constant;
import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.SystemParam;
import com.googlecode.flyway.core.Flyway;

@Service(value = "globalConfigService")
public class GlobalConfigService extends DaoSupport {
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(GlobalConfigService.class);
	
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

	public void initSystemParam() {
		SystemParam sp1 = this.hibernateDao.get(SystemParam.class, Constant.INIT_PASSWORD_KEY);
		Constant.INIT_PASSWORD = sp1.getValue();
		SystemParam logParam = this.hibernateDao.get(SystemParam.class, Constant.LOG_PARAM_SWITCH_KEY);
		Constant.LOG_PARAM_SWITCH = NumberUtils.toInt(logParam.getValue(), 0);
		SystemParam logResult = this.hibernateDao.get(SystemParam.class, Constant.LOG_RESULT_SWITCH_KEY);
		Constant.LOG_RESULT_SWITCH = NumberUtils.toInt(logResult.getValue(), 0);
	}
	
}
