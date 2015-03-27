package com.foal.question.jersey.resource.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foal.question.config.Constant;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppTextImageOpLog;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.app.AppTextImageService;
import com.foal.question.util.FileTools;
import com.foal.question.util.GsonTools;
import com.foal.question.util.StringTools;
import com.google.gson.JsonArray;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@SuppressWarnings("unchecked")
@Path("/text_image")
public class TextImageResource extends BaseResource {
	
	private static final Logger logger = Logger.getLogger(TextImageResource.class);
	
	@Autowired
	AppTextImageService appTextImageService;
	
	@POST
	@Path(value = "/add")
	@Produces( { MediaType.TEXT_HTML })
	public String addTextImage(@Context HttpServletRequest request) {
		ResultMap ret = ResultMap.getResultMap();
		ret.setResult(RetCode.Faild);
		try {
			String uid = request.getParameter("uid");
			String content = request.getParameter("content");
			String imageName = request.getParameter("image_name");
			String imageUrl = "";
			if (!checkUid(uid)) {
				ret.setResult(RetCode.Faild, "用户uid不存在");
				return ret.toJson();
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			File tmpDir = new File(Constant.TOMCAT_SERVICE_ADDRESS + Constant.UPLOAD_TEMP_PATH);
			if (!tmpDir.isDirectory()) {
				tmpDir.mkdir();
			}
			factory.setRepository(tmpDir);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(Constant.FILE_MAX_SIZE);
			List items = new ArrayList();
			items = upload.parseRequest(request);

			Iterator<?> it = items.iterator();
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				if (fileItem.isFormField()) {
					continue;
				} else {
					String fileName = fileItem.getName();
					if (StringTools.isNotBlank(fileName) && fileName.equals(imageName) && fileItem.getSize() != 0) {
						String fileSuffix = FileTools.getFileSuffix(fileName);
						if (FileTools.isImageSuffix(fileSuffix)) {
							String imagePath = Constant.UPLOAD_IMAGE_PATH + FileTools.getUploadFileName(fileSuffix);
							File newFile = new File(Constant.TOMCAT_SERVICE_ADDRESS + Constant.UPLOAD_IMAGE_PATH + FileTools.getUploadFileName(fileSuffix));
							fileItem.write(newFile);
							imageUrl = imagePath;
						}
						break;
					}
				}
			}
			AppTextImage textImage = new AppTextImage();
			textImage.setContent(content);
			textImage.setOwnerId(uid);
			textImage.setImageUrl(imageUrl);
			textImage.setCreateTime(new Date());
			textImage.setPositiveCount(0);
			appTextImageService.addAppTextImage(textImage);
			ret.add("text_image", GsonTools.parseJsonObject(textImage));
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
	public String getByOwner(@QueryParam(value = "uid") String uid, @QueryParam(value = "order_by") int orderBy, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		if (!checkUid(uid)) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		List<AppTextImage> textImageList = appTextImageService.getAppTextImageByOwner(uid, orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextImage textImage : textImageList) {
			retJa.add(textImage.toJson());
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
	public String addTextImage(@QueryParam(value = "order_by") int orderBy, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		List<AppTextImage> textImageList = appTextImageService.getPublicAppTextImage(orderBy, page, pageSize);
		JsonArray retJa = new JsonArray();
		for (AppTextImage textImage : textImageList) {
			retJa.add(textImage.toJson());
		}
		ret.add("text_images", retJa);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	@GET
	@Path(value = "/positive")
	@Produces( { MediaType.TEXT_HTML })
	public String positive(@QueryParam(value = "uid") String uid, @QueryParam(value = "text_image_id") int textImageId) {
		ResultMap ret = ResultMap.getResultMap();
		if (!checkUid(uid)) {
			ret.setResult(RetCode.Faild, "用户uid不存在");
			return ret.toJson();
		}
		AppTextImageOpLog opLog = appTextImageService.getOpLog(textImageId, uid);
		if (opLog != null) {
			ret.setResult(RetCode.Faild, "已经对该条记录点过赞");
			return ret.toJson();
		}
		AppTextImage textImage = appTextImageService.getAppTextImage(textImageId);
		if (textImage == null) {
			ret.setResult(RetCode.Faild, "要点赞的记录不存在");
			return ret.toJson();
		}
		appTextImageService.incPositiveCount(textImage, uid);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	private boolean checkUid(String uid) {
		AppUser user = appUserService.getAppUserById(uid);
		if (user == null) {
			return false;
		}
		return true;
	}
}
