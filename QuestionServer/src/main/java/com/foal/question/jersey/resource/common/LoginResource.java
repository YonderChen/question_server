package com.foal.question.jersey.resource.common;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.foal.question.pojo.AppUser;
import com.google.gson.JsonObject;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@Path("/login")
public class LoginResource extends BaseResource {
	
	@POST
	@Produces( { MediaType.TEXT_HTML })
	public String login(@FormParam(value = "openid") String openId, @FormParam(value = "name") String name,
			@FormParam(value = "gender") String gender, @FormParam(value = "figureurl") String figureurl) {
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
		
		JsonObject ret = new JsonObject();
		ret.addProperty("ret", 0);
		ret.addProperty("uid", appUser.getUid());
		return ret.toString();
	}
}
