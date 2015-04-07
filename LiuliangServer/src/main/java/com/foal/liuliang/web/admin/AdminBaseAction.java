package com.foal.liuliang.web.admin;

import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.web.BaseAction;

public class AdminBaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8033121388697166177L;
	public static String SESSION_USERINFO_KEY = "sessionServerUserInfo";
	
	protected ServerUser getSessionServerUser() {
		ServerUser user = (ServerUser)getAttrFromSession(SESSION_USERINFO_KEY);
		return user;
	}
	
	protected void setSessionServerUser(ServerUser user) {
		this.setAttrToSession(SESSION_USERINFO_KEY, user);
	}
	
}
