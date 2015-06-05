package com.foal.question.web.admin.appapitest;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.foal.question.bean.APITestBean;
import com.foal.question.bean.AjaxBean;
import com.foal.question.config.Constant;
import com.foal.question.util.MD5Tools;
import com.foal.question.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class APITestAction extends AdminBaseAction implements ModelDriven<APITestBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5720362261497664936L;

    private APITestBean apitestBean = new APITestBean();

    public APITestBean getModel() {
		return apitestBean;
	}

    @Action("index")
    public String index() {
        return SUCCESS;
    }

    @Action("get_sign")
    public String get_sign() {
    	short command = NumberUtils.toShort(apitestBean.getCommand(), (short)0);
		String root = apitestBean.getRoot() == null ? "" : apitestBean.getRoot();
		String version = apitestBean.getVersion() == null ? "" : apitestBean.getVersion();
		if (command == 0) {
			ajaxBean = new AjaxBean(false, "command错误");
		} else {
			String signStr = command + root + version + Constant.KEY_OF_SIGN;
			String sign = MD5Tools.hashToMD5(signStr);
			ajaxBean = new AjaxBean(true, sign);
		}
		this.ajaxWrite(ajaxBean);
		return null;
    }

}
