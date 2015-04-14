package com.foal.liuliang.web.admin.useradmin.verifymanage.dealmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.service.LLScoreOrderService;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreAction extends AdminBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4011144184353752866L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	LLScoreOrderService llScoreOrderService;
	
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
		PageBean pageBean = this.llScoreOrderService.queryLLScoreOrder(llOrderBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
	
	@Action("check_score_order")
	public String checkShop() {
		llOrderBean.setOperator(this.getSessionServerUser());
        int status = this.llScoreOrderService.checkScoreOrder(llOrderBean);
		this.ajaxWrite(new AjaxBean(true, String.valueOf(status)));
        return null;
	}
	
}
