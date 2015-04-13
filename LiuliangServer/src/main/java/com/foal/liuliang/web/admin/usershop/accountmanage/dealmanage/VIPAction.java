package com.foal.liuliang.web.admin.usershop.accountmanage.dealmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.service.LLVIPOrderService;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class VIPAction extends AdminBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6845704668780315015L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	LLVIPOrderService llVIPOrderService;
	
	public LLDealOrderBean getModel() {
		return llOrderBean;
	}

	@Action("renewalvip")
	public String renewalvip() {
		return SUCCESS;
	}
	
	@Action("add_vip_order")
	public String add() {
		llOrderBean.setOperator(this.getSessionServerUser());
		llOrderBean.setPrice((int)(llOrderBean.getNum() * Constant.VIPPriceRate));
		llVIPOrderService.add(llOrderBean);
		this.ajaxWrite(new AjaxBean(true, "订单提交成功，请耐心等待审核"));
        return null;
	}

	@Action("index_vip_order")
    public String indexVipOrder() {
        return SUCCESS;
    }
	
	@Action("list_vip_order")
    public String list() {
		PageBean pageBean = this.llVIPOrderService.queryLLScoreOrder(getSessionServerUser().getUserId(), llOrderBean);
        this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
}
