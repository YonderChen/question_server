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
import com.foal.question.util.StringTools;

public class EditUserInfoCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		String name = param.get("name", "");
		String gender = param.get("gender", "");
		String signature = param.get("signature");
		
		if (StringTools.isBlank(uid)) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		if (StringTools.isBlank(name)) {
			throw new QuestionException(QuestionException.NickNameError, "昵称不能为空");
		}
		if (name.length() > 50) {
			throw new QuestionException(QuestionException.NickNameError, "昵称过长");
		}
		AppUser appUser = appUserService.getAppUserById(uid);
		if (appUserService.getAppUserById(uid) == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		Date now = new Date();
		appUser.setName(name);
		appUser.setGender(gender);
		appUser.setSignature(signature);
		
		if (param.getFileItemList().size() > 0) {
			String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);
			if (StringTools.isNotBlank(imageUrl)) {
				appUser.setFigureurl(imageUrl);
			}
		}
		appUser.setUpdateAt(now);
		appUserService.updateAppUser(appUser);
		ret.setResult(RetCode.Success);
		ret.add("figureurl", Constant.CONTEXT_WEB_URL + appUser.getFigureurl());
		return ret;
	}

}
