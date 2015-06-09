package com.foal.question.jersey.command.common;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.StringTools;

public class EditPasswordCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);
	
	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		String oldPassword = param.get("oldPassword");
		String newPassword = param.get("newPassword");
		if (StringTools.isBlank(uid)) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		AppUser appUser = appUserService.getAppUserById(uid);
		if (appUser == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		boolean isSuccess = appUserService.editPassword(appUser, oldPassword, newPassword);
		if (!isSuccess) {
			throw new QuestionException(QuestionException.PasswordError, "旧密码不正确");
		}
		ret.setResult(RetCode.Success);
		return ret;
	}

}
