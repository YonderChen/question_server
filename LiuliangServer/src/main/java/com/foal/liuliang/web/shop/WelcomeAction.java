package com.foal.liuliang.web.shop;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.RoleService;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.util.StringUtil;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class WelcomeAction extends UserBaseAction implements ModelDriven<ServerUserBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273200623453693193L;

	@Autowired
	private ServerUserService serverUserService;
	
	@Autowired
	private RoleService roleService;

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

	@Action("baseinfo")
	public String baseinfo() {
		ServerUser loginUser = this.refreshAndGetSessionServerUser();
		List<String> roleIds = roleService.queryRoleIds(loginUser.getUserId());
		int isShopUser = 1;
		if (!roleIds.contains(Constant.ROLE_ID_USER_SHOP)) {
			isShopUser = 0;
		}
		this.setAttrToRequest("isShopUser", isShopUser);
		this.setAttrToRequest("nowDate", new Date());
		return SUCCESS;
	}

	@Action("userinfo")
	public String userinfo() {
		return SUCCESS;
	}

	@Action("center")
	public String center() {
		return SUCCESS;
	}
}