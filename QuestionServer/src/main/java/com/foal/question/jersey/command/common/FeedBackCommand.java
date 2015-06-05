package com.foal.question.jersey.command.common;

import java.util.Date;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppFeedback;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppCommonService;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.StringTools;

public class FeedBackCommand implements ICommand {

	private AppCommonService appCommonService = ServiceLocator.getBean(AppCommonService.class);
	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);
	private RiskWordService riskWordService = ServiceLocator.getBean(RiskWordService.class);
	
	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String content = param.get("content");
		String uid = param.get("uid");
		if (StringTools.isBlank(content)) {
			throw new QuestionException(QuestionException.ContentIsEmpty, "反馈内容不能为空");
		}
		if (appUserService.getAppUserById(uid) == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		if(riskWordService.containRiskWord(content)) {
			throw new QuestionException(QuestionException.ContentHasRiskWord, "您输入的文字包含敏感内容");
		}
		AppFeedback feedback = new AppFeedback();
		feedback.setContent(content);
		feedback.setUid(uid);
		feedback.setCreateTime(new Date());
		appCommonService.addEntity(feedback);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
