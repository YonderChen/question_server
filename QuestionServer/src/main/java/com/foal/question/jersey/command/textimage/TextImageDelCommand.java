package com.foal.question.jersey.command.textimage;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppTextImageService;

public class TextImageDelCommand implements ICommand {
	
	private AppTextImageService appTextImageService = ServiceLocator.getBean(AppTextImageService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int recordId = param.getInt("recordId");
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		AppTextImage record = appTextImageService.getRecord(recordId);
		if (record == null) {
			throw new QuestionException(QuestionException.RecordNotExist, "要分享的记录不存在");
		}
		appTextImageService.share(record, uid);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
