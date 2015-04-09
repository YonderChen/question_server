package com.foal.liuliang.web.admin.usershop.accountmanage.shopmanage;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.Page;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ShopAction extends AdminBaseAction implements ModelDriven<Page>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3314755557809492024L;
	
	@Autowired
	LLShopService llShopService;
	
	public ServerUserBean getModel() {
		return null;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}

	@Action("list")
    public String list() {
        List list = this.llShopService.queryLLShop(getSessionServerUser().getUserId());
		this.setAttrToRequest("list", list);
        return SUCCESS;
    }
}
