package com.foal.question.web.admin.appuser;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.question.bean.AjaxBean;
import com.foal.question.bean.AppUserBean;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppUserService;
import com.foal.question.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class AppUserAction extends AdminBaseAction implements ModelDriven<AppUserBean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6304528677823172483L;

	private AppUserBean appUserBean = new AppUserBean();
	
	@Autowired
	private AppUserService appUserService;
	
	public AppUserBean getModel() {
		return appUserBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
	public String list() {
		this.setAttrToRequest("pageBean", this.appUserService.queryAppUser(appUserBean));
		return SUCCESS;
	}
	
	@Action("edit_input")
	public String editInput() {
    	AppUser user = this.appUserService.getAppUserById(appUserBean.getUid());
    	this.setAttrToRequest("user", user);
		return SUCCESS;
	}
	
	@Action("edit_status")
	public String edit() {
		boolean result = this.appUserService.updateAppUserStatus(appUserBean);
		if (result) {
			ajaxBean = new AjaxBean(true, "设置成功.");
		} else {
			ajaxBean = new AjaxBean(false, "指定用户不存在.");
		}
		this.ajaxWrite(ajaxBean);
		return null;
	}
}
