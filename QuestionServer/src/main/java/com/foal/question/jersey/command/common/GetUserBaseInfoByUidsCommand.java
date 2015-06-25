package com.foal.question.jersey.command.common;

import com.foal.question.config.Constant;
import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppUserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GetUserBaseInfoByUidsCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		JsonArray targetUids = param.getJsonArray("targetUids");
		if (targetUids.size() == 0) {
			throw new QuestionException(QuestionException.UserNotExist, "目标用户列表不能为空");
		}
		JsonArray userBaseInfoJa = new JsonArray();
		for (JsonElement je : targetUids) {
			String targetUid = je.getAsString();
			AppUser targetUser = appUserService.getAppUserById(targetUid);
			if (targetUser == null) {
				continue;
			}
			JsonObject userBaseInfo = new JsonObject();
			userBaseInfo.addProperty("uid", targetUser.getUid());
			userBaseInfo.addProperty("name", targetUser.getName());
			if (targetUser.getUserType() == AppUser.UserType.Local) {
				userBaseInfo.addProperty("figureurl", Constant.CONTEXT_WEB_URL + targetUser.getFigureurl());
			} else {
				userBaseInfo.addProperty("figureurl", targetUser.getFigureurl());
			}
			userBaseInfoJa.add(userBaseInfo);
		}
		ret.add("userBaseInfos", userBaseInfoJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
