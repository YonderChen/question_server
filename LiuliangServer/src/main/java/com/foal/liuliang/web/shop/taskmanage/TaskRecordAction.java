package com.foal.liuliang.web.shop.taskmanage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.bean.LLTaskRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLShop;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class TaskRecordAction extends UserBaseAction implements ModelDriven<LLTaskRecordBean>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8880338425644486933L;

	private LLTaskRecordBean llTaskRecordBean = new LLTaskRecordBean();
	
	@Autowired
	private LLTaskService llTaskService;
	
	@Autowired
	private LLShopService llShopService;
	
	public LLTaskRecordBean getModel() {
		return llTaskRecordBean;
	}

	@Action("task_list")
    public String taskList() {
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
        return SUCCESS;
    }

	@Action("load_shop")
    public String loadShop() {
		LLShopBean llShopBean = new LLShopBean();
		llShopBean.setUserId(getSessionServerUser().getUserId());
		List list = this.llShopService.queryLLShopList(llShopBean);
		StringBuilder sb = new StringBuilder();
		sb.append("<option value=\"\">");
		sb.append("请选择");
		sb.append("</option>");
		for (Object o : list) {
			LLShop shop = (LLShop) o;
			sb.append("<option value=\"");
			sb.append(shop.getShopId());
			sb.append("\">");
			sb.append(shop.getBindName());
			sb.append("</option>");
		}
		this.ajaxWrite(new AjaxBean(true, sb.toString()));
        return null;
    }
	
	@Action("detail")
    public String detail() {
    	LLTask lltask = this.llTaskService.getLLTask(llTaskRecordBean.getTaskId());
    	this.setAttrToRequest("lltask", lltask);
        return SUCCESS;
    }
}
