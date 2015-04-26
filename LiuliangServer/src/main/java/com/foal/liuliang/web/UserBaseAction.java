package com.foal.liuliang.web;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import com.foal.liuliang.pojo.ServerUser;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class UserBaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8033121388697166177L;
	public static String SESSION_USERINFO_KEY = "sessionServerUserInfo";


	private final static Cache<String, Map<String, HttpSession>> sessionCache = CacheBuilder
	          .newBuilder()
	          .maximumSize(10000)
	          .expireAfterWrite(30, TimeUnit.MINUTES)
	          .build();
	
	protected ServerUser getSessionServerUser() {
		ServerUser user = (ServerUser)getAttrFromSession(SESSION_USERINFO_KEY);
		return user;
	}

    
	protected void putSessionToCache(String userId, HttpSession session){
		if (userId == null) {
			return;
		}
		Map<String, HttpSession> map = sessionCache.getIfPresent(userId);
		if (map == null) {
			map = new HashMap<String, HttpSession>();
		}
		map.put(session.getId(), session);
		sessionCache.put(userId, map);
	}
	
	protected void removeSessionFromCache(String userId){
		sessionCache.invalidate(userId);
	}
	
	protected void invalidateSessionFromCache(HttpSession session){
		ServerUser user = (ServerUser)session.getAttribute(SESSION_USERINFO_KEY);
		if (user == null) {
			return;
		}
		Map<String, HttpSession> map = sessionCache.getIfPresent(user.getUserId());
		if (map == null) {
			return;
		}
		map.remove(session.getId());
	}
	
	protected Map<String, HttpSession> getSessionFromCache(String userId){
		return sessionCache.getIfPresent(userId);
	}
	
	protected void updateSessionUser(ServerUser user){
		Map<String, HttpSession> map = sessionCache.getIfPresent(user.getUserId());
		if (map == null) {
			return;
		}
		for (Map.Entry<String, HttpSession> entry : map.entrySet()) {
			entry.getValue().setAttribute(SESSION_USERINFO_KEY, user);
		}
	}
	
	protected void setSessionServerUser(ServerUser user) {
		this.putSessionToCache(user.getUserId(), this.getSession());
		this.setAttrToSession(SESSION_USERINFO_KEY, user);
	}
	
	protected void invalidateSession() {
		this.invalidateSessionFromCache(getSession());
		getSession().invalidate();
	}
}
