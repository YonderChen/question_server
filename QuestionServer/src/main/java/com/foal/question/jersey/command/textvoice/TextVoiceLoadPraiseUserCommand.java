package com.foal.question.jersey.command.textvoice;

import java.util.List;

import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppTextVoiceService;
import com.google.gson.JsonArray;

public class TextVoiceLoadPraiseUserCommand implements ICommand {
	
	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		int recordId = param.getInt("recordId");
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		List<AppUser> userList = appTextVoiceService.getPraiseUsers(recordId, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppUser user : userList) {
			retJa.add(user.toJson());
		}
		ret.add("users", retJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
