package com.foal.question.web.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.question.bean.AjaxBean;
import com.foal.question.bean.ServerUserBean;
import com.foal.question.config.Constant;
import com.foal.question.pojo.ServerUser;
import com.foal.question.service.ServerUserService;
import com.foal.question.util.StringUtil;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class WelcomeAction extends AdminBaseAction implements ModelDriven<ServerUserBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8007911817907874520L;

	@Autowired
	private ServerUserService serverUserService;

	private ServerUserBean userBean = new ServerUserBean();

	public ServerUserBean getModel() {
		return userBean;
	}

	@Action("main")
	public String main() {
		return SUCCESS;
	}
	
	@Action("welcome")
	public String welcome() {
		ServerUser user = this.getSessionServerUser();
		if (StringUtil.checkPassword(Constant.INIT_PASSWORD, user.getEncryptedPassword(), user.getAssistantPassword())) {
			this.setAttrToRequest("editPassModal", true);
		}
		return SUCCESS;
	}

	@Action("edit_pass")
	public String editPass() {
		userBean.setUserId(this.getSessionServerUser().getUserId());
		ServerUser user = this.serverUserService.updateServerUserPass(userBean);
		if (user != null) {
			ajaxBean = new AjaxBean(true, "保存成功");
			this.setSessionServerUser(user);
		} else {
			ajaxBean = new AjaxBean(false, "原始密码错误");
		}
		this.ajaxWrite(ajaxBean);
		return null;
	}
	
	@Action("edit_info")
	public String editInfo() {
		userBean.setUserId(this.getSessionServerUser().getUserId());
		ServerUser user = this.serverUserService.updateServerUserBaseInfo(userBean);
		this.setSessionServerUser(user);
		ajaxBean = new AjaxBean(true, "保存成功");
		this.ajaxWrite(ajaxBean);
		return null;
	}
	
}