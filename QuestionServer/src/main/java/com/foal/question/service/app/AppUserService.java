package com.foal.question.service.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foal.question.bean.AppUserBean;
import com.foal.question.bean.PageBean;
import com.foal.question.config.Constant;
import com.foal.question.dao.DaoSupport;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.pojo.AppUser;
import com.foal.question.util.MD5Tools;
import com.foal.question.util.StringTools;
import com.foal.question.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "appUserService")
public class AppUserService extends DaoSupport {

	public boolean addAppUser(AppUser appUser) {
		this.hibernateDao.save(appUser);
		return true;
	}

	public boolean isUsernameExist(String username) {
		String queryHql = "from AppUser as u where u.username = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, username);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public AppUser getAppUserByUsernameAndPwd(String username, String password) {
		String queryHql = "from AppUser as u where u.username = ? and password = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, username, MD5Tools.hashToMD5(password + Constant.PASSWORD_SECRET_KEY));
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public ResultMap editPassword(String uid, String oldPassword, String newPassword) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser appUser = getAppUserById(uid);
		if (appUser == null) {
			ret.setResult(RetCode.Faild, "uid不正确");
			return ret;
		} else {
			if(!appUser.getPassword().equals(MD5Tools.hashToMD5(oldPassword + Constant.PASSWORD_SECRET_KEY))) {
				ret.setResult(RetCode.Faild, "旧密码不正确");
				return ret;
			}
			appUser.setPassword(MD5Tools.hashToMD5(newPassword + Constant.PASSWORD_SECRET_KEY));
			Date now = new Date();
			appUser.setUpdateAt(now);
			this.hibernateDao.update(appUser);
			ret.setResult(RetCode.Success);
			return ret;
		}
	}

	public AppUser getAppUserByOpenId(String openId, int userType) {
		String queryHql = "from AppUser as u where u.openId = ? and userType = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, openId, userType);
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
	
	public PageBean queryAppUser(AppUserBean appUserBean) {
		String queryHql = "from AppUser as u where 1 = 1";
		Map paramMap = new HashMap();
		if (!StringUtil.isEmpty(appUserBean.getUid())) {
			queryHql += " and u.uid = :uid";
			paramMap.put("uid", appUserBean.getUid());
		}
		if (!StringUtil.isEmpty(appUserBean.getName())) {
			queryHql += " and u.name like :name";
			paramMap.put("name", "%"+appUserBean.getName()+"%");
		}
		int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		queryHql += " order by u.updateAt desc";
		List list = this.hibernateDao.queryList(queryHql, appUserBean.getPage(), appUserBean.getPageSize(), paramMap);
		return new PageBean(list, allRow, appUserBean.getPage(), appUserBean.getPageSize());
	}

	public boolean updateAppUserStatus(AppUserBean appUserBean) {
		AppUser user = this.getAppUserById(appUserBean.getUid());
		if (user == null) {
			return false;
		}
		user.setStatus(appUserBean.getStatus());
		user.setUpdateAt(new Date());
		this.hibernateDao.update(user);
		return true;
	}
}
