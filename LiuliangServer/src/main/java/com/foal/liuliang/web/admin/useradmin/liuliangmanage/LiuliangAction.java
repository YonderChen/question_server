package com.foal.liuliang.web.admin.useradmin.liuliangmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.LLLiuliangBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.service.LLLiuliangService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class LiuliangAction extends UserBaseAction implements ModelDriven<LLLiuliangBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -357112630505176351L;

	private LLLiuliangBean llLiuliangBean = new LLLiuliangBean();
	
	@Autowired
	LLLiuliangService llLiuliangService;
	
	public LLLiuliangBean getModel() {
		return llLiuliangBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
    public String list() {
        PageBean pageBean = this.llLiuliangService.queryLLLiuliang(llLiuliangBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }

}
