package com.foal.question.web.admin.apptextimage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.question.bean.AjaxBean;
import com.foal.question.bean.AppTextImageBean;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.service.app.AppTextImageService;
import com.foal.question.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class AppTextImageAction extends AdminBaseAction implements ModelDriven<AppTextImageBean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6304528677823172483L;

	private AppTextImageBean appTextImageBean = new AppTextImageBean();
	
	@Autowired
	private AppTextImageService appTextImageService;
	
	public AppTextImageBean getModel() {
		return appTextImageBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
	public String list() {
		this.setAttrToRequest("pageBean", this.appTextImageService.queryTextImage(appTextImageBean));
		return SUCCESS;
	}
	
	@Action("del")
	public String edit() {
		AppTextImage record = appTextImageService.getRecord(appTextImageBean.getId());
		if (record == null) {
			ajaxBean = new AjaxBean(true, "要删除的记录不存在.");
			this.ajaxWrite(ajaxBean);
			return null;
		}
		appTextImageService.deleteRecord(record);
		ajaxBean = new AjaxBean(true, "删除成功.");
		this.ajaxWrite(ajaxBean);
		return null;
	}
}
