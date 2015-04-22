package com.foal.liuliang.web.shop;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.pojo.Menu;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.RoleService;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class IndexAction extends UserBaseAction implements ModelDriven<ServerUserBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6326182089465782556L;

	private ServerUserBean userBean = new ServerUserBean();
	
	@Autowired
	private ServerUserService serverUserService;
	
	@Autowired
	private RoleService roleService;

	public ServerUserBean getModel() {
		return this.userBean;
	}
	
	@Action("register_index")
	public String registerIndex() {
		return SUCCESS;
	}
	
	@Action("register")
	public String register() {
		StringBuffer sb = new StringBuffer();
		userBean.setRoleIds(Constant.ROLE_ID_USER_SHOP);
		userBean.setOperator(serverUserService.getServerUser(Constant.ADMIN_ID));
        boolean result = this.serverUserService.addServerUser(userBean, sb);
        if (result) {
   			ajaxBean = new AjaxBean(true, "新增成功.");
   			String redirectUrl = Constant.PRO_CTX_VALUE + "/web/shop/index";
   			this.setAttrToSession("redirectUrl", redirectUrl);
   			ajaxBean = new AjaxBean(true);
   			ajaxBean.setRedirectUrl(redirectUrl);
   			this.ajaxWrite(ajaxBean);
   		} else {
   			ajaxBean = new AjaxBean(false, sb.toString());
   	   		return null;
   		}
   		this.ajaxWrite(ajaxBean);
		return null;
	}
	
	@Action("login")
	public String login() {
		/*
		String validationCode = (String)this.getAttrFromSession("validationCode");
		if (validationCode == null) {
			ajaxBean = new AjaxBean(false, "验证码超时.");
			this.ajaxWrite(ajaxBean);
			return null;
		} else if (!this.userBean.getCode().equalsIgnoreCase(validationCode)) {
			ajaxBean = new AjaxBean(false, "验证码错误.");
			this.ajaxWrite(ajaxBean);
			return null;
		}
		*/
		StringBuffer sb = new StringBuffer();
		ServerUser user = this.serverUserService.queryServerUser(userBean, sb);
		if (user == null) {
			ajaxBean = new AjaxBean(false, sb.toString());
			this.ajaxWrite(ajaxBean);
			return null;
		}
		if (!Constant.ADMIN_ID.equals(user.getUserId())) {	//不是超级管理员，验证路径权限
			List<String> roleIds = roleService.queryRoleIds(user.getUserId());
			if (!roleIds.contains(Constant.ROLE_ID_USER_SHOP)) {
				ajaxBean = new AjaxBean(false, "您没有权限访问该页面");
				this.ajaxWrite(ajaxBean);
				return null;
			}
		}
		this.setAttrToSession("loginLast", user.getLastLoginTime());
		user.setLastLoginIp(this.getRequest().getRemoteAddr());
		this.setSessionServerUser(user);
		this.serverUserService.updateServerUserLastLoginTime(user);
		List<Menu> menuList = this.serverUserService.queryLoginMenu(user.getUserId());
		if (menuList.size() == 0) {
			ajaxBean = new AjaxBean(false, "权限获取失败.");
			this.ajaxWrite(ajaxBean);
			return null;
		}
		String redirectUrl = Constant.PRO_CTX_VALUE + "/web/shop/center";
		this.setAttrToSession("redirectUrl", redirectUrl);
		ajaxBean = new AjaxBean(true);
		ajaxBean.setRedirectUrl(redirectUrl);
		this.ajaxWrite(ajaxBean);
		return null;
	}
	
	@Action("logout")
	public String logout() {
		this.getSession().invalidate();
		return SUCCESS;
	}
	
	@Action("identify")
	public String identify() {
		ServerUser user = (ServerUser)this.getAttrFromSession(SESSION_USERINFO_KEY);
		if (user == null) {
			ajaxBean = new AjaxBean(false);
			this.ajaxWrite(ajaxBean);
		} else {
			String redirectUrl = "";
			if (this.getAttrFromSession("redirectUrl") != null) {
				redirectUrl = this.getAttrFromSession("redirectUrl").toString();
			}
			ajaxBean = new AjaxBean(true);
			ajaxBean.setRedirectUrl(redirectUrl);
			this.ajaxWrite(ajaxBean);
		}
		return null;
	}
}
