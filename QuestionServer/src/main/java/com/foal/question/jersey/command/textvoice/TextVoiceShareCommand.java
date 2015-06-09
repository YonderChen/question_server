package com.foal.question.jersey.command.textvoice;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppTextVoiceService;
import com.foal.question.util.StringTools;

public class TextVoiceShareCommand implements ICommand {
	
	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int recordId = param.getInt("recordId");
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			throw new QuestionException(QuestionException.RecordNotExist, "要删除的记录不存在");
		}
		if (!StringTools.equalsStr(record.getOwner().getUid(), uid)) {
			throw new QuestionException(QuestionException.RecordIsNotYours, "该记录不属于你，不能进行删除");
		}
		appTextVoiceService.deleteRecord(record);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
