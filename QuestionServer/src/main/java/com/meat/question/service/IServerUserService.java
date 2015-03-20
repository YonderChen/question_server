package com.meat.question.service;

import java.util.List;

import com.meat.question.bean.PageBean;
import com.meat.question.bean.ServerUserBean;
import com.meat.question.pojo.Menu;
import com.meat.question.pojo.ServerUser;


public interface IServerUserService {
	public ServerUser queryServerUser(ServerUserBean userBean, StringBuffer sb);
	public void updateServerUserLastLoginTime(ServerUser user);
	public List<Menu> queryLoginMenu(String userId);
	public PageBean queryServerUser(ServerUserBean serverUserBean);
	public ServerUser getServerUser(String userId);
	public boolean updateServerUserInfo(ServerUserBean userBean);
	public boolean addServerUser(ServerUserBean userBean);
}
