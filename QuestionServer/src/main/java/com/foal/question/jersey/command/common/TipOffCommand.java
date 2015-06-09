package com.foal.question.jersey.command.common;

import java.util.Date;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTipOff;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppCommonService;
import com.foal.question.util.StringTools;

public class TipOffCommand implements ICommand {

	private RiskWordService riskWordService = ServiceLocator.getBean(RiskWordService.class);
	private AppCommonService appCommonService = ServiceLocator.getBean(AppCommonService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		int type = param.getInt("type");
		int recordId = param.getInt("recordId");
		String content = param.get("content");
		String uid = param.getUid();
		if (StringTools.isBlank(content)) {
			throw new QuestionException(QuestionException.ContentIsEmpty, "举报内容不能为空");
		}
		if (riskWordService.containRiskWord(content)) {
			throw new QuestionException(QuestionException.ContentHasRiskWord, "您输入的文字包含敏感内容");
		}
		AppTipOff tipOff = new AppTipOff();
		tipOff.setType(type);
		tipOff.setRecordId(recordId);
		tipOff.setContent(content);
		tipOff.setUid(uid);
		if (tipOff.getUid() == null) {
			tipOff.setUid("");
		}
		tipOff.setCreateTime(new Date());
		appCommonService.addEntity(tipOff);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
