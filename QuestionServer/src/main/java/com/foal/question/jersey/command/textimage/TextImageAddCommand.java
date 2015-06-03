package com.foal.question.jersey.command.textimage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import com.foal.question.config.Constant;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResourceTools;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppTextImageService;
import com.google.gson.JsonObject;

public class TextImageAddCommand implements ICommand {
	
	private AppTextImageService appTextImageService = ServiceLocator.getBean(AppTextImageService.class);
	private RiskWordService riskWordService = ServiceLocator.getBean(RiskWordService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.get("uid", "");
		String content = param.get("content", "");
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
		}
		if (user.getStatus() == AppUser.Status.Freeze) {
			ret.setResult(RetCode.Faild, "该帐号已经被封，请联系管理人员");
		}
		if (user.getStatus() == AppUser.Status.Silenced) {
			ret.setResult(RetCode.Faild, "该帐号已经被禁言，请联系管理人员");
		}
		if(riskWordService.containRiskWord(content)) {
			ret.setResult(RetCode.Faild, "您输入的文字包含敏感内容");
		}
		String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);
		AppTextImage record = new AppTextImage();
		record.setContent(content);
		record.setOwner(user);
		record.setImageUrl(imageUrl);
		//获取图片信息
		int width = 0;
		int height = 0;
		try {
			BufferedImage image = ImageIO.read(new File(Constant.TOMCAT_SERVICE_ADDRESS + imageUrl));
			width = image.getWidth();
			height = image.getHeight();
		} catch (Exception e) {}
		JsonObject imageInfo = new JsonObject();
		imageInfo.addProperty("width", width);
		imageInfo.addProperty("height", height);
		record.setImageInfo(imageInfo.toString());
		
		record.setCreateTime(new Date());
		record.setPraiseCount(0);
		record.setShareCount(0);
		appTextImageService.addRecord(record);
		ret.add("text_image", record.toJson(false, 0));
		ret.setResult(RetCode.Success);
		return ret;
	}

}
