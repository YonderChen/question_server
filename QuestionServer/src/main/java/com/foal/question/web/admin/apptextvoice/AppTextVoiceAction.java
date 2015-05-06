package com.foal.question.web.admin.apptextvoice;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.question.bean.AjaxBean;
import com.foal.question.bean.AppTextVoiceBean;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.service.app.AppTextVoiceService;
import com.foal.question.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class AppTextVoiceAction extends AdminBaseAction implements ModelDriven<AppTextVoiceBean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6304528677823172483L;

	private AppTextVoiceBean appTextVoiceBean = new AppTextVoiceBean();
	
	@Autowired
	private AppTextVoiceService appTextVoiceService;
	
	public AppTextVoiceBean getModel() {
		return appTextVoiceBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
	public String list() {
		this.setAttrToRequest("pageBean", this.appTextVoiceService.queryTextVoice(appTextVoiceBean));
		return SUCCESS;
	}
	
	@Action("voice_detail")
	public String voiceDetail() {
    	this.setAttrToRequest("voiceUrl", appTextVoiceBean.getVoiceUrl());
		return SUCCESS;
	}
	
	@Action("del")
	public String edit() {
		AppTextVoice record = appTextVoiceService.getRecord(appTextVoiceBean.getId());
		if (record == null) {
			ajaxBean = new AjaxBean(true, "要删除的记录不存在.");
			this.ajaxWrite(ajaxBean);
			return null;
		}
		appTextVoiceService.deleteRecord(record);
		ajaxBean = new AjaxBean(true, "删除成功.");
		this.ajaxWrite(ajaxBean);
		return null;
	}
}
