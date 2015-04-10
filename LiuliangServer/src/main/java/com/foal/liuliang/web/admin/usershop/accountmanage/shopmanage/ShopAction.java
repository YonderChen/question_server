package com.foal.liuliang.web.admin.usershop.accountmanage.shopmanage;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ShopAction extends AdminBaseAction implements ModelDriven<LLShopBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3314755557809492024L;

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
	
	@Action("add")
	public String add() {
		llShopBean.setOperator(this.getSessionServerUser());
		if (!this.llShopService.checkBindName(llShopBean)) {
			this.ajaxWrite(new AjaxBean(false, "店铺旺旺与验证页面旺旺不符"));
		}
		if (!this.llShopService.checkVerifyCode(llShopBean)) {
			this.ajaxWrite(new AjaxBean(false, "验证页面标题中未找到匹配的验证码"));
		} else {
			this.llShopService.add(llShopBean);
			this.ajaxWrite(new AjaxBean(true, "绑定成功.请耐心等待店铺审核工作"));
		}
        return null;
	}

	@Action("list")
    public String list() {
        List list = this.llShopService.queryLLShop(getSessionServerUser().getUserId());
		this.setAttrToRequest("list", list);
        return SUCCESS;
    }
}
