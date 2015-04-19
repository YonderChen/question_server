package com.foal.liuliang.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.ServerUserService;

public class UserBaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8033121388697166177L;
	public static String SESSION_USERINFO_KEY = "sessionServerUserInfo";

	@Autowired
	private ServerUserService serverUserService;
	
	protected ServerUser getSessionServerUser() {
		ServerUser user = (ServerUser)getAttrFromSession(SESSION_USERINFO_KEY);
		return user;
	}
	
	protected void setSessionServerUser(ServerUser user) {
		this.setAttrToSession(SESSION_USERINFO_KEY, user);
	}
	/**
	 * 刷新数据库最新的用户数据到缓存，并获取用户信息
	 * @return
	 */
	protected ServerUser refreshAndGetSessionServerUser() {
		ServerUser user = getSessionServerUser();
		if (user == null) {
			return null;
		}
		ServerUser newUserData = serverUserService.getServerUser(user.getUserId());
		setSessionServerUser(newUserData);
		return newUserData;
	}
	
}
