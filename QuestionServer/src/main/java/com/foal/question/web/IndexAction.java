package com.foal.question.web;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.service.app.AppTextImageService;
import com.foal.question.service.app.AppTextVoiceService;
import com.foal.question.web.admin.AdminBaseAction;

public class IndexAction extends AdminBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5369312737292827903L;

	@Autowired
	AppTextImageService appTextImageService;
	
	@Autowired
	AppTextVoiceService appTextVoiceService;

	@Action("image_share_info")
	public String getImageShareInfo() {
		int recordId = NumberUtils.toInt(getRequest().getParameter("record_id"), 0);
		AppTextImage record = appTextImageService.getRecord(recordId);
		if (record == null) {
			return null;
		}
		this.setAttrToRequest("record", record);
		return SUCCESS;
	}
	
	@Action("voice_share_info")
	public String getVoiceShareInfo() {
		int recordId = NumberUtils.toInt(getRequest().getParameter("record_id"), 0);
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			return null;
		}
		this.setAttrToRequest("record", record);
		return SUCCESS;
	}
	
}
