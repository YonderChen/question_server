package com.foal.question.web.admin;

import com.foal.question.pojo.ServerUser;
import com.foal.question.web.BaseAction;

public class AdminBaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -647966570451290024L;
	public static String SESSION_USERINFO_KEY = "sessionServerUserInfo";
	
	protected ServerUser getSessionServerUser() {
		ServerUser user = (ServerUser)getAttrFromSession(SESSION_USERINFO_KEY);
		return user;
	}
	
	protected void setSessionServerUser(ServerUser user) {
		this.setAttrToSession(SESSION_USERINFO_KEY, user);
	}
	
}
