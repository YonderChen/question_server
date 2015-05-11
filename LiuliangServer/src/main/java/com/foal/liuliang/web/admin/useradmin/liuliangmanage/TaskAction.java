package com.foal.liuliang.web.admin.useradmin.liuliangmanage;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLTaskBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class TaskAction extends UserBaseAction implements ModelDriven<LLTaskBean>{

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

	@Action("task_detail")
    public String taskDetail() {
    	LLTask lltask = this.llTaskService.getLLTask(llTaskBean.getTaskId());
    	this.setAttrToRequest("llTask", lltask);
        return SUCCESS;
    }

	@Action("check_task")
    public String checkTask() {
    	LLTask llTask = this.llTaskService.getLLTask(llTaskBean.getTaskId());
    	if(llTask.getServerUser().getStatus() != ServerUser.Status.Normal){
        	this.ajaxWrite(new AjaxBean(false, "该用户账户已被冻结，审核失败"));
            return null;
    	}
    	//调用第三方接口
    	//
    	//============
    	llTask.setStatus(LLTask.Status.Executing);
    	llTask.setCheckTime(new Date());
    	llTask.setCheckAdmin(this.getSessionServerUser());
    	this.llTaskService.updateLLTask(llTask);
    	this.ajaxWrite(new AjaxBean(true, "审核成功"));
        return null;
    }
}
