package com.foal.liuliang.web.shop.accountmanage.dealmanage;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.LLPayRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLScoreOrder;
import com.foal.liuliang.pojo.LLVIPOrder;
import com.foal.liuliang.service.LLScoreOrderService;
import com.foal.liuliang.service.LLVIPOrderService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-shop") })
public class PayRecordAction extends UserBaseAction implements ModelDriven<LLPayRecordBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 72187367485218853L;

	private LLPayRecordBean llPayRecordBean = new LLPayRecordBean();

	@Autowired
	private LLScoreOrderService llScoreOrderService;
	@Autowired
	private LLVIPOrderService llVIPOrderService;
	
	public LLPayRecordBean getModel() {
		return llPayRecordBean;
	}

	@Action("pay_record")
    public String list() {
		llPayRecordBean.setUserId(getSessionServerUser().getUserId());
		if (llPayRecordBean.getBeginTime() == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -1);
			llPayRecordBean.setBeginTime(cal.getTime());
		}
		if (llPayRecordBean.getEndTime() == null) {
			llPayRecordBean.setEndTime(new Date());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(llPayRecordBean.getBeginTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		llPayRecordBean.setBeginTime(cal.getTime());
		cal.setTime(llPayRecordBean.getEndTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		llPayRecordBean.setEndTime(cal.getTime());
		int recordType = NumberUtils.toInt(this.getRequest().getParameter("record_type"), -1);
		if (recordType > 0) {
			llPayRecordBean.setRecordType(recordType);
		}
		if (recordType == LLPayRecordBean.RecordType.Vip) {
			PageBean pageBean = this.llVIPOrderService.queryLLPayOrder(llPayRecordBean);
			this.setAttrToRequest("pageBean", pageBean);
		} else {
			PageBean pageBean = this.llScoreOrderService.queryLLPayOrder(llPayRecordBean);
			this.setAttrToRequest("pageBean", pageBean);
		}
		this.setAttrToRequest("llPayRecordBean", llPayRecordBean);
        return SUCCESS;
    }

	@Action("pay_record_detail")
    public String detail() {
		llPayRecordBean.setUserId(getSessionServerUser().getUserId());
		int recordType = NumberUtils.toInt(this.getRequest().getParameter("record_type"), -1);
		if (recordType > 0) {
			llPayRecordBean.setRecordType(recordType);
		}
		String orderId = this.getRequest().getParameter("order_id");
		if (orderId == null) {
			this.alertAndGoBack("订单不存在");
	        return null;
		}
		if (recordType == LLPayRecordBean.RecordType.Vip) {
			LLVIPOrder order = this.llVIPOrderService.getOrder(orderId);
			if (order == null) {
				this.alertAndGoBack("订单不存在");
		        return null;
			}
			this.setAttrToRequest("order", order);
		} else {
			LLScoreOrder order = this.llScoreOrderService.getOrder(orderId);
			if (order == null) {
				this.alertAndGoBack("订单不存在");
		        return null;
			}
			this.setAttrToRequest("order", order);
		}
		this.setAttrToRequest("llPayRecordBean", llPayRecordBean);
        return SUCCESS;
    }

}
