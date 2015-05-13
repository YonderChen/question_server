package com.foal.liuliang.web.admin.useradmin.shopusermanage;

import java.util.Calendar;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.RoleService;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class UserAction extends UserBaseAction implements ModelDriven<ServerUserBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 159922337889419508L;

	@Autowired
    private ServerUserService serverUserService;

	@Autowired
	private RoleService roleService;

    private ServerUserBean serverUserBean = new ServerUserBean();

    public ServerUserBean getModel() {
		return serverUserBean;
	}

    @Action("index")
    public String index() {
        return SUCCESS;
    }

    @Action("list")
    public String list() {
    	serverUserBean.setOperator(getSessionServerUser());
		if (serverUserBean.getBeginTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(serverUserBean.getBeginTime());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			serverUserBean.setBeginTime(cal.getTime());
		}
		if (serverUserBean.getEndTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(serverUserBean.getEndTime());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			serverUserBean.setEndTime(cal.getTime());
		}
        PageBean pageBean = this.serverUserService.queryShopUser(serverUserBean);
		for (int i = 0; i < pageBean.getList().size(); i++) {
			ServerUser user = (ServerUser)pageBean.getList().get(i);
			user.setRoleName(this.roleService.queryRoleName(user.getUserId()));
		}
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
    
    @Action("edit_input")
    public String editInput() {
    	ServerUser user = this.serverUserService.getServerUser(serverUserBean.getUserId());
    	this.setAttrToRequest("user", user);
        return SUCCESS;
    }
    
    @Action("edit")
   	public String edit() {
    	serverUserBean.setOperator(this.getSessionServerUser());
        ServerUser user = this.serverUserService.updateShopUserInfo(serverUserBean);
        if (user != null) {
   			ajaxBean = new AjaxBean(true, "编辑成功.");
   			this.updateSessionUser(user);
   		} else {
   			ajaxBean = new AjaxBean(false, "该用户不存在.");
   		}
   		this.ajaxWrite(ajaxBean);
   		return null;
   	}
    
}
