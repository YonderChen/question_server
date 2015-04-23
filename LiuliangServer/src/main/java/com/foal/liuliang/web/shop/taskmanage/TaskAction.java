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
	LLTaskService llTaskService;
	
	@Autowired
	LLShopService llShopService;
	
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
		return SUCCESS;
	}

	@Action("add_task_step_three")
	public String addTaskStepThree() {
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
	
}
