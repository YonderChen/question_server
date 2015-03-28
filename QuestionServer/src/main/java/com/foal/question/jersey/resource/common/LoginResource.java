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
import com.foal.question.service.app.AppUserService;
import com.foal.question.util.StringTools;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@Path("/login")
public class LoginResource {
	
	@Autowired
	AppUserService appUserService;
	
	@POST
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
