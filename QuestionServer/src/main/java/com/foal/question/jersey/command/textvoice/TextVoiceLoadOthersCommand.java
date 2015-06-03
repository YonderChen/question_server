package com.foal.question.jersey.command.textvoice;

import java.util.List;

import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppTextVoiceService;
import com.google.gson.JsonArray;

public class TextVoiceLoadOthersCommand implements ICommand {

	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.get("uid");
		String targetUid = param.get("targetUid");
		int orderBy = param.getInt("orderBy");
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		AppUser targetUser = appTextVoiceService.getAppUserService().getAppUserById(targetUid);
		if (targetUser == null) {
			ret.setResult(RetCode.Faild, "目标用户不存在");
		}
		List<AppTextVoice> recordList = appTextVoiceService.getRecordByOwner(targetUid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(appTextVoiceService.getRetRecordJson(record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
