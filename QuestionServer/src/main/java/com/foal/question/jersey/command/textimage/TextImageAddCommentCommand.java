package com.foal.question.jersey.command.textimage;

import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppTextImageService;

public class TextImageAddCommentCommand implements ICommand {
	
	private AppTextImageService appTextImageService = ServiceLocator.getBean(AppTextImageService.class);
	private RiskWordService riskWordService = ServiceLocator.getBean(RiskWordService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.get("uid", "");
		int recordId = param.getInt("recordId");
		String content = param.get("content", "");
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
		}
		AppTextImage record = appTextImageService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要评论的记录不存在");
		}
		if(riskWordService.containRiskWord(content)) {
			ret.setResult(RetCode.Faild, "您输入的文字包含敏感内容");
		}
		appTextImageService.addComment(user, record, content);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
