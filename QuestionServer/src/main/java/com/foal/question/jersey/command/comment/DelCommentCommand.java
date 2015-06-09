package com.foal.question.jersey.command.comment;

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
import com.foal.question.util.StringTools;

public class DelCommentCommand implements ICommand {

	private AppUserService appUserService = ServiceLocator.getBean(AppUserService.class);
	private AppCommentService appCommentService = ServiceLocator.getBean(AppCommentService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int commentId = param.getInt("commentId");
		AppUser user = appUserService.getAppUserById(uid);
		if (user == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		AppComment comment = appCommentService.getRecordComment(commentId);
		if (comment == null) {
			throw new QuestionException(QuestionException.RecordNotExist, "要删除的评论不存在");
		}
		if (!StringTools.equalsStr(comment.getOwner().getUid(),uid)) {
			throw new QuestionException(QuestionException.RecordIsNotYours, "该评论不属于您");
		}
		appCommentService.delRecordComment(comment);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
