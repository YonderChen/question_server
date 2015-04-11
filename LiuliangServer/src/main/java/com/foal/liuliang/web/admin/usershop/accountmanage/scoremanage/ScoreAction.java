package com.foal.liuliang.web.admin.usershop.accountmanage.scoremanage;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLScoreOrderBean;
import com.foal.liuliang.service.LLScoreOrderService;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreAction extends AdminBaseAction implements ModelDriven<LLScoreOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185455689368526250L;

	private LLScoreOrderBean llScoreOrderBean = new LLScoreOrderBean();

	@Autowired
	LLScoreOrderService llScoreOrderService;
	
	public LLScoreOrderBean getModel() {
		return llScoreOrderBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("add")
	public String add() {
		llScoreOrderBean.setOperator(this.getSessionServerUser());
		llScoreOrderBean.setScoreNum((int)(llScoreOrderBean.getPrice() * 1.8));
		llScoreOrderService.add(llScoreOrderBean);
		this.ajaxWrite(new AjaxBean(true, "订单提交成功，请耐心等待审核"));
        return null;
	}

	@Action("list")
    public String list() {
        List list = this.llScoreOrderService.queryLLScoreOrder(getSessionServerUser().getUserId());
		this.setAttrToRequest("list", list);
        return SUCCESS;
    }
}
