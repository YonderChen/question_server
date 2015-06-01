package com.foal.liuliang.web.shop.taskmanage;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.LLLiuliangBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.service.LLLiuliangService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-shop") })
public class LiuliangAction extends UserBaseAction implements ModelDriven<LLLiuliangBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3233806274263855422L;

	private LLLiuliangBean llLiuliangBean = new LLLiuliangBean();
	
	@Autowired
	private LLLiuliangService llLiuliangService;
	
	public LLLiuliangBean getModel() {
		return llLiuliangBean;
	}
	
	@Action("liuliang_list")
    public String list() {
		if (llLiuliangBean.getBeginTime() == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -1);
			llLiuliangBean.setBeginTime(cal.getTime());
		}
		if (llLiuliangBean.getEndTime() == null) {
			llLiuliangBean.setEndTime(new Date());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(llLiuliangBean.getBeginTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		llLiuliangBean.setBeginTime(cal.getTime());
		cal.setTime(llLiuliangBean.getEndTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		llLiuliangBean.setEndTime(cal.getTime());
        PageBean pageBean = this.llLiuliangService.queryLLLiuliang(llLiuliangBean);
		this.setAttrToRequest("pageBean", pageBean);
		this.setAttrToRequest("llLiuliangBean", llLiuliangBean);
        return SUCCESS;
    }

}
