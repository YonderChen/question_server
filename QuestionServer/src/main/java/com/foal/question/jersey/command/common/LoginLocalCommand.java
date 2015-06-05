package com.foal.question.jersey.command.common;

import java.util.Date;

import com.foal.question.config.Constant;
import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.MD5Tools;
import com.foal.question.util.StringTools;

public class LoginLocalCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String username = param.get("username");
		String password = param.get("password");

		if (StringTools.isBlank(username)) {
			throw new QuestionException(QuestionException.UsernameError, "用户名不能为空");
		}
		AppUser appUser = appUserService.getAppUserByUsername(username);
		if (appUser == null) {
			throw new QuestionException(QuestionException.UsernameError, "用户名不存在");
		}
		String checkPwd = MD5Tools.hashToMD5(password + Constant.PASSWORD_SECRET_KEY);
		if (!checkPwd.equals(appUser.getPassword())) {
			throw new QuestionException(QuestionException.PasswordError, "用户名密码错误");
		}
		if (appUser.getStatus() == AppUser.Status.Freeze) {
			throw new QuestionException(QuestionException.AccountIsFreeze, "该帐号已经被封，请联系管理人员");
		}
		appUser.setUpdateAt(new Date());
		appUser.setLastLoginIp(param.getRequest().getRemoteAddr());
		appUserService.updateAppUser(appUser);
		ret.setResult(RetCode.Success);
		ret.add("uid", appUser.getUid());
		ret.add("name", appUser.getName());
		ret.add("gender", appUser.getGender());
		ret.add("figureurl", Constant.CONTEXT_WEB_URL + appUser.getFigureurl());
		return ret;
	}

}
