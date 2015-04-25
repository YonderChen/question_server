package com.foal.liuliang.web.shop.taskmanage;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
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
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.util.FileUtil;
import com.foal.liuliang.util.StringTools;
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
	private LLTaskService llTaskService;
	
	@Autowired
	private LLShopService llShopService;
	
	public LLTaskBean getModel() {
		return llTaskBean;
	}

	@Action("add_task_step_one")
	public String addTaskStepOne() {
		String bindPlat = llTaskBean.getBindPlat();
		if (StringTools.isEmpty(bindPlat)) {
			bindPlat = "taobao";
		}
		this.setAttrToRequest("bindPlat", bindPlat);
		String shopId = llTaskBean.getShopId();
		if (StringTools.isEmpty(shopId)) {
			shopId = "";
		}
		this.setAttrToRequest("shopId", shopId);
		this.setAttrToRequest("taobao_shop_num", llShopService.getShopNum(getSessionServerUser().getUserId(), "taobao"));
		this.setAttrToRequest("tmall_shop_num", llShopService.getShopNum(getSessionServerUser().getUserId(), "tmall"));
		this.setAttrToRequest("jd_shop_num", llShopService.getShopNum(getSessionServerUser().getUserId(), "jd"));
		return SUCCESS;
	}

	@Action("add_task_step_two")
	public String addTaskStepTwo() {
		if (StringTools.isEmpty(llTaskBean.getTaskId())) {
			if (StringTools.isEmpty(llTaskBean.getBindPlat()) || StringTools.isEmpty(llTaskBean.getShopId())) {
				this.alertAndRedirect(null, "web/shop/taskmanage/add_task_step_one");
				return null;
			}
			LLShop llShop = llShopService.getShop(llTaskBean.getShopId());
			if (llShop == null || !StringTools.equalsStr(llShop.getBindPlat(), llTaskBean.getBindPlat())) {
				this.alertAndRedirect(null, "web/shop/taskmanage/add_task_step_one");
				return null;
			}
			LLTask task = new LLTask();
			task.setLlShop(llShop);
			task.setTaskType(NumberUtils.toInt(llTaskBean.getTaskType(), 0));
			this.setAttrToRequest("llTask", task);
		} else {
			LLTask task = llTaskService.getLLTask(llTaskBean.getTaskId());
			if (task == null) {
				this.alertAndRedirect(null, "web/shop/taskmanage/add_task_step_one");
				return null;
			}
			this.setAttrToRequest("llTask", task);
		}
		return SUCCESS;
	}

	@Action("add_task_step_three")
	public String addTaskStepThree() {
		try {
			String fileSuffix = ResourceTools.getFileSuffix(llTaskBean.getGoodsImgFileFileName());
			if(!ResourceTools.checkSuffix(fileSuffix, ResourceTools.getImageSuffixs())) {
				this.alertAndGoBack("请选择正确的图片");
				return null;
			}
			String fileDirPath = Constant.TOMCAT_SERVICE_ADDRESS + Constant.UPLOAD_IMAGE_PATH;
			String fileName = FileUtil.uploadFile(llTaskBean.getGoodsImgFile(), fileDirPath, llTaskBean.getGoodsImgFileFileName());
			llTaskBean.setGoodsImg(Constant.UPLOAD_IMAGE_PATH + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			this.alertAndRedirect("上传图片失败", "web/shop/taskmanage/add_task_step_one");
			return null;
		}
		try{
			llTaskBean.setOperator(this.refreshAndGetSessionServerUser());
			//验证vip是否到期
			if (!getSessionServerUser().checkVIPValid()) {
				this.alertAndRedirect("vip有效期已过，请先续费vip", "web/shop/taskmanage/add_task_step_one");
				return null;
			} 
			//店铺审核验证处理
			LLShop shop = llShopService.getShop(llTaskBean.getShopId());
			if (shop == null) {
				this.alertAndRedirect("找不到该店铺", "web/shop/taskmanage/add_task_step_one");
				return null;
			}
			if (shop.getStatus() != Constant.Status.Success) {
				this.alertAndRedirect("该店铺未审核通过，请等待审核通过后再次尝试", "web/shop/taskmanage/add_task_step_one");
				return null;
			}
			//设置流量优化默认值
			llTaskBean.setPageStayType(Constant.PageStayTypeDefault);
			llTaskBean.setVisitTimeType(Constant.VisitTimeTypeDefault);
			llTaskBean.setIsQuickVerify(Constant.QuickVerifyDefault);
			llTaskBean.setIsQuickExecute(Constant.QuickExecuteDefault);
			if (StringTools.isEmpty(llTaskBean.getKeyword4().trim()) || llTaskBean.getOrderNumberOneDay4() < 0) {
				llTaskBean.setOrderNumberOneDay4(0);
			}
			if (StringTools.isEmpty(llTaskBean.getKeyword5().trim()) || llTaskBean.getOrderNumberOneDay5() < 0) {
				llTaskBean.setOrderNumberOneDay5(0);
			}
			if (llTaskBean.getOrderNumberOneDay1() < 10) {
				llTaskBean.setOrderNumberOneDay1(10);
			}
			if (llTaskBean.getOrderNumberOneDay2() < 10) {
				llTaskBean.setOrderNumberOneDay2(10);
			}
			if (llTaskBean.getOrderNumberOneDay3() < 10) {
				llTaskBean.setOrderNumberOneDay3(10);
			}
			if (llTaskBean.getOrderNumberOneDay4() < 10 && llTaskBean.getOrderNumberOneDay4() != 0) {
				llTaskBean.setOrderNumberOneDay4(10);
			}
			if (llTaskBean.getOrderNumberOneDay5() < 10 && llTaskBean.getOrderNumberOneDay5() != 0) {
				llTaskBean.setOrderNumberOneDay5(10);
			}
			LLTask llTask = llTaskService.add(llTaskBean, getSessionServerUser());
			this.setAttrToRequest("llTask", llTask);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.alertAndRedirect("任务添加失败", "web/shop/taskmanage/add_task_step_one");
			return null;
		}
	}

	@Action("add_task_success")
	public String addTaskSuccess() {
		try{
			llTaskBean.setOperator(this.refreshAndGetSessionServerUser());
			//验证vip是否到期
			if (!getSessionServerUser().checkVIPValid()) {
				this.ajaxWrite(new AjaxBean(false, "vip有效期已过，请先续费vip"));
				return null;
			} 
			LLTask task = llTaskService.getLLTask(llTaskBean.getTaskId());
			if (task == null) {
				this.alertAndRedirect("任务发布失败，请从第一步重试", "web/shop/taskmanage/add_task_failed");
			}
			if (task.getStatus() != Constant.TaskStatus.Create) {
				this.alertAndRedirect("改任务已经发布过！", "web/shop/taskmanage/add_task_failed");
			}
			//店铺审核验证处理
			LLShop shop = task.getLlShop();
			if (shop == null) {
				this.alertAndRedirect("任务发布失败，找不到该店铺", "web/shop/taskmanage/add_task_failed");
				return null;
			}
			if (shop.getStatus() != Constant.Status.Success) {
				this.alertAndRedirect("任务发布失败，该店铺未审核通过，请等待审核通过后再次尝试", "/web/shop/taskmanage/add_task_failed");
				return null;
			}
			task.setPageStayType(llTaskBean.getPageStayType());
			task.setVisitTimeType(llTaskBean.getVisitTimeType());
			task.setIsQuickVerify(llTaskBean.getIsQuickVerify());
			task.setIsQuickExecute(llTaskBean.getIsQuickExecute());
			if (!llTaskService.updatePublishTask(task, getSessionServerUser())) {
				this.alertAndRedirect("任务发布失败", "web/shop/taskmanage/add_task_failed");
				return null;
			}
			this.alertAndRedirect("任务发布成功", "web/shop/taskmanage/task_detail");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			this.alertAndRedirect("任务发布失败", "web/shop/taskmanage/add_task_failed");
			return null;
		}
	}
	
	@Action("add_task_failed")
	public String addTaskFailed() {
		return SUCCESS;
	}

	@Action("load_add_task_shop")
    public String loadAddTaskShop() {
		LLShopBean llShopBean = new LLShopBean();
		llShopBean.setUserId(getSessionServerUser().getUserId());
		llShopBean.setBindPlat(llTaskBean.getBindPlat());
		List list = this.llShopService.queryLLShopList(llShopBean);
		StringBuilder sb = new StringBuilder();
		if (list.size() > 0) {
			boolean hasShop = false;
			for (Object o : list) {
				LLShop shop = (LLShop) o;
				if (shop.getShopId().equals(llTaskBean.getShopId())) {
					hasShop = true;
				}
			}
			sb.append("<ul class=\"clearfix\">");
			int i = 0;
			for (Object o : list) {
				LLShop shop = (LLShop) o;
				if (hasShop) {
					if (shop.getShopId().equals(llTaskBean.getShopId())) {
						sb.append("<li class=\"cur\">");
					} else {
						sb.append("<li>");
					}
				} else {
					if (i == 0) {
						sb.append("<li class=\"cur\">");
					} else {
						sb.append("<li>");
					}
				}
				sb.append("<i></i>");
				sb.append("<label style=\"display: inline-block; cursor: pointer;\">");
				sb.append("<a> ");
				sb.append("<input id=\"shop_radio_" + shop.getShopId() + "\" type=\"radio\" onclick=\"changeShop('" + shop.getShopId() + "');\" name=\"shop_id\" value=\"" + shop.getShopId() + "\"");
				if (hasShop) {
					if (shop.getShopId().equals(llTaskBean.getShopId())) {
						sb.append(" checked=\"checked\"");
					}
				} else {
					if (i == 0) {
						sb.append(" checked=\"checked\"");
					}
				}
				sb.append(" > ");
				sb.append("<span>");
				sb.append(shop.getBindName());
				sb.append("</span></a></label></li>");
				i++;
			}
			sb.append("</ul>");
			int remain = Constant.PlatBindShopMaxNum - list.size();
			if (remain > 0) {
				sb.append("<p class=\"Release-shop-tip\">");
				sb.append("<em>(还可绑定");
				sb.append(remain < 0 ? 0 : remain);
				sb.append("个店铺)</em><a href=\"" + Constant.PRO_CTX_VALUE + "/web/shop/accountmanage/shopmanage/bind_shop\" target=\"_blank\">+ 绑定更多店铺</a>");
				sb.append("</p>");
			} else {
				sb.append("<p class=\"Release-shop-tip\">");
				sb.append("<em>(店铺绑定个数已满)</em>");
				sb.append("</p>");
			}
		} else {
			sb.append("<div class=\"Release-shop-center\">");
			sb.append("<p>您当前还未绑定店铺，无法发布任务</p>");
			sb.append("<p>请先<a href=\"" + Constant.PRO_CTX_VALUE + "/web/shop/accountmanage/shopmanage/bind_shop\" target=\"_blank\">绑定店铺</a>后再发布任务</p>");
			sb.append("</div>");
			sb.append("<p class=\"Release-shop-tip\">");
			sb.append("<a href=\"" + Constant.PRO_CTX_VALUE + "/web/shop/accountmanage/shopmanage/bind_shop\" target=\"_blank\">+ 绑定店铺</a>");
			sb.append("</p>");
		}
		this.ajaxWrite(new AjaxBean(true, sb.toString()));
        return null;
    }

	@Action("task_detail")
	public String taskDetail(){
		if (StringTools.isEmpty(llTaskBean.getTaskId())) {
			this.alertAndGoBack("任务不存在！");
			return null;
		}
		LLTask llTask = llTaskService.getLLTask(llTaskBean.getTaskId());
		if (llTask == null) {
			this.alertAndGoBack("任务不存在！");
			return null;
		}
		this.setAttrToRequest("llTask", llTask);
		return SUCCESS;
	}
}
