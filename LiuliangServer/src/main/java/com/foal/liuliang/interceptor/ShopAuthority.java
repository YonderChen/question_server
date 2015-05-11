package com.foal.liuliang.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.foal.liuliang.config.Constant;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class ShopAuthority extends Authority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 299928136867496818L;
	private final Logger logger = Logger.getLogger(ShopAuthority.class);
	
	/**
	 * 默认构造器
	 */
	public ShopAuthority() {
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
			if (!Constant.ADMIN_ID.equals(loginUser.getUserId())) {	//不是超级管理员，验证路径权限
				if (loginUser.getUserType() != ServerUser.UserType.ShopUser) {
					logger.info("权限错误");
					this.setAuthorityUrl("visit_limit_shop");
					return false;
				}
				if (loginUser.getStatus() != ServerUser.Status.Normal) {
					logger.info("账户被冻结");
					this.setAuthorityUrl("account_freeze_shop");
					return false;
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
