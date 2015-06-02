package com.foal.question.jersey.command.common;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.StringTools;

public class EditPasswordCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);;
	
	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.get("uid");
		String oldPassword = param.get("old_password");
		String newPassword = param.get("new_password");
		if (StringTools.isBlank(uid)) {
			throw new QuestionException(QuestionException.UnKnowError, "登录信息异常，请重新登录");
		}
		ret = appUserService.editPassword(uid, oldPassword, newPassword);
		return ret;
	}

}
