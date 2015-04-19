package com.foal.liuliang.web.admin.useradmin.verifymanage.shopmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ShopAction extends UserBaseAction implements ModelDriven<LLShopBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5890892070511906681L;

	private LLShopBean llShopBean = new LLShopBean();
	
	@Autowired
	LLShopService llShopService;
	
	public LLShopBean getModel() {
		return llShopBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
    public String list() {
		llShopBean.setOperator(this.getSessionServerUser());
		llShopBean.setUserId("");
        PageBean pageBean = this.llShopService.queryLLShop(llShopBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
	
	@Action("check_shop")
	public String checkShop() {
		llShopBean.setOperator(this.getSessionServerUser());
        int status = this.llShopService.checkShop(llShopBean);
		this.ajaxWrite(new AjaxBean(true, String.valueOf(status)));
        return null;
	}
}
