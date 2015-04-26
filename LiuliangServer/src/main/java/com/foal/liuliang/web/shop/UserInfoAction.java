package com.foal.liuliang.web.shop;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class UserInfoAction extends UserBaseAction implements ModelDriven<ServerUserBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 189044384603590315L;

	@Autowired
	private ServerUserService serverUserService;
	
	private ServerUserBean userBean = new ServerUserBean();

	public ServerUserBean getModel() {
		return userBean;
	}

	@Action("edit_pass")
	public String editPass() {
		userBean.setUserId(this.getSessionServerUser().getUserId());
		ServerUser user = this.serverUserService.updateServerUserPass(userBean);
		if (user != null) {
			ajaxBean = new AjaxBean(true, "保存成功");
			this.setSessionServerUser(user);
		} else {
			ajaxBean = new AjaxBean(false, "原始密码错误");
		}
		this.ajaxWrite(ajaxBean);
		return null;
	}
	
	@Action("edit_qq")
	public String editqq() {
		userBean.setUserId(this.getSessionServerUser().getUserId());
		ServerUser user = this.serverUserService.updateServerUserQQ(userBean);
		this.setSessionServerUser(user);
		ajaxBean = new AjaxBean(true, "保存成功");
		this.ajaxWrite(ajaxBean);
		return null;
	}
	
	@Action("edit_phone")
	public String editPhone() {
		userBean.setUserId(this.getSessionServerUser().getUserId());
		ServerUser user = this.serverUserService.updateServerUserPhone(userBean);
		this.setSessionServerUser(user);
		ajaxBean = new AjaxBean(true, "保存成功");
		this.ajaxWrite(ajaxBean);
		return null;
	}

	@Action("userinfo")
	public String userinfo() {
		this.getSessionServerUser();
		return SUCCESS;
	}
}