package com.foal.liuliang.web.shop.accountmanage.dealmanage;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.jersey.resource.tools.ResourceTools;
import com.foal.liuliang.service.LLVIPOrderService;
import com.foal.liuliang.util.FileUtil;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-shop") })
public class VIPAction extends UserBaseAction implements ModelDriven<LLDealOrderBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1082183931451271908L;

	private LLDealOrderBean llOrderBean = new LLDealOrderBean();

	@Autowired
	private LLVIPOrderService llVIPOrderService;
	
	public LLDealOrderBean getModel() {
		return llOrderBean;
	}

	@Action("renewalvip")
	public String renewalvip() {
		this.setAttrToRequest("nowDate", new Date());
		return SUCCESS;
	}
	
	@Action("add_vip_order")
	public String add() {
		llOrderBean.setOperator(this.getSessionServerUser());
		if (!Constant.VIPTimePriceMap.containsKey(String.valueOf(llOrderBean.getNum()))) {
			this.ajaxWrite(new AjaxBean(false, "找不到该续费月数对应的价格，请联系管理员"));
	        return null;
		}
		llOrderBean.setPrice((int)(Constant.VIPTimePriceMap.get(String.valueOf(llOrderBean.getNum()))));
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
			this.alertAndRedirect("上传图片失败", "web/shop/accountmanage/dealmanage/renewalvip");
			return null;
		}
		llVIPOrderService.add(llOrderBean);
		this.alertAndRedirect("订单提交成功，请耐心等待审核", "web/shop/accountmanage/dealmanage/renewalvip");
        return null;
	}

	@Action("index_vip_order")
    public String indexVipOrder() {
        return SUCCESS;
    }
	
	@Action("list_vip_order")
    public String list() {
		llOrderBean.setUserId(this.getSessionServerUser().getUserId());
		PageBean pageBean = this.llVIPOrderService.queryLLScoreOrder(llOrderBean);
        this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }
}
