package com.foal.question.jersey.command.follow;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppUserService;

public class AddFollowCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.get("uid");
		String targetUid = param.get("targetUid");
		AppUser follower = appUserService.getAppUserById(uid);
		if (follower == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		AppUser owner = appUserService.getAppUserById(targetUid);
		if (owner == null) {
			throw new QuestionException(QuestionException.UsernameError, "目标用户不存在");
		}
		if (!appUserService.hasFollow(uid, targetUid)) {
			appUserService.follow(follower, owner);
		}
		ret.setResult(RetCode.Success);
		return ret;
	}

}
