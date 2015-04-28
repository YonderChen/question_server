package com.foal.question.jersey.resource.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import com.foal.question.pojo.AppFeedback;
import com.foal.question.pojo.AppTipOff;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.RiskWordService;
import com.foal.question.service.SystemParamService;
import com.foal.question.service.app.AppCommonService;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.MD5Tools;
import com.foal.question.util.StringTools;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@Path("/common")
public class CommonResource {
	
	private static final Logger logger = Logger.getLogger(CommonResource.class);

	@Autowired
	AppCommonService appCommonService;
	
	@Autowired
	AppUserService appUserService;
	
	@Autowired
	SystemParamService systemParamService;
	
	@Autowired
	RiskWordService riskWordService;
	
	@POST
	@Path("/get_version")
	@Produces( { MediaType.TEXT_HTML })
	public String getVersion(@FormParam(value = "version") String version) {
		ResultMap ret = ResultMap.getResultMap();
		String versionNew = systemParamService.getSystemParam("client_version").getValue();
		ret.setResult(RetCode.Success);
		if (!versionNew.equals(version)) {
			ret.add("update", true);
			ret.add("version", versionNew);
			String versionInfo = systemParamService.getSystemParam("client_version_info").getValue();
			ret.add("version_info", versionInfo);
		} else {
			ret.add("update", false);
		}
		return ret.toJson();
	}

