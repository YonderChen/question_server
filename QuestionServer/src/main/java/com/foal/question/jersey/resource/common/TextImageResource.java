package com.foal.question.jersey.resource.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
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
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.app.AppTextImageService;
import com.foal.question.util.StringTools;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@Path("/text_image")
public class TextImageResource {
	
	private static final Logger logger = Logger.getLogger(TextImageResource.class);

	@Autowired
	AppTextImageService appTextImageService;
	
	@Autowired
	RiskWordService riskWordService;
	
	@POST
	@Path(value = "/add")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String addTextImage(@Context HttpServletRequest request) {
		ResultMap ret = ResultMap.getResultMap();
		try {
			MultipartFormParam param = ResourceTools.getMultipartFormParam(request);
			String uid = param.getField("uid", "");
			String content = param.getField("content", "");
			AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
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
			String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);
			AppTextImage record = new AppTextImage();
			record.setContent(content);
			record.setOwner(user);
			record.setImageUrl(imageUrl);
			//获取图片信息
			int width = 0;
			int height = 0;
			try {
				BufferedImage image = ImageIO.read(new File(Constant.TOMCAT_SERVICE_ADDRESS + imageUrl));
				width = image.getWidth();
				height = image.getHeight();
			} catch (Exception e) {}
			JsonObject imageInfo = new JsonObject();
			imageInfo.addProperty("width", width);
			imageInfo.addProperty("height", height);
			record.setImageInfo(imageInfo.toString());
			
			record.setCreateTime(new Date());
			record.setPraiseCount(0);
			record.setShareCount(0);
			appTextImageService.addRecord(record);
			ret.add("text_image", record.toJson(false));
			ret.setResult(RetCode.Success);
		} catch (Exception e) {
			logger.error("发送文字图片失败", e);
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
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		List<AppTextImage> recordList = appTextImageService.getRecordByOwner(uid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextImage record : recordList) {
			retJa.add(getRetRecordJson(record, uid));
		}
		ret.add("text_images", retJa);
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
		AppUser targetUser = appTextImageService.getAppUserService().getAppUserById(targetUid);
		if (targetUser == null) {
			ret.setResult(RetCode.Faild, "目标用户不存在");
			return ret.toJson();
		}
		List<AppTextImage> recordList = appTextImageService.getRecordByOwner(targetUid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextImage record : recordList) {
			retJa.add(getRetRecordJson(record, uid));
		}
		ret.add("text_images", retJa);
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
		List<AppTextImage> recordList = appTextImageService.getPublicRecord(orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextImage record : recordList) {
			retJa.add(getRetRecordJson(record, uid));
		}
		ret.add("text_images", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	private JsonObject getRetRecordJson(AppTextImage record, String uid) {
		boolean hasPraised = appTextImageService.hasPraised(record.getId(), uid);
		return record.toJson(hasPraised);
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
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		AppTextImage record = appTextImageService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要点赞的记录不存在");
			return ret.toJson();
		}
		appTextImageService.praise(record, uid);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 删除
	 * @param uid
	 * @param recordId
	 * @return
	 */
	@GET
	@Path(value = "/del")
	@Produces( { MediaType.TEXT_HTML })
	public String del(@QueryParam(value = "uid") String uid, @QueryParam(value = "record_id") int recordId) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		AppTextImage record = appTextImageService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要删除的记录不存在");
			return ret.toJson();
		}
		if (!StringTools.equalsStr(record.getOwner().getUid(), uid)) {
			ret.setResult(RetCode.Faild, "该记录不属于你，不能进行删除");
			return ret.toJson();
		}
		appTextImageService.deleteRecord(record);
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
		AppUser user = appTextImageService.getAppUserService().getAppUserById(uid);
		if (user == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		AppTextImage record = appTextImageService.getRecord(recordId);
		if (record == null) {
			ret.setResult(RetCode.Faild, "要分享的记录不存在");
			return ret.toJson();
		}
		appTextImageService.share(record, uid);
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
		List<AppUser> userList = appTextImageService.getPraiseUsers(recordId, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppUser user : userList) {
			retJa.add(user.toJson());
		}
		ret.add("users", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
}
