package com.foal.liuliang.web.shop;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.RoleService;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.util.MD5Tools;
import com.foal.liuliang.util.MailUtil;
import com.foal.liuliang.util.StringTools;
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
		String redirectUrl = Constant.PRO_CTX_VALUE + "/web/shop/center";
		this.setAttrToSession("redirectUrl", redirectUrl);
		ajaxBean = new AjaxBean(true);
		ajaxBean.setRedirectUrl(redirectUrl);
		this.ajaxWrite(ajaxBean);
		return null;
	}
	
	@Action("logout")
	public String logout() {
		this.invalidateSession();
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
	
	@Action("find_passwd")
	public String findPasswd() {
		return SUCCESS;
	}
	
	@Action("send_success")
	public String sendSuccess() {
		String validationCode = (String)this.getAttrFromSession("validationCode");
		if (validationCode == null) {
			this.alertAndGoBack("邮箱有误.");
			return null;
		} else if (!this.userBean.getCode().equalsIgnoreCase(validationCode)) {
			this.alertAndGoBack("验证码错误.");
			return null;
		}
		String email = userBean.getEmail();
		if (StringTools.isEmpty(email)) {
			this.alertAndGoBack("邮箱有误.");
			return null;
		}
		ServerUser serverUser = serverUserService.getServerUserByEmail(email);
		if (serverUser == null) {
			this.alertAndGoBack("邮箱不存在.");
			return null;
		}
		StringBuilder url = new StringBuilder();
		url.append(Constant.CONTEXT_WEB_URL + "web/shop/reset_passwd?");
		url.append("userId=");
		url.append(serverUser.getUserId());
		long now = System.currentTimeMillis();
		url.append("&time=");
		url.append(now);
		String sign = MD5Tools.hashToMD5(serverUser.getUserId() + now + Constant.SIGN_KEY);
		url.append("&sign=");
		url.append(sign);
		StringBuilder mailContent = new StringBuilder();
		mailContent.append("请点击一下链接重置您的密码。<br>");
		mailContent.append("<a href=\"");
		mailContent.append(url);
		mailContent.append("\">");
		mailContent.append(url);
		mailContent.append("</a>");
		if(MailUtil.sendSystemMail(serverUser.getEmail(), "找回密码", mailContent.toString())){
			return SUCCESS;
		} else {
			this.alertAndGoBack("邮件发送失败");
			return null;
		}
	}
	
	@Action("reset_passwd")
	public String resetPasswd(){
		String userId = this.getRequest().getParameter("userId");
		String time = this.getRequest().getParameter("time");
		String sign = this.getRequest().getParameter("sign");
		if (StringTools.isEmpty(userId) || StringTools.isEmpty(time) || StringTools.isEmpty(sign)) {
			this.alertAndRedirect("链接有误", "");
			return null;
		}
		ServerUser serverUser = serverUserService.getServerUser(userId);
		if (serverUser == null) {
			this.alertAndGoBack("用户不存在.");
			return null;
		}
		String checkSign = MD5Tools.hashToMD5(serverUser.getUserId() + time + Constant.SIGN_KEY);
		if (!sign.equals(checkSign)) {
			this.alertAndRedirect("签名错误", "");
			return null;
		}
		long urlTime = NumberUtils.toLong(time, 0);
		long now = System.currentTimeMillis();
		if (now - urlTime > Constant.ResetPwdUrlEffectiveTime) {
			this.alertAndRedirect("链接已失效，请重新请求发送邮件", "web/shop/find_passwd");
			return null;
		}
		this.setAttrToRequest("userId", userId);
		this.setAttrToRequest("time", urlTime);
		this.setAttrToRequest("sign", sign);
		return SUCCESS;
	}
	

	@Action("reset_passwd_success")
	public String resetPasswdSuccess(){
		String userId = this.getRequest().getParameter("userId");
		String time = this.getRequest().getParameter("time");
		String sign = this.getRequest().getParameter("sign");
		String password = this.getRequest().getParameter("password");
		if (StringTools.isEmpty(userId) || StringTools.isEmpty(time) || StringTools.isEmpty(sign)) {
			this.alertAndRedirect("链接有误", "");
			return null;
		}
		String checkSign = MD5Tools.hashToMD5(userId + time + Constant.SIGN_KEY);
		if (!sign.equals(checkSign)) {
			this.alertAndRedirect("签名错误", "");
			return null;
		}
		long urlTime = NumberUtils.toLong(time, 0);
		long now = System.currentTimeMillis();
		if (now - urlTime > Constant.ResetPwdUrlEffectiveTime) {
			this.alertAndRedirect("链接已失效，请重新请求发送邮件", "web/shop/find_passwd");
			return null;
		}
		ServerUser user = serverUserService.updateServerUserPass(userId, password);
		if (user == null) {
			this.alertAndRedirect("找不到用户", "");
		}
		this.alertAndRedirect("修改成功", "");
		return null;
	}
}
