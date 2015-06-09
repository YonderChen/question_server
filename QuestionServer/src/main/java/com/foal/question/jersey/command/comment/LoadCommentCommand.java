package com.foal.question.jersey.command.comment;

import java.util.List;

import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppComment;
import com.foal.question.service.app.AppCommentService;
import com.google.gson.JsonArray;

public class LoadCommentCommand implements ICommand {

	private AppCommentService appCommentService = ServiceLocator.getBean(AppCommentService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		int type = param.getInt("type");
		int recordId = param.getInt("recordId");
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		List<AppComment> commentList = appCommentService.getRecordComment(type, recordId, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppComment comment : commentList) {
			retJa.add(comment.toJson());
		}
		ret.add("comments", retJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
