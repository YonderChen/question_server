package com.foal.liuliang.web.admin.usershop.accountmanage.shopmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.util.RandomTools;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ShopAction extends UserBaseAction implements ModelDriven<LLShopBean>{

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
	
	@Action("bind_shop")
	public String bindShop() {
		this.setAttrToSession("goodsVerifyCode", getVerifyCode());
		return SUCCESS;
	}

	
	private String getVerifyCode() {
	    String str = "";
	    char[] numberArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	    char[] wordArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		str += randomWord(4, numberArr);
		str += "-";
		str += randomWord(4, wordArr);
		return str;
	}
	private String randomWord(int length, char[] arr){
		String str = "";
	    // 随机产生
	    for(int i=0; i<length; i++){
	        int pos = RandomTools.random(0, arr.length);
	        str += arr[pos];
	    }
	    return str;
	}
	
	@Action("add")
	public String add() {
		llShopBean.setVerifyCode(this.getAttrFromSession("goodsVerifyCode").toString());
		if (!this.llShopService.checkShopUrl(llShopBean)) {
			this.ajaxWrite(new AjaxBean(false, "请输入正确的店铺地址"));
			return null;
		}
		if (!this.llShopService.checkGoodsUrl(llShopBean)) {
			this.ajaxWrite(new AjaxBean(false, "请输入正确的商品地址"));
			return null;
		} 
		llShopBean.setOperator(this.refreshAndGetSessionServerUser());
		llShopBean.setUserId(this.getSessionServerUser().getUserId());
		if (!this.llShopService.checkShopNum(llShopBean)) {
			this.ajaxWrite(new AjaxBean(false, "商店绑定个数已满"));
			return null;
		} 
		if (!getSessionServerUser().checkVIPValid()) {
			this.ajaxWrite(new AjaxBean(false, "vip有效期已过，请先续费vip"));
			return null;
		} else {
			this.llShopService.add(llShopBean, Constant.Status.Create);
			this.ajaxWrite(new AjaxBean(true, "绑定成功.请耐心等待店铺审核工作"));
	        return null;
		}
	}

	@Action("list")
    public String list() {
		llShopBean.setOperator(this.getSessionServerUser());
		llShopBean.setUserId(getSessionServerUser().getUserId());
        PageBean pageBean = this.llShopService.queryLLShop(llShopBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
}
