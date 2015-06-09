package com.foal.question.jersey.command.textvoice;

import java.util.Date;

import com.foal.question.config.Constant;
import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResourceTools;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppTextVoiceService;
import com.google.gson.JsonObject;

public class TextVoiceAddCommand implements ICommand {
	
	private AppTextVoiceService appTextVoiceService = ServiceLocator.getBean(AppTextVoiceService.class);
	private RiskWordService riskWordService = ServiceLocator.getBean(RiskWordService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		String uid = param.getUid();
		String content = param.get("content", "");
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			throw new QuestionException(QuestionException.LoginInfoError, "登录信息异常，请重新登录");
		}
		if (user.getStatus() == AppUser.Status.Freeze) {
			throw new QuestionException(QuestionException.AccountIsFreeze, "该帐号已经被封，请联系管理人员");
		}
		if (user.getStatus() == AppUser.Status.Silenced) {
			throw new QuestionException(QuestionException.AccountIsSilenced, "该帐号已经被禁言，请联系管理人员");
		}
		if(riskWordService.containRiskWord(content)) {
			throw new QuestionException(QuestionException.ContentHasRiskWord, "您输入的文字包含敏感内容");
		}
		String voiceUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getVoiceSuffixs(), Constant.UPLOAD_VOICE_PATH);
		AppTextVoice record = new AppTextVoice();
		record.setContent(content);
		record.setOwner(user);
		record.setVoiceUrl(voiceUrl);
		//获取图片信息
		JsonObject voiceInfo = new JsonObject();
		voiceInfo.addProperty("voiceLength", param.getInt("voiceLength", 0));
		record.setVoiceInfo(voiceInfo.toString());
		
		record.setCreateTime(new Date());
		record.setPraiseCount(0);
		record.setShareCount(0);
		appTextVoiceService.addRecord(record);
		ret.add("text_voice", record.toJson(false, 0));
		ret.setResult(RetCode.Success);
		return ret;
	}

}
