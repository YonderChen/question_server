package com.foal.question.jersey.command.shopping;

import com.foal.question.jersey.command.ShoppingCommand;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;

public class LoadGoodsListCommand implements ShoppingCommand {
	
//	private AppTextImageService appTextImageService = ServiceLocator.getBean(AppTextImageService.class);
//	private RiskWordService riskWordService = ServiceLocator.getBean(RiskWordService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
//		String uid = param.getUid();
//		String content = param.get("content", "");
//		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
//		if (user == null) {
//			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
//		}
//		if (user.getStatus() == AppUser.Status.Freeze) {
//			throw new QuestionException(QuestionException.AccountIsFreeze, "该帐号已经被封，请联系管理人员");
//		}
//		if (user.getStatus() == AppUser.Status.Silenced) {
//			throw new QuestionException(QuestionException.AccountIsSilenced, "该帐号已经被禁言，请联系管理人员");
//		}
//		if(riskWordService.containRiskWord(content)) {
//			throw new QuestionException(QuestionException.ContentHasRiskWord, "您输入的文字包含敏感内容");
//		}
//		String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);
//		AppTextImage record = new AppTextImage();
//		record.setContent(content);
//		record.setOwner(user);
//		record.setImageUrl(imageUrl);
//		//获取图片信息
//		int width = 0;
//		int height = 0;
//		try {
//			BufferedImage image = ImageIO.read(new File(Constant.TOMCAT_SERVICE_ADDRESS + imageUrl));
//			width = image.getWidth();
//			height = image.getHeight();
//		} catch (Exception e) {}
//		JsonObject imageInfo = new JsonObject();
//		imageInfo.addProperty("width", width);
//		imageInfo.addProperty("height", height);
//		record.setImageInfo(imageInfo.toString());
//		
//		record.setCreateTime(new Date());
//		record.setPraiseCount(0);
//		record.setShareCount(0);
//		appTextImageService.addRecord(record);
//		ret.add("text_image", record.toJson(false, 0));
		ret.setResult(RetCode.Success);
		return ret;
	}

}
