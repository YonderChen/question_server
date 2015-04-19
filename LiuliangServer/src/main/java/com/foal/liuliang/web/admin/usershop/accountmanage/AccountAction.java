package com.foal.liuliang.web.admin.usershop.accountmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.foal.liuliang.bean.Page;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class AccountAction extends UserBaseAction implements ModelDriven<Page>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4938843306292212414L;

	public ServerUserBean getModel() {
		return null;
	}

	@Action("renewalvip")
	public String renewalvip() {
		return SUCCESS;
	}

	@Action("inviteothers")
	public String inviteothers() {
		return SUCCESS;
	}
	
}
