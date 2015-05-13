package com.foal.liuliang.web.admin.useradmin.shopmanage;

import java.util.Calendar;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLShop;
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
		if (llShopBean.getBeginTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(llShopBean.getBeginTime());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			llShopBean.setBeginTime(cal.getTime());
		}
		if (llShopBean.getEndTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(llShopBean.getEndTime());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			llShopBean.setEndTime(cal.getTime());
		}
        PageBean pageBean = this.llShopService.queryLLShop(llShopBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
    
    @Action("edit_input")
    public String editInput() {
    	LLShop shop = this.llShopService.getShop(llShopBean.getShopId());
    	this.setAttrToRequest("shop", shop);
        return SUCCESS;
    }
    
    @Action("edit")
   	public String edit() {
    	llShopBean.setOperator(this.getSessionServerUser());
    	LLShop shop = this.llShopService.updateShop(llShopBean);
        if (shop != null) {
   			ajaxBean = new AjaxBean(true, "编辑成功.");
   		} else {
   			ajaxBean = new AjaxBean(false, "没找到该店铺.请刷新后重试。");
   		}
   		this.ajaxWrite(ajaxBean);
   		return null;
   	}
	
	@Action("check_shop")
	public String checkShop() {
		llShopBean.setOperator(this.getSessionServerUser());
        int status = this.llShopService.checkShop(llShopBean);
		this.ajaxWrite(new AjaxBean(true, String.valueOf(status)));
        return null;
	}
}
