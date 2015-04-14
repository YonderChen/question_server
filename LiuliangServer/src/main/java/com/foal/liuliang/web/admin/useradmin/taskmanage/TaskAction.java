package com.foal.liuliang.web.admin.useradmin.taskmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.LLTaskBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class TaskAction extends AdminBaseAction implements ModelDriven<LLTaskBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -357112630505176351L;

	private LLTaskBean llTaskBean = new LLTaskBean();
	
	@Autowired
	LLTaskService llTaskService;
	
	@Autowired
	LLShopService llShopService;
	
	public LLTaskBean getModel() {
		return llTaskBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
    public String list() {
		llTaskBean.setUserId("");
        PageBean pageBean = this.llTaskService.queryLLTask(llTaskBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }

	@Action("detail")
    public String detail() {
    	LLTask lltask = this.llTaskService.getLLTask(llTaskBean.getTaskId());
    	this.setAttrToRequest("lltask", lltask);
        return SUCCESS;
    }
}
