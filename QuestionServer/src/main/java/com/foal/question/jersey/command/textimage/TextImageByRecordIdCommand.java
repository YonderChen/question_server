package com.foal.question.jersey.command.textimage;

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.service.app.AppTextImageService;

public class TextImageByRecordIdCommand implements ICommand {
	
	private AppTextImageService appTextImageService = ServiceLocator.getBean(AppTextImageService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		int recordId = param.getInt("recordId", -1);
		if (recordId < 0) {
			throw new QuestionException(QuestionException.ParamError, "记录id不能为空");
		}
		AppTextImage record = appTextImageService.getRecord(recordId);
		if (record == null) {
			throw new QuestionException(QuestionException.RecordNotExist, "记录不存在，可能已被删除");
		}
		ret.add("text_image", appTextImageService.getRetRecordJson(record, uid));
		ret.setResult(RetCode.Success);
		return ret;
	}

}
