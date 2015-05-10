package com.foal.liuliang.web.shop;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.LLTaskRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class WelcomeAction extends UserBaseAction implements ModelDriven<LLTaskRecordBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3608935410391804029L;

	@Autowired
	LLTaskService llTaskService;
	
	@Autowired
	LLShopService llShopService;

	private LLTaskRecordBean llTaskRecordBean = new LLTaskRecordBean();

	public LLTaskRecordBean getModel() {
		return llTaskRecordBean;
	}

	@Action("center")
	public String center() {
		this.getSessionServerUser();
		this.setAttrToRequest("nowDate", new Date());
		llTaskRecordBean.setUserId(getSessionServerUser().getUserId());
		if (llTaskRecordBean.getBeginTime() == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -1);
			llTaskRecordBean.setBeginTime(cal.getTime());
		}
		if (llTaskRecordBean.getEndTime() == null) {
			llTaskRecordBean.setEndTime(new Date());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(llTaskRecordBean.getBeginTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		llTaskRecordBean.setBeginTime(cal.getTime());
		cal.setTime(llTaskRecordBean.getEndTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		llTaskRecordBean.setEndTime(cal.getTime());
		if ("taskId".equals(llTaskRecordBean.getQueryKey())) {
			llTaskRecordBean.setTaskId(llTaskRecordBean.getQueryValue());
		} else if ("goodsName".equals(llTaskRecordBean.getQueryKey())) {
			llTaskRecordBean.setGoodsName(llTaskRecordBean.getQueryValue());
		}
        PageBean pageBean = this.llTaskService.queryLLTaskRecord(llTaskRecordBean);
		this.setAttrToRequest("pageBean", pageBean);
		this.setAttrToRequest("llTaskRecordBean", llTaskRecordBean);
		int allLLTaskCount = this.llTaskService.queryAllLLTaskRecordCount(llTaskRecordBean.getUserId());
		this.setAttrToRequest("allLLTaskCount", allLLTaskCount);
		int executingLLTaskCount = this.llTaskService.queryLLTaskRecordCountByStatus(llTaskRecordBean.getUserId(), LLTask.Status.Executing);
		this.setAttrToRequest("executingLLTaskCount", executingLLTaskCount);
		int finishLLTaskCount = this.llTaskService.queryLLTaskRecordCountByStatus(llTaskRecordBean.getUserId(), LLTask.Status.Finish);
		this.setAttrToRequest("finishLLTaskCount", finishLLTaskCount);
		this.setAttrToRequest("conditionAction", "/web/shop/center");
		return SUCCESS;
	}
}