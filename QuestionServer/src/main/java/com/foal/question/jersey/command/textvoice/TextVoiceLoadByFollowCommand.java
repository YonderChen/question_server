package com.foal.question.jersey.command.textvoice;

import java.util.List;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppTextVoiceService;
import com.google.gson.JsonArray;

public class TextVoiceLoadByFollowCommand implements ICommand {

	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int orderBy = param.getInt("orderBy");
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		List<AppTextVoice> recordList = appTextVoiceService.getRecordByFollow(uid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(appTextVoiceService.getRetRecordJson(record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
