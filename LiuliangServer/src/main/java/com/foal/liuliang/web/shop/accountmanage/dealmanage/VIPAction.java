package com.foal.liuliang.web.shop.accountmanage.dealmanage;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.service.LLVIPOrderService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class VIPAction extends UserBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1082183931451271908L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	private LLVIPOrderService llVIPOrderService;
	
	public LLDealOrderBean getModel() {
		return llOrderBean;
	}

	@Action("renewalvip")
	public String renewalvip() {
		this.setAttrToRequest("nowDate", new Date());
		return SUCCESS;
	}
	
	@Action("add_vip_order")
	public String add() {
		llOrderBean.setOperator(this.getSessionServerUser());
		if (!Constant.VIPTimePriceMap.containsKey(String.valueOf(llOrderBean.getNum()))) {
			this.ajaxWrite(new AjaxBean(false, "找不到该续费月数对应的价格，请联系管理员"));
	        return null;
		}
		llOrderBean.setPrice((int)(Constant.VIPTimePriceMap.get(String.valueOf(llOrderBean.getNum()))));
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
		llOrderBean.setUserId(this.getSessionServerUser().getUserId());
		PageBean pageBean = this.llVIPOrderService.queryLLScoreOrder(llOrderBean);
        this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
}
