package com.foal.liuliang.web.admin.usershop.accountmanage.dealmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.service.LLScoreOrderService;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreAction extends AdminBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185455689368526250L;

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
	
	@Action("get_score_num")
	public String getScoreNum() {
		int price = llOrderBean.getPrice();
		Integer scoreNum = Constant.PriceScoreMap.get(String.valueOf(price));
		if (scoreNum == null) {
			this.ajaxWrite(new AjaxBean(false, "找不到该价格对应的积分，请联系管理员"));
	        return null;
		}
		this.ajaxWrite(new AjaxBean(true, String.valueOf(scoreNum)));
        return null;
	}
}
