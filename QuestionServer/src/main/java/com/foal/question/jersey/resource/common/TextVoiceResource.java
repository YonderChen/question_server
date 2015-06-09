package com.foal.question.jersey.resource.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import com.foal.question.pojo.AppComment;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppCommentService;
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
	
	@Autowired
	AppCommentService appCommentService;
	
	@Autowired
	RiskWordService riskWordService;
	
	@POST
	@Path(value = "/add")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String addTextVoice(@Context HttpServletRequest request) {
		ResultMap ret = ResultMap.getResultMap();
		try {
			MultipartFormParam param = ResourceTools.getMultipartFormParam(request);
			String uid = param.getField("uid", "");
			String content = param.getField("content", "");
			AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
			if (user == null) {
				ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
				return ret.toJson();
			}
			if (user.getStatus() == AppUser.Status.Freeze) {
				ret.setResult(RetCode.Faild, "该帐号已经被封，请联系管理人员");
				return ret.toJson();
			}
			if (user.getStatus() == AppUser.Status.Silenced) {
				ret.setResult(RetCode.Faild, "该帐号已经被禁言，请联系管理人员");
				return ret.toJson();
			}
			if(riskWordService.containRiskWord(content)) {
				ret.setResult(RetCode.Faild, "您输入的文字包含敏感内容");
				return ret.toJson();
			}
			String voiceUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getVoiceSuffixs(), Constant.UPLOAD_VOICE_PATH);
			AppTextVoice record = new AppTextVoice();
			record.setContent(content);
			record.setOwner(user);
			record.setVoiceUrl(voiceUrl);
			//获取图片信息
			JsonObject voiceInfo = new JsonObject();
			voiceInfo.addProperty("voiceLength", param.getIntField("voiceLength", 0));
			record.setVoiceInfo(voiceInfo.toString());
			
			record.setCreateTime(new Date());
			record.setPraiseCount(0);
			record.setShareCount(0);
			appTextVoiceService.addRecord(record);
			ret.add("text_voice", record.toJson(false, 0));
			ret.setResult(RetCode.Success);
		} catch (Exception e) {
			logger.error("发送文字声音失败", e);
			ret.setResult(RetCode.Faild, "未知错误");
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
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		List<AppTextVoice> recordList = appTextVoiceService.getRecordByOwner(uid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(getRetRecordJson(record, uid));
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
	public String loadByOthers(@QueryParam(value = "uid") String uid, @QueryParam(value = "target_uid") String targetUid, @QueryParam(value = "order_by") int orderBy, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser targetUser = appTextVoiceService.getAppUserService().getAppUserById(targetUid);
		if (targetUser == null) {
			ret.setResult(RetCode.Faild, "目标用户不存在");
			return ret.toJson();
		}
		List<AppTextVoice> recordList = appTextVoiceService.getRecordByOwner(targetUid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(getRetRecordJson(record, uid));
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
			retJa.add(getRetRecordJson(record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	private JsonObject getRetRecordJson(AppTextVoice record, String uid) {
		return appTextVoiceService.getRetRecordJson(record, uid);
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
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
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
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要删除的记录不存在");
			return ret.toJson();
		}
		if (!StringTools.equalsStr(record.getOwner().getUid(), uid)) {
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
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
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

	/**
	 * 加载关注的人的声音文字列表
	 * @param uid
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/load_by_follow")
	@Produces( { MediaType.TEXT_HTML })
	public String loadByFollow(@QueryParam(value = "uid") String uid, @QueryParam(value = "order_by") int orderBy, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		List<AppTextVoice> recordList = appTextVoiceService.getRecordByFollow(uid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextVoice record : recordList) {
			retJa.add(getRetRecordJson(record, uid));
		}
		ret.add("text_voices", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 添加评论
	 * @param uid
	 * @param recordId
	 * @param content
	 * @return
	 */
	@POST
	@Path(value = "/add_comment")
	@Produces( { MediaType.TEXT_HTML })
	public String addComment(@FormParam(value = "uid") String uid, @FormParam(value = "type") int type, @FormParam(value = "record_id") int recordId, @FormParam(value = "content") String content) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		AppTextVoice record = appTextVoiceService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要评论的记录不存在");
			return ret.toJson();
		}
		appCommentService.addComment(user, type, recordId, content);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 删除评论
	 * @param recordId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/del_comment")
	@Produces( { MediaType.TEXT_HTML })
	public String delComment(@QueryParam(value = "uid") String uid, @QueryParam(value = "comment_id") int commentId) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextVoiceService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		AppComment comment = appCommentService.getRecordComment(commentId);
		if (comment == null) {
			ret.setResult(RetCode.Faild, "要删除的评论不存在");
			return ret.toJson();
		}
		if (!StringTools.equalsStr(comment.getOwner().getUid(),uid)) {
			ret.setResult(RetCode.Faild, "该评论不属于您");
			return ret.toJson();
		}
		appCommentService.delRecordComment(comment);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 加载记录的评论
	 * @param recordId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/load_comment")
	@Produces( { MediaType.TEXT_HTML })
	public String loadComment(@QueryParam(value = "type") int type, @QueryParam(value = "record_id") int recordId, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		List<AppComment> commentList = appCommentService.getRecordComment(type, recordId, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppComment comment : commentList) {
			retJa.add(comment.toJson());
		}
		ret.add("comments", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
}
