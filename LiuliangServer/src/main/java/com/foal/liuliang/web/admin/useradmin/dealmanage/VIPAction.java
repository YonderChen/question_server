package com.foal.liuliang.web.admin.useradmin.dealmanage;

import java.util.Calendar;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLVIPOrder;
import com.foal.liuliang.service.LLVIPOrderService;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class VIPAction extends UserBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1716056576268155844L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	LLVIPOrderService llVIPOrderService;

	@Autowired
	ServerUserService serverUserService;
	
	public LLDealOrderBean getModel() {
		return llOrderBean;
	}

	@Action("index_vip_order")
    public String indexVipOrder() {
        return SUCCESS;
    }
	
	@Action("list_vip_order")
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
		PageBean pageBean = this.llVIPOrderService.queryLLScoreOrder(llOrderBean);
        this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
	
	@Action("edit_vip_order_input")
	public String editVipOrderInput() {
		LLVIPOrder order = this.llVIPOrderService.getOrder(llOrderBean.getOrderId());
        this.setAttrToRequest("order", order);
        return SUCCESS;
	}
	
	@Action("edit_vip_order")
	public String editVipOrder() {
		llOrderBean.setOperator(this.getSessionServerUser());
		AjaxBean ajaxBean = new AjaxBean();
		LLVIPOrder order = this.llVIPOrderService.updateVIPOrder(llOrderBean, ajaxBean);
        if (ajaxBean.isSuccess() && order.getStatus() == LLVIPOrder.Status.Success) {//审核通过
			this.updateSessionUser(order.getServerUser());
		}
        this.ajaxWrite(ajaxBean);
        return null;
	}
}
