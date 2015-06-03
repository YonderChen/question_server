package com.foal.question.jersey.command.follow;

import java.util.List;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.pojo.AppUserFollow;
import com.foal.question.service.app.AppUserService;
import com.google.gson.JsonArray;

public class ListFriendCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.get("uid");
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		AppUser owner = appUserService.getAppUserById(uid);
		if (owner == null) {
			throw new QuestionException(QuestionException.UnKnowError, "登录信息异常，请重新登录");
		}
		List<AppUserFollow> followList = appUserService.getFriends(owner.getUid(), page, pageSize);
		JsonArray friends = new JsonArray();
		for (AppUserFollow follow : followList) {
			friends.add(follow.getFollower().toJson());
		}
		ret.add("friends", friends);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
