package com.foal.liuliang.interceptor;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.config.Constant;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.RoleService;
import com.foal.liuliang.util.StringTools;
import com.foal.liuliang.util.StringUtil;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class AdminAuthority extends Authority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 299928136867496818L;
	private final Logger logger = Logger.getLogger(AdminAuthority.class);

	@Autowired
	private RoleService roleService;
	
	/**
	 * 默认构造器
	 */
	public AdminAuthority() {
		this.setAuthorityUrl("shop_login");// 设置返回的配置路径
	}

	@Override
	public boolean authority(ActionInvocation AI) {
		// 取得请求相关的ActionContext实例
		ActionContext ctx = AI.getInvocationContext();
		// 取出名为user的Session属性
		String requestUrl = ServletActionContext.getRequest().getRequestURI();
		ServerUser loginUser = (ServerUser)ctx.getSession().get(UserBaseAction.SESSION_USERINFO_KEY);
		// 如果有登陆,通过认证
		if (loginUser != null) {
			if (requestUrl.equals(Constant.PRO_CTX_VALUE + "/web/admin/welcome") ||
					requestUrl.equals(Constant.PRO_CTX_VALUE + "/web/admin/main") ||
					requestUrl.equals(Constant.PRO_CTX_VALUE + "/web/admin/edit_pass") ||
					requestUrl.equals(Constant.PRO_CTX_VALUE + "/web/admin/edit_info")) {
				return true;
			}
			if (StringUtil.checkPassword(Constant.INIT_PASSWORD, loginUser.getEncryptedPassword(), loginUser.getAssistantPassword())) {
				logger.info("必须修改密码");
				this.setAuthorityUrl("admin_welcome");
				return false;
			}
			if (!Constant.ADMIN_ID.equals(loginUser.getUserId())) {	//不是超级管理员，验证路径权限
				List<String> roleIds = roleService.queryRoleIds(loginUser.getUserId());
				if(StringTools.contains(requestUrl, Constant.PRO_CTX_VALUE + "/web/admin/useradmin/")) {
					if (!roleIds.contains(Constant.ROLE_ID_USER_ADMIN)) {
						logger.info("权限错误");
						this.setAuthorityUrl("visit_limit");
						return false;
					}
				} else if(StringTools.contains(requestUrl, Constant.PRO_CTX_VALUE + "/web/admin/usershop/")) {
					if (!roleIds.contains(Constant.ROLE_ID_USER_SHOP)) {
						logger.info("权限错误");
						this.setAuthorityUrl("visit_limit");
						return false;
					}
				}
			}
			return true;
		} else {
			logger.info("登录超时,返回到登录界面");
			if (requestUrl.indexOf("/web/admin") > 0) {//管理员界面
				this.setAuthorityUrl("admin_login");// 设置返回的配置路径
			} else {
				this.setAuthorityUrl("shop_login");
			}
			return false;
		}
	}

}
