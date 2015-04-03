package com.foal.question.jersey.resource.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foal.question.config.Constant;
import com.foal.question.jersey.resource.tools.MultipartFormParam;
import com.foal.question.jersey.resource.tools.ResourceTools;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppTextVoiceService;
import com.foal.question.util.StringTools;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@Path("/text_voice")
public class TextVoiceResource {
	
	private static final Logger logger = Logger.getLogger(TextVoiceResource.class);

	@Autowired
	AppTextVoiceService appTextVoiceService;
	
	@POST
	@Path(value = "/add")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String addTextVoice(@Context HttpServletRequest request) {
		ResultMap ret = ResultMap.getResultMap();
		ret.setResult(RetCode.Faild);
		try {
			MultipartFormParam param = ResourceTools.getMultipartFormParam(request);
			String uid = param.getField("uid", "");
			String content = param.getField("content", "");
			AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
			if (user == null) {
				ret.setResult(RetCode.Faild, "用户uid不存在");
				return ret.toJson();
			}
			String voiceUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getVoiceSuffixs(), Constant.UPLOAD_VOICE_PATH);
			AppTextVoice record = new AppTextVoice();
			record.setContent(content);
			record.setOwnerId(uid);
			record.setVoiceUrl(voiceUrl);
			//获取图片信息
			JsonObject voiceInfo = new JsonObject();
			voiceInfo.addProperty("voiceLength", param.getIntField("voiceLength", 0));
			record.setVoiceInfo(voiceInfo.toString());
			
			record.setCreateTime(new Date());
			record.setPraiseCount(0);
			appTextVoiceService.addRecord(record);
			ret.add("text_voice", record.toJson(user, false, false));
			ret.setResult(RetCode.Success);
		} catch (Exception e) {
			logger.error("发送文字声音失败", e);
		}
		return ret.toJson();
	}
	/**
	 * 加载自己的列表
	 * @param uid
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/load_my")
	@Produces( { MediaType.TEXT_HTML })
	public String loadByOwner(@QueryParam(value = "uid") String uid, @QueryParam(value = "order_by") int orderBy, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		List<AppTextVoice> recordList = appTextVoiceService.getRecordByOwner(uid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(getRetRecordJson(user, record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 加载别人的列表
	 * @param uid
	 * @param targetUid
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/load_others")
	@Produces( { MediaType.TEXT_HTML })
	public String loadByOwner(@QueryParam(value = "uid") String uid, @QueryParam(value = "target_uid") String targetUid, @QueryParam(value = "order_by") int orderBy, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppUser targetUser = appTextVoiceService.getAppUserService().getAppUserById(targetUid);
		if (targetUser == null) {
			ret.setResult(RetCode.Faild, "目标用户uid不存在");
			return ret.toJson();
		}
		List<AppTextVoice> recordList = appTextVoiceService.getRecordByOwner(targetUid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(getRetRecordJson(targetUser, record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 加载全部列表
	 * @param uid
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/load_public")
	@Produces( { MediaType.TEXT_HTML })
	public String loadPublic(@QueryParam(value = "uid") String uid, @QueryParam(value = "order_by") int orderBy, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		List<AppTextVoice> recordList = appTextVoiceService.getPublicRecord(orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			AppUser user = appTextVoiceService.getAppUserService().getAppUserById(record.getOwnerId());
			retJa.add(getRetRecordJson(user, record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	private JsonObject getRetRecordJson(AppUser user, AppTextVoice record, String uid) {
		boolean hasPraised = appTextVoiceService.hasPraised(record.getId(), uid);
		boolean hasShared = appTextVoiceService.hasShared(record.getId(), uid);
		return record.toJson(user, hasPraised, hasShared);
	}

	/**
	 * 点赞
	 * @param uid
	 * @param recordId
	 * @return
	 */
	@GET
	@Path(value = "/praise")
	@Produces( { MediaType.TEXT_HTML })
	public String praise(@QueryParam(value = "uid") String uid, @QueryParam(value = "record_id") int recordId) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要点赞的记录不存在");
			return ret.toJson();
		}
		appTextVoiceService.praise(record, uid);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	@GET
	@Path(value = "/del")
	@Produces( { MediaType.TEXT_HTML })
	public String del(@QueryParam(value = "uid") String uid, @QueryParam(value = "record_id") int recordId) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要删除的记录不存在");
			return ret.toJson();
		}
		if (!StringTools.equalsStr(record.getOwnerId(), uid)) {
			ret.setResult(RetCode.Faild, "该记录不属于你，不能进行删除");
			return ret.toJson();
		}
		appTextVoiceService.deleteRecord(record);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 分享
	 * @param uid
	 * @param recordId
	 * @return
	 */
	@GET
	@Path(value = "/share")
	@Produces( { MediaType.TEXT_HTML })
	public String share(@QueryParam(value = "uid") String uid, @QueryParam(value = "record_id") int recordId) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要分享的记录不存在");
			return ret.toJson();
		}
		appTextVoiceService.share(record, uid);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}

	/**
	 * 加载别人的分享列表
	 * @param uid
	 * @param targetUid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/load_share")
	@Produces( { MediaType.TEXT_HTML })
	public String loadShare(@QueryParam(value = "uid") String uid, @QueryParam(value = "target_uid") String targetUid, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppUser targetUser = appTextVoiceService.getAppUserService().getAppUserById(targetUid);
		if (targetUser == null) {
			ret.setResult(RetCode.Faild, "目标用户uid不存在");
			return ret.toJson();
		}
		List<AppTextVoice> recordList = appTextVoiceService.getShareRecordByOwner(targetUid, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(getRetRecordJson(targetUser, record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}

	/**
	 * 加载某条记录的点赞用户列表
	 * @param recordId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/load_praise_user")
	@Produces( { MediaType.TEXT_HTML })
	public String loadPraiseUsers(@QueryParam(value = "record_id") int recordId, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		List<AppUser> userList = appTextVoiceService.getPraiseUsers(recordId, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppUser user : userList) {
			retJa.add(user.toJson());
		}
		ret.add("users", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
}
