package com.foal.question.web.admin.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.question.bean.AjaxBean;
import com.foal.question.bean.PageBean;
import com.foal.question.bean.ServerUserBean;
import com.foal.question.config.Constant;
import com.foal.question.pojo.ServerUser;
import com.foal.question.service.RoleService;
import com.foal.question.service.ServerUserService;
import com.foal.question.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class UserAction extends AdminBaseAction implements ModelDriven<ServerUserBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5720362261497664936L;

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
        PageBean pageBean = this.serverUserService.queryServerUser(serverUserBean);
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
    	if (this.getSessionServerUser().getUserId().equals(Constant.ADMIN_ID)) {
    		this.setAttrToRequest("roleIds", this.roleService.queryRoleId(serverUserBean.getUserId()));
        	this.setAttrToRequest("roleList", this.roleService.queryRole(this.getSessionServerUser().getUserId()));
        	this.setAttrToRequest("isAdmin", true);
    	} else {
    		this.setAttrToRequest("isAdmin", false);
    		user.setRoleName(this.roleService.queryRoleName(user.getUserId()));
    	}
    	this.setAttrToRequest("user", user);
        return SUCCESS;
    }
    
    @Action("edit")
   	public String edit() {
    	serverUserBean.setOperator(this.getSessionServerUser());
        boolean result = this.serverUserService.updateServerUserInfo(serverUserBean);
        if (result) {
   			ajaxBean = new AjaxBean(true, "编辑成功.");
   		} else {
   			ajaxBean = new AjaxBean(false, "该用户不存在.");
   		}
   		this.ajaxWrite(ajaxBean);
   		return null;
   	}
    
    @Action("add_input")
    public String addInput() {
    	this.setAttrToRequest("roleList", this.roleService.queryRole(this.getSessionServerUser().getUserId()));
        return SUCCESS;
    }
    
    @Action("add")
   	public String add() {
    	serverUserBean.setOperator(this.getSessionServerUser());
        boolean result = this.serverUserService.addServerUser(serverUserBean);
        if (result) {
   			ajaxBean = new AjaxBean(true, "新增成功.");
   		} else {
   			ajaxBean = new AjaxBean(false, "该用户名已存在.");
   		}
   		this.ajaxWrite(ajaxBean);
   		return null;
   	}
    
}
