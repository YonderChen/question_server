package com.foal.question.service;

import com.foal.question.pojo.AppUser;


public interface IAppUserService {
	public AppUser getOpenIdById(String id);
	public AppUser getAppUserByOpenId(String openId);
	public boolean addAppUser(AppUser appUser);
	public boolean updateAppUser(AppUser appUser);
}
