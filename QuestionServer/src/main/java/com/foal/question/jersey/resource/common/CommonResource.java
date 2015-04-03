package com.foal.question.jersey.resource.common;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.pojo.AppUser;
import com.foal.question.service.SystemParamService;
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.StringTools;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@Path("/common")
public class CommonResource {
	
	@Autowired
	AppUserService appUserService;
	
	@Autowired
	SystemParamService systemParamService;
	
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
	@Path("/login")
	@Produces( { MediaType.TEXT_HTML })
	public String login(@FormParam(value = "openid") String openId, @FormParam(value = "name") String name,
			@FormParam(value = "gender") String gender, @FormParam(value = "figureurl") String figureurl) {

		ResultMap ret = ResultMap.getResultMap();
		if (StringTools.isBlank(openId)) {
			ret.setResult(RetCode.Faild, "OpenId不正确");
			return ret.toJson();
		}
		AppUser appUser = appUserService.getAppUserByOpenId(openId);
		Date now = new Date();
		if (appUser == null) {
			appUser = new AppUser();
			appUser.setOpenId(openId);
			appUser.setName(name);
			appUser.setGender(gender);
			appUser.setFigureurl(figureurl);
			appUser.setCreateTime(now);
			appUser.setUpdateAt(now);
			appUserService.addAppUser(appUser);
		} else {
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
}
