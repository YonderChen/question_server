package com.foal.liuliang.web.admin.taskmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.foal.liuliang.bean.Page;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class TaskAction extends AdminBaseAction implements ModelDriven<Page>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 516957496293754173L;

	public ServerUserBean getModel() {
		return null;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
}
