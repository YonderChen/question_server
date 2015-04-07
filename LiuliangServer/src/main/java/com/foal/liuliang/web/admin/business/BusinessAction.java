package com.foal.liuliang.web.admin.business;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.foal.liuliang.bean.SystemParamBean;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class BusinessAction extends AdminBaseAction implements ModelDriven<SystemParamBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -638687224202724684L;

	private SystemParamBean paramBean = new SystemParamBean();
	
	public SystemParamBean getModel() {
		return paramBean;
	}
	
	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
}
