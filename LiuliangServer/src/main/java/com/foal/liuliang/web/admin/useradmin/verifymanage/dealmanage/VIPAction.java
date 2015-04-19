package com.foal.liuliang.web.admin.useradmin.verifymanage.dealmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.service.LLVIPOrderService;
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
		PageBean pageBean = this.llVIPOrderService.queryLLScoreOrder(llOrderBean);
        this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
	
	@Action("check_vip_order")
	public String checkShop() {
		llOrderBean.setOperator(this.getSessionServerUser());
        int status = this.llVIPOrderService.checkVIPOrder(llOrderBean);
		this.ajaxWrite(new AjaxBean(true, String.valueOf(status)));
        return null;
	}
}
