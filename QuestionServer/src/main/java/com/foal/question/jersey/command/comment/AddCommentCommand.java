package com.foal.question.jersey.command.comment;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppComment;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppCommentService;
import com.foal.question.service.app.AppTextImageService;
import com.foal.question.service.app.AppTextVoiceService;

public class AddCommentCommand implements ICommand {

	private AppCommentService appCommentService = ServiceLocator.getBean(AppCommentService.class);
	private AppTextImageService appTextImageService = ServiceLocator.getBean(AppTextImageService.class);
	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);
	private RiskWordService riskWordService = ServiceLocator.getBean(RiskWordService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int type = param.getInt("type");
		int recordId = param.getInt("recordId");
		String content = param.get("content", "");
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		if (type == AppComment.Type.TextImageComment) {
			AppTextImage record = appTextImageService.getRecord(recordId);
			if (record == null) {
				throw new QuestionException(QuestionException.RecordNotExist, "要评论的记录不存在");
			}
		} else if (type == AppComment.Type.TextVoiceComment) {
			AppTextVoice record = appTextVoiceService.getRecord(recordId);
			if (record == null) {
				throw new QuestionException(QuestionException.RecordNotExist, "要评论的记录不存在");
			}
		} else {
			throw new QuestionException(QuestionException.RecordNotExist, "要评论的记录不存在");
		}
		if(riskWordService.containRiskWord(content)) {
			throw new QuestionException(QuestionException.ContentHasRiskWord, "您输入的文字包含敏感内容");
		}
		appCommentService.addComment(user, type, recordId, content);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
