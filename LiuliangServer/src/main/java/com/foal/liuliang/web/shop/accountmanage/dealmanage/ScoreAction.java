package com.foal.liuliang.web.shop.accountmanage.dealmanage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.jersey.resource.tools.ResourceTools;
import com.foal.liuliang.service.LLScoreOrderService;
import com.foal.liuliang.util.FileUtil;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreAction extends UserBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4774394154373735988L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	private LLScoreOrderService llScoreOrderService;
	
	public LLDealOrderBean getModel() {
		return llOrderBean;
	}

	@Action("get_score")
	public String getScore() {
		return SUCCESS;
	}
	
	@Action("add_score_order")
	public String add() {
		llOrderBean.setOperator(this.getSessionServerUser());
		if (!Constant.PriceScoreMap.containsKey(String.valueOf(llOrderBean.getPrice()))) {
			this.ajaxWrite(new AjaxBean(false, "提交数据有误，请重试"));
	        return null;
		}
		llOrderBean.setNum(Constant.PriceScoreMap.get(String.valueOf(llOrderBean.getPrice())));
		try {
			String fileSuffix = ResourceTools.getFileSuffix(llOrderBean.getPayImgFileFileName());
			if(!ResourceTools.checkSuffix(fileSuffix, ResourceTools.getImageSuffixs())) {
				this.alertAndGoBack("请选择正确的图片");
				return null;
			}
			String fileDirPath = Constant.TOMCAT_SERVICE_ADDRESS + Constant.UPLOAD_IMAGE_PATH;
			String fileName = FileUtil.uploadFile(llOrderBean.getPayImgFile(), fileDirPath, llOrderBean.getPayImgFileFileName());
			llOrderBean.setPayImgUrl(Constant.UPLOAD_IMAGE_PATH + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			this.alertAndRedirect("上传图片失败", "web/shop/accountmanage/dealmanage/get_score");
			return null;
		}
		llScoreOrderService.add(llOrderBean);
		this.alertAndRedirect("订单提交成功", "web/shop/accountmanage/dealmanage/get_score");
        return null;
	}

}
