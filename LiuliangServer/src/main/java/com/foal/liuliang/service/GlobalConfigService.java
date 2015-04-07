package com.foal.liuliang.service;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.SystemParam;
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
	}
	
}
