package com.foal.liuliang.web.admin.useradmin.system.param;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.SystemParamBean;
import com.foal.liuliang.service.SystemParamService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class SystemParamAction extends UserBaseAction implements ModelDriven<SystemParamBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2411641967573194661L;

	private SystemParamBean paramBean = new SystemParamBean();
	
	@Autowired
	private SystemParamService systemParamService;
	
	
	public SystemParamBean getModel() {
		return paramBean;
	}
	
	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
	public String list() {
		this.setAttrToRequest("paramList", this.systemParamService.querySystemParam());
		return SUCCESS;
	}
	
	@Action("edit")
	public String edit() {
		this.systemParamService.updateSystemParam(paramBean, getSession().getServletContext());
		ajaxBean = new AjaxBean(true, "保存成功.");
		this.ajaxWrite(ajaxBean);
		return null;
	}
	
}