	@POST
	@Path(value = "/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String register(@Context HttpServletRequest request) {
		ResultMap ret = ResultMap.getResultMap();
		try {
			MultipartFormParam param = ResourceTools.getMultipartFormParam(request);
			
			String username = param.getField("username", "");
			String password = param.getField("password", "");
			String name = param.getField("name", "");
			String gender = param.getField("gender", "");
			
			if (StringTools.isBlank(username)) {
				ret.setResult(RetCode.Faild, "用户名不能为空");
				return ret.toJson();
			}
			if (StringTools.isBlank(password)) {
				ret.setResult(RetCode.Faild, "密码不能为空");
				return ret.toJson();
			}
			if (username.length() < 6 || username.length() > 50) {
				ret.setResult(RetCode.Faild, "用户名长度只能在6-50字符之间");
				return ret.toJson();
			}
			if (name.length() > 50) {
				ret.setResult(RetCode.Faild, "昵称过长");
				return ret.toJson();
			}
			if (!StringTools.checkNumberOrLetterOrUnderLine(username)) {
				ret.setResult(RetCode.Faild, "用户名只能是字母数字或下划线");
				return ret.toJson();
			}
			if (appUserService.isUsernameExist(username)) {
				ret.setResult(RetCode.Faild, "用户名已存在");
				return ret.toJson();
			}
			Date now = new Date();
			AppUser appUser = new AppUser();
			appUser.setUserType(AppUser.UserType.Local);
			appUser.setUsername(username);
			appUser.setPassword(MD5Tools.hashToMD5(password + Constant.PASSWORD_SECRET_KEY));
			appUser.setName(name);
			appUser.setGender(gender);
			
			String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);

			appUser.setFigureurl(imageUrl);
			appUser.setCreateTime(now);
			appUser.setUpdateAt(now);
			appUser.setStatus(AppUser.Status.Normal);
			appUserService.addAppUser(appUser);
			
			ret.setResult(RetCode.Success);
			ret.add("uid", appUser.getUid());
			ret.add("figureurl", Constant.CONTEXT_WEB_URL + appUser.getFigureurl());
			return ret.toJson();
		} catch (Exception e) {
			logger.error("注册失败", e);
			ret.setResult(RetCode.Faild, "未知错误");
		}
		return ret.toJson();
	}
	
	@POST
	@Path("/login_local")
	@Produces( { MediaType.TEXT_HTML })
	public String login_local(@FormParam(value = "username") String username, @FormParam(value = "password") String password) {

		ResultMap ret = ResultMap.getResultMap();
		if (StringTools.isBlank(username)) {
			ret.setResult(RetCode.Faild, "用户名不能为空");
			return ret.toJson();
		}
		if (!appUserService.isUsernameExist(username)) {
			ret.setResult(RetCode.Faild, "用户名不存在");
			return ret.toJson();
		}
		AppUser appUser = appUserService.getAppUserByUsernameAndPwd(username, password);
		if (appUser == null) {
			ret.setResult(RetCode.Faild, "用户名密码错误");
			return ret.toJson();
		}
		if (appUser.getStatus() == AppUser.Status.Freeze) {
			ret.setResult(RetCode.Faild, "该帐号已经被封，请联系管理人员");
			return ret.toJson();
		}
		ret.setResult(RetCode.Success);
		ret.add("uid", appUser.getUid());
		ret.add("name", appUser.getName());
		ret.add("gender", appUser.getGender());
		ret.add("figureurl", Constant.CONTEXT_WEB_URL + appUser.getFigureurl());
		return ret.toJson();
	}
	
	@POST
	@Path("/login")
	@Produces( { MediaType.TEXT_HTML })
	public String login(@FormParam(value = "openid") String openId, @FormParam(value = "name") String name,
			@FormParam(value = "gender") String gender, @FormParam(value = "figureurl") String figureurl) {
		ResultMap ret = ResultMap.getResultMap();
		if (StringTools.isBlank(openId)) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		int userType = AppUser.UserType.Thrid;
		AppUser appUser = appUserService.getAppUserByOpenId(openId, userType);
		Date now = new Date();
		if (appUser == null) {
			appUser = new AppUser();
			appUser.setUserType(userType);
			appUser.setOpenId(openId);
			appUser.setName(name);
			appUser.setGender(gender);
			appUser.setFigureurl(figureurl);
			appUser.setCreateTime(now);
			appUser.setUpdateAt(now);
			appUser.setStatus(AppUser.Status.Normal);
			appUserService.addAppUser(appUser);
		} else {
			if (appUser.getStatus() == AppUser.Status.Freeze) {
				ret.setResult(RetCode.Faild, "该帐号已经被封，请联系管理人员");
				return ret.toJson();
			}
			appUser.setName(name);
			appUser.setGender(gender);
			appUser.setFigureurl(figureurl);
			appUser.setUpdateAt(now);
			appUserService.updateAppUser(appUser);
		}
		
		ret.setResult(RetCode.Success);
		ret.add("uid", appUser.getUid());
		return ret.toJson();
	}
	
	@POST
	@Path(value = "/edit_password")
	@Produces( { MediaType.TEXT_HTML })
	public String edit_password(@FormParam(value = "uid") String uid, @FormParam(value = "old_password") String oldPassword,
			@FormParam(value = "new_password") String newPassword) {
		ResultMap ret = ResultMap.getResultMap();
		if (StringTools.isBlank(uid)) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		ret = appUserService.editPassword(uid, oldPassword, newPassword);
		return ret.toJson();
	}
	
	@POST
	@Path(value = "/edit_userinfo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String edit_userinfo(@Context HttpServletRequest request) {
		ResultMap ret = ResultMap.getResultMap();
		try {
			MultipartFormParam param = ResourceTools.getMultipartFormParam(request);
			
			String uid = param.getField("uid", "");
			String name = param.getField("name", "");
			String gender = param.getField("gender", "");
			
			if (StringTools.isBlank(uid)) {
				ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
				return ret.toJson();
			}
			if (StringTools.isBlank(name)) {
				ret.setResult(RetCode.Faild, "昵称不能为空");
				return ret.toJson();
			}
			if (name.length() > 50) {
				ret.setResult(RetCode.Faild, "昵称过长");
				return ret.toJson();
			}
			AppUser appUser = appUserService.getAppUserById(uid);
			if (appUserService.getAppUserById(uid) == null) {
				ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
				return ret.toJson();
			}
			Date now = new Date();
			appUser.setName(name);
			appUser.setGender(gender);
			
			if (param.getFileItemList().size() > 0) {
				String imageUrl = ResourceTools.uploadFile(param.getFileItemList(), ResourceTools.getImageSuffixs(), Constant.UPLOAD_IMAGE_PATH);
				if (StringTools.isNotBlank(imageUrl)) {
					appUser.setFigureurl(imageUrl);
				}
			}
			appUser.setCreateTime(now);
			appUser.setUpdateAt(now);
			appUserService.updateAppUser(appUser);
			ret.setResult(RetCode.Success);
			ret.add("figureurl", Constant.CONTEXT_WEB_URL + appUser.getFigureurl());
			return ret.toJson();
		} catch (Exception e) {
			logger.error("修改信息失败", e);
			ret.setResult(RetCode.Faild, "未知错误");
		}
		return ret.toJson();
	}
	
	@POST
	@Path("/tip_off")
	@Produces( { MediaType.TEXT_HTML })
	public String tip_off(@FormParam(value = "type") int type, @FormParam(value = "record_id") int recordId, @FormParam(value = "content") String content, @FormParam(value = "uid") String uid) {
		ResultMap ret = ResultMap.getResultMap();
		if (StringTools.isBlank(content)) {
			ret.setResult(RetCode.Faild, "举报内容不能为空");
			return ret.toJson();
		}
		if (appUserService.getAppUserById(uid) == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		if(riskWordService.containRiskWord(content)) {
			ret.setResult(RetCode.Faild, "您输入的文字包含敏感内容");
			return ret.toJson();
		}
		AppTipOff tipOff = new AppTipOff();
		tipOff.setType(type);
		tipOff.setRecordId(recordId);
		tipOff.setContent(content);
		tipOff.setUid(uid);
		tipOff.setCreateTime(new Date());
		appCommonService.addEntity(tipOff);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	
	@POST
	@Path("/feedback")
	@Produces( { MediaType.TEXT_HTML })
	public String feedback(@FormParam(value = "content") String content, @FormParam(value = "uid") String uid) {
		ResultMap ret = ResultMap.getResultMap();
		if (StringTools.isBlank(content)) {
			ret.setResult(RetCode.Faild, "反馈内容不能为空");
			return ret.toJson();
		}
		if (appUserService.getAppUserById(uid) == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		if(riskWordService.containRiskWord(content)) {
			ret.setResult(RetCode.Faild, "您输入的文字包含敏感内容");
			return ret.toJson();
		}
		AppFeedback feedback = new AppFeedback();
		feedback.setContent(content);
		feedback.setUid(uid);
		feedback.setCreateTime(new Date());
		appCommonService.addEntity(feedback);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
}
