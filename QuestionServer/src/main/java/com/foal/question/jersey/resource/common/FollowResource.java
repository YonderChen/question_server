package com.foal.question.jersey.resource.common;

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
import com.foal.question.service.app.AppCommonService;
import com.foal.question.service.app.AppUserService;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
@Path("/follow")
public class FollowResource {
	
	@Autowired
	AppCommonService appCommonService;
	
	@Autowired
	AppUserService appUserService;
	
	@POST
	@Path("/add")
	@Produces( { MediaType.TEXT_HTML })
	public String getVersion(@FormParam(value = "uid") String uid, @FormParam(value = "target_uid") String targetUid) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser follower = appUserService.getAppUserById(uid);
		if (follower == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		AppUser owner = appUserService.getAppUserById(targetUid);
		if (owner == null) {
			ret.setResult(RetCode.Faild, "目标用户不存在");
			return ret.toJson();
		}
		if (!appUserService.hasFollow(uid, targetUid)) {
			appUserService.follow(follower, owner);
		}
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}

	@POST
	@Path(value = "/list")
	@Produces( { MediaType.TEXT_HTML })
	public String register(@FormParam(value = "uid") String uid) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser owner = appUserService.getAppUserById(uid);
		if (owner == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		
		return ret.toJson();
	}
	
}
