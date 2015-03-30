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
import com.foal.question.pojo.AppTextImage;
import com.foal.question.service.app.AppTextImageService;
import com.foal.question.util.StringTools;
import com.google.gson.JsonArray;

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
	
	@POST
	@Path(value = "/add")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String addTextImage(@Context HttpServletRequest request) {
		ResultMap ret = ResultMap.getResultMap();
		ret.setResult(RetCode.Faild);
		try {
			MultipartFormParam param = ResourceTools.getMultipartFormParam(request);
			String uid = param.getFieldParam().get("uid");
			String content = param.getFieldParam().get("content");
			if (!ResourceTools.checkUid(uid)) {
				ret.setResult(RetCode.Faild, "用户uid不存在");
				return ret.toJson();
			}
			String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);
			AppTextImage textImage = new AppTextImage();
			textImage.setContent(content);
			textImage.setOwnerId(uid);
			textImage.setImageUrl(imageUrl);
			textImage.setCreateTime(new Date());
			textImage.setPraiseCount(0);
			appTextImageService.addAppTextImage(textImage);
			ret.add("text_image", textImage.toJson(false));
			ret.setResult(RetCode.Success);
		} catch (Exception e) {
			logger.error("发送文字图片失败", e);
		}
		return ret.toJson();
	}
	/**
	 * 
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
		if (!ResourceTools.checkUid(uid)) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		List<AppTextImage> textImageList = appTextImageService.getAppTextImageByOwner(uid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextImage textImage : textImageList) {
			retJa.add(textImage.toJson(appTextImageService.hasPraised(textImage.getId(), uid)));
		}
		ret.add("text_images", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	/**
	 * 
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
		List<AppTextImage> textImageList = appTextImageService.getPublicAppTextImage(orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextImage textImage : textImageList) {
			retJa.add(textImage.toJson(appTextImageService.hasPraised(textImage.getId(), uid)));
		}
		ret.add("text_images", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	@GET
	@Path(value = "/praise")
	@Produces( { MediaType.TEXT_HTML })
	public String praise(@QueryParam(value = "uid") String uid, @QueryParam(value = "record_id") int recordId) {
		ResultMap ret = ResultMap.getResultMap();
		if (!ResourceTools.checkUid(uid)) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppTextImage textImage = appTextImageService.getAppTextImage(recordId);
		if (textImage == null) {
			ret.setResult(RetCode.Faild, "要点赞的记录不存在");
			return ret.toJson();
		}
		appTextImageService.praise(textImage, uid);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	@GET
	@Path(value = "/del")
	@Produces( { MediaType.TEXT_HTML })
	public String del(@QueryParam(value = "uid") String uid, @QueryParam(value = "record_id") int recordId) {
		ResultMap ret = ResultMap.getResultMap();
		if (!ResourceTools.checkUid(uid)) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppTextImage textImage = appTextImageService.getAppTextImage(recordId);
		if (textImage == null) {
			ret.setResult(RetCode.Faild, "要删除的记录不存在");
			return ret.toJson();
		}
		if (!StringTools.equalsStr(textImage.getOwnerId(), uid)) {
			ret.setResult(RetCode.Faild, "该记录不属于你，不能进行删除");
			return ret.toJson();
		}
		appTextImageService.deleteAppTextImage(textImage);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
}
