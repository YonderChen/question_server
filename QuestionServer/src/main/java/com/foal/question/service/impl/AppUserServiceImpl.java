package com.foal.question.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.IAppUserService;

@SuppressWarnings("unchecked")
@Service(value = "appUserService")
public class AppUserServiceImpl extends DaoSupport implements IAppUserService{

	@Override
	public boolean addAppUser(AppUser appUser) {
		this.hibernateDao.save(appUser);
		return true;
	}

	@Override
	public AppUser getAppUserByOpenId(String openId) {
		String queryHql = "from AppUser as u where u.openId = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, openId);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public AppUser getOpenIdById(String id) {
		return this.hibernateDao.get(AppUser.class, id);
	}

	@Override
	public boolean updateAppUser(AppUser appUser) {
		this.hibernateDao.update(appUser);
		return true;
	}
	
}
