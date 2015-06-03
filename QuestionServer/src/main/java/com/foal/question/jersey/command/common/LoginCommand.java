package com.foal.question.jersey.command.common;

import java.util.Date;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.StringTools;

public class LoginCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String openId = param.get("openId");
		String name = param.get("name");
		String gender = param.get("gender");
		String figureurl = param.get("figureurl");
		if (StringTools.isBlank(openId)) {
			throw new QuestionException(QuestionException.UnKnowError, "登录信息异常，请重新登录");
		}
		int userType = AppUser.UserType.Thrid;
		AppUser appUser = appUserService.getAppUserByOpenId(openId, userType);
		Date now = new Date();
		if (appUser == null) {
			appUser = new AppUser();
			appUser.setUserType(userType);
			appUser.setOpenId(openId);
			appUser.setUsername("");
			appUser.setPassword("");
			appUser.setName(name);
			appUser.setGender(gender);
			appUser.setFigureurl(figureurl);
			appUser.setCreateTime(now);
			appUser.setUpdateAt(now);
			appUser.setLastLoginIp(param.getRequest().getRemoteAddr());
			appUser.setStatus(AppUser.Status.Normal);
			appUserService.addAppUser(appUser);
		} else {
			if (appUser.getStatus() == AppUser.Status.Freeze) {
				throw new QuestionException(QuestionException.UnKnowError, "该帐号已经被封，请联系管理人员");
			}
			appUser.setName(name);
			appUser.setGender(gender);
			appUser.setFigureurl(figureurl);
			appUser.setUpdateAt(now);
			appUser.setLastLoginIp(param.getRequest().getRemoteAddr());
			appUserService.updateAppUser(appUser);
		}

		ret.setResult(RetCode.Success);
		ret.add("uid", appUser.getUid());
		return ret;
	}

}
