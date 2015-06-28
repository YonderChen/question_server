package com.foal.question.jersey.command.textvoice;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.service.app.AppTextVoiceService;

public class TextVoiceByRecordIdCommand implements ICommand {
	
	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int recordId = param.getInt("recordId", -1);
		if (recordId < 0) {
			throw new QuestionException(QuestionException.ParamError, "记录id不能为空");
		}
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			throw new QuestionException(QuestionException.RecordNotExist, "记录不存在，可能已被删除");
		}
		ret.add("text_voice", appTextVoiceService.getRetRecordJson(record, uid));
		ret.setResult(RetCode.Success);
		return ret;
	}

}
