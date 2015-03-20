package com.meat.question.web.admin;

import com.meat.question.pojo.ServerUser;
import com.meat.question.web.BaseAction;

public class AdminBaseAction extends BaseAction {
	private static final long serialVersionUID = -1697153674698435403L;
	public static String SESSION_USERINFO_KEY = "sessionServerUserInfo";
	
	protected ServerUser getSessionServerUser() {
		ServerUser user = (ServerUser)getAttrFromSession(SESSION_USERINFO_KEY);
		return user;
	}
	
	protected void setSessionServerUser(ServerUser user) {
		this.setAttrToSession(SESSION_USERINFO_KEY, user);
	}
	
}
