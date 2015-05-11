package com.foal.liuliang.web.admin;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.util.StringUtil;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class WelcomeAction extends UserBaseAction implements ModelDriven<ServerUserBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273200623453693193L;

	@Autowired
	private ServerUserService serverUserService;

	private ServerUserBean userBean = new ServerUserBean();

	public ServerUserBean getModel() {
		return userBean;
	}

	@Action("main")
	public String main() {
		return SUCCESS;
	}
	
	@Action("welcome")
	public String welcome() {
		ServerUser user = this.getSessionServerUser();
		if (StringUtil.checkPassword(Constant.INIT_PASSWORD, user.getEncryptedPassword(), user.getAssistantPassword())) {
			this.setAttrToRequest("editPassModal", true);
		}
		return SUCCESS;
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
	
	@Action("edit_info")
	public String editInfo() {
		userBean.setUserId(this.getSessionServerUser().getUserId());
		ServerUser user = this.serverUserService.updateServerUserBaseInfo(userBean);
		this.setSessionServerUser(user);
		ajaxBean = new AjaxBean(true, "保存成功");
		this.ajaxWrite(ajaxBean);
		return null;
	}

	@Action("baseinfo")
	public String baseinfo() {
		this.setAttrToRequest("nowDate", new Date());
		return SUCCESS;
	}
	
	@Action("clear_hibernate_query_cache")
	public String clearHibernateQueryCache() {
		serverUserService.clearHibernateCache();
		this.ajaxWrite(new AjaxBean(true, "清除hibernate缓存成功"));
		return null;
	}
}