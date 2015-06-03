package com.foal.question.jersey.command.textvoice;

import java.util.List;

import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.service.app.AppTextVoiceService;
import com.google.gson.JsonArray;

public class TextVoiceLoadPublicCommand implements ICommand {

	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.get("uid");
		int orderBy = param.getInt("orderBy");
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		List<AppTextVoice> recordList = appTextVoiceService.getPublicRecord(orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(appTextVoiceService.getRetRecordJson(record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
