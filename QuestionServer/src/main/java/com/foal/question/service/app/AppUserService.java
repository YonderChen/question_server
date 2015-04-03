package com.foal.question.service.app;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppUser;
import com.foal.question.util.StringTools;

@SuppressWarnings("unchecked")
@Service(value = "appUserService")
public class AppUserService extends DaoSupport {

	public boolean addAppUser(AppUser appUser) {
		this.hibernateDao.save(appUser);
		return true;
	}

	public AppUser getAppUserByOpenId(String openId) {
		String queryHql = "from AppUser as u where u.openId = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, openId);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public AppUser getAppUserById(String id) {
		if (StringTools.isBlank(id)) {
			return null;
		}
		return this.hibernateDao.get(AppUser.class, id);
	}

	public boolean updateAppUser(AppUser appUser) {
		this.hibernateDao.update(appUser);
		return true;
	}
	
}
