package com.foal.question.service.app;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;

@Service(value = "appCommonService")
public class AppCommonService extends DaoSupport {

	public boolean addEntity(Object entity) {
		this.hibernateDao.save(entity);
		return true;
	}

	public boolean updateEntity(Object entity) {
		this.hibernateDao.update(entity);
		return true;
	}
	
}
