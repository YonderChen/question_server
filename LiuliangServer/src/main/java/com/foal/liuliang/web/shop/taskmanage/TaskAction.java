package com.foal.liuliang.web.shop.taskmanage;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.bean.LLTaskBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.jersey.resource.tools.ResourceTools;
import com.foal.liuliang.pojo.LLShop;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.util.FileUtil;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class TaskAction extends UserBaseAction implements ModelDriven<LLTaskBean>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8880338425644486933L;

	private LLTaskBean llTaskBean = new LLTaskBean();
	
	@Autowired
	LLTaskService llTaskService;
	
	@Autowired
	LLShopService llShopService;
	
	public LLTaskBean getModel() {
		return llTaskBean;
	}

	@Action("add_task")
	public String addTask() {
		return SUCCESS;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("add")
	public String add() {
		try {
			String fileSuffix = ResourceTools.getFileSuffix(llTaskBean.getGoodsImgFileFileName());
			if(!ResourceTools.checkSuffix(fileSuffix, ResourceTools.getImageSuffixs())) {
				this.ajaxWrite(new AjaxBean(false, "请选择正确的图片"));
				return null;
			}
			String fileDirPath = Constant.TOMCAT_SERVICE_ADDRESS + Constant.UPLOAD_IMAGE_PATH;
			String fileName = FileUtil.uploadFile(llTaskBean.getGoodsImgFile(), fileDirPath, llTaskBean.getGoodsImgFileFileName());
			llTaskBean.setGoodsImg(Constant.UPLOAD_IMAGE_PATH + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxWrite(new AjaxBean(false, "上传图片失败"));
			return null;
		}
		try{
			llTaskBean.setOperator(this.refreshAndGetSessionServerUser());
			//验证vip是否到期
			if (!getSessionServerUser().checkVIPValid()) {
				this.ajaxWrite(new AjaxBean(false, "vip有效期已过，请先续费vip"));
				return null;
			} 
			//店铺审核验证处理
			LLShop shop = llShopService.getShop(llTaskBean.getShopId());
			if (shop == null) {
				this.ajaxWrite(new AjaxBean(false, "找不到该店铺"));
				return null;
			}
			if (shop.getStatus() != Constant.Status.Success) {
				this.ajaxWrite(new AjaxBean(false, "该店铺未审核通过，请等待审核通过后再次尝试"));
				return null;
			}
			if (llTaskService.add(llTaskBean, getSessionServerUser())) {
				this.ajaxWrite(new AjaxBean(true, "任务发布成功"));
			} else {
				this.ajaxWrite(new AjaxBean(false, "您的积分不足，请先充值后再次尝试"));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxWrite(new AjaxBean(false, "任务发布失败"));
			return null;
		}
	}

	@Action("load_add_task_shop")
    public String loadAddTaskShop() {
		LLShopBean llShopBean = new LLShopBean();
		llShopBean.setUserId(getSessionServerUser().getUserId());
		llShopBean.setBindPlat(getRequest().getParameter("bindPlat"));
		List list = this.llShopService.queryLLShopList(llShopBean);
		StringBuilder sb = new StringBuilder();
		sb.append("<option value=\"\">");
		sb.append("请选择");
		sb.append("</option>");
		for (Object o : list) {
			LLShop shop = (LLShop) o;
			sb.append("<option value=\"");
			sb.append(shop.getShopId());
			sb.append("\">");
			sb.append(shop.getBindName());
			sb.append("</option>");
		}
		this.ajaxWrite(new AjaxBean(true, sb.toString()));
        return null;
    }
	
}
