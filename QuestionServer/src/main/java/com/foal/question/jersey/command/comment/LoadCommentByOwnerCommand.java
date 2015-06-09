package com.foal.question.jersey.command.comment;

import java.util.List;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppComment;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppCommentService;
import com.foal.question.service.app.AppUserService;
import com.google.gson.JsonArray;

public class LoadCommentByOwnerCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);
	private AppCommentService appCommentService = ServiceLocator.getBean(AppCommentService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		AppUser user = appUserService.getAppUserById(uid);
		if (user == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		List<AppComment> commentList = appCommentService.getRecordCommentByOwner(uid, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppComment comment : commentList) {
			retJa.add(comment.toJson());
		}
		ret.add("comments", retJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
