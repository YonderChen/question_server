package com.foal.question.jersey.command.common;

import java.util.Date;

import com.foal.question.config.Constant;
import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResourceTools;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.MD5Tools;
import com.foal.question.util.StringTools;

public class RegistCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);

	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();

		String username = param.get("username");
		String password = param.get("password");
		String name = param.get("name");
		String gender = param.get("gender");

		if (StringTools.isBlank(username)) {
			throw new QuestionException(QuestionException.UsernameError, "用户名不能为空");
		}
		if (StringTools.isBlank(password)) {
			throw new QuestionException(QuestionException.PasswordIsEmpty, "密码不能为空");
		}
		if (username.length() < 6 || username.length() > 50) {
			throw new QuestionException(QuestionException.UsernameError, "用户名长度只能在6-50字符之间");
		}
		if (name.length() > 50) {
			throw new QuestionException(QuestionException.NickNameError, "昵称过长");
		}
		if (!StringTools.checkNumberOrLetterOrUnderLine(username)) {
			throw new QuestionException(QuestionException.UsernameError, "用户名只能是字母数字或下划线");
		}
		if (appUserService.isUsernameExist(username)) {
			throw new QuestionException(QuestionException.UsernameError, "用户名已存在");
		}
		Date now = new Date();
		AppUser appUser = new AppUser();
		appUser.setUserType(AppUser.UserType.Local);
		appUser.setOpenId("");
		appUser.setUsername(username);
		appUser.setPassword(MD5Tools.hashToMD5(password + Constant.PASSWORD_SECRET_KEY));
		appUser.setName(name);
		appUser.setGender(gender);

		String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);

		appUser.setFigureurl(imageUrl);
		appUser.setCreateTime(now);
		appUser.setUpdateAt(now);
		appUser.setLastLoginIp(param.getRequest().getRemoteAddr());
		appUser.setLastLoadFollowersTime(now);
		appUser.setStatus(AppUser.Status.Normal);
		appUserService.addAppUser(appUser);

		ret.setResult(RetCode.Success);
		ret.add("uid", appUser.getUid());
		ret.add("figureurl", appUser.getRealFigureurl());
		return ret;
	}

}
