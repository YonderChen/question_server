package com.foal.liuliang.web.admin.useradmin.dealmanage;

import java.util.Calendar;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLScoreOrder;
import com.foal.liuliang.pojo.LLVIPOrder;
import com.foal.liuliang.service.LLScoreOrderService;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreAction extends UserBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4011144184353752866L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	LLScoreOrderService llScoreOrderService;
	
	@Autowired
	ServerUserService serverUserService;
	
	public LLDealOrderBean getModel() {
		return llOrderBean;
	}

	@Action("index_score_order")
    public String indexScoreOrder() {
        return SUCCESS;
    }

	@Action("list_score_order")
    public String list() {
		llOrderBean.setUserId("");
		if (llOrderBean.getBeginTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(llOrderBean.getBeginTime());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			llOrderBean.setBeginTime(cal.getTime());
		}
		if (llOrderBean.getEndTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(llOrderBean.getEndTime());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			llOrderBean.setEndTime(cal.getTime());
		}
		PageBean pageBean = this.llScoreOrderService.queryLLScoreOrder(llOrderBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
	
	@Action("edit_score_order_input")
	public String editScoreOrderInput() {
		LLScoreOrder order = this.llScoreOrderService.getOrder(llOrderBean.getOrderId());
        this.setAttrToRequest("order", order);
        return SUCCESS;
	}
	
	@Action("edit_score_order")
	public String editScoreOrder() {
		llOrderBean.setOperator(this.getSessionServerUser());
		AjaxBean ajaxBean = new AjaxBean();
		LLScoreOrder order = this.llScoreOrderService.updateScoreOrder(llOrderBean, ajaxBean);
        if (ajaxBean.isSuccess() && order.getStatus() == LLVIPOrder.Status.Success) {//审核通过
			this.updateSessionUser(order.getServerUser());
		}
        this.ajaxWrite(ajaxBean);
        return null;
	}
}
