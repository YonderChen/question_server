package com.foal.liuliang.web.admin.usershop.taskmanage;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.bean.LLTaskBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.jersey.resource.tools.ResourceTools;
import com.foal.liuliang.pojo.LLShop;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.util.FileUtil;
import com.foal.liuliang.web.admin.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class TaskAction extends AdminBaseAction implements ModelDriven<LLTaskBean>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 516957496293754173L;

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
			llTaskBean.setOperator(this.getSessionServerUser());
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
			llTaskService.add(llTaskBean);
			this.ajaxWrite(new AjaxBean(true, "任务发布成功"));
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxWrite(new AjaxBean(false, "任务发布失败"));
			return null;
		}
	}

	@Action("list")
    public String list() {
		llTaskBean.setUserId(getSessionServerUser().getUserId());
        PageBean pageBean = this.llTaskService.queryLLTask(llTaskBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }

	@Action("load_shop")
    public String loadShop() {
		LLShopBean llShopBean = new LLShopBean();
		llShopBean.setUserId(getSessionServerUser().getUserId());
		llShopBean.setBindPlat(getRequest().getParameter("bindPlat"));
		List list = this.llShopService.queryLLShopList(llShopBean);
		StringBuilder sb = new StringBuilder();
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
	
	@Action("detail")
    public String detail() {
    	LLTask lltask = this.llTaskService.getLLTask(llTaskBean.getTaskId());
    	this.setAttrToRequest("lltask", lltask);
        return SUCCESS;
    }
}
