package com.foal.liuliang.web.shop.accountmanage.dealmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.service.LLScoreOrderService;
import com.foal.liuliang.util.GsonTools;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreAction extends UserBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4774394154373735988L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	LLScoreOrderService llScoreOrderService;
	
	public LLDealOrderBean getModel() {
		return llOrderBean;
	}

	@Action("get_score")
	public String getScore() {
		return SUCCESS;
	}
	
	@Action("add_score_order")
	public String add() {
		llOrderBean.setOperator(this.getSessionServerUser());
		if (!Constant.PriceScoreMap.containsKey(String.valueOf(llOrderBean.getPrice()))) {
			this.ajaxWrite(new AjaxBean(false, "提交数据有误，请重试"));
	        return null;
		}
		System.out.println(GsonTools.parseJsonObject(llOrderBean).toString());
		llOrderBean.setNum(Constant.PriceScoreMap.get(String.valueOf(llOrderBean.getPrice())));
		llScoreOrderService.add(llOrderBean);
		this.ajaxWrite(new AjaxBean(true, "订单提交成功，请耐心等待审核"));
        return null;
	}

	@Action("index_score_order")
    public String indexScoreOrder() {
        return SUCCESS;
    }

	@Action("list_score_order")
    public String list() {
		llOrderBean.setUserId(getSessionServerUser().getUserId());
		PageBean pageBean = this.llScoreOrderService.queryLLScoreOrder(llOrderBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
	
}
