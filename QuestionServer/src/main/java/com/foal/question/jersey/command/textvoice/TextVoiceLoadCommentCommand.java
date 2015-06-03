package com.foal.question.jersey.command.textvoice;

import java.util.List;

import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextVoiceComment;
import com.foal.question.service.app.AppTextVoiceService;
import com.google.gson.JsonArray;

public class TextVoiceLoadCommentCommand implements ICommand {

	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);

	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		int recordId = param.getInt("recordId");
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		List<AppTextVoiceComment> commentList = appTextVoiceService.getRecordComment(recordId, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoiceComment comment : commentList) {
			retJa.add(comment.toJson());
		}
		ret.add("comments", retJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
