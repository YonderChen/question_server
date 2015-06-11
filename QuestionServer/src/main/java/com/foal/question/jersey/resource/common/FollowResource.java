package com.foal.question.jersey.resource.common;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.pojo.AppUser;
import com.foal.question.pojo.AppUserFollow;
import com.foal.question.service.app.AppCommonService;
import com.foal.question.service.app.AppUserService;
import com.google.gson.JsonArray;

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
	
	/**
	 * 加关注
	 * @param uid
	 * @param targetUid
	 * @return
	 */
	@POST
	@Path("/add")
	@Produces( { MediaType.TEXT_HTML })
	public String add(@FormParam(value = "uid") String uid, @FormParam(value = "target_uid") String targetUid) {
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
			appUserService.addFollow(follower, owner);
		}
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}
	/**
	 * 取消关注
	 * @param uid
	 * @param targetUid
	 * @return
	 */
	@POST
	@Path("/del")
	@Produces( { MediaType.TEXT_HTML })
	public String del(@FormParam(value = "uid") String uid, @FormParam(value = "target_uid") String targetUid) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser follower = appUserService.getAppUserById(uid);
		if (follower == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		appUserService.delFollow(uid, targetUid);
		ret.setResult(RetCode.Success);
		return ret.toJson();
	}

	/**
	 * 加载好友（相互关注）
	 * @param uid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/list_friends")
	@Produces( { MediaType.TEXT_HTML })
	public String listFriends(@QueryParam(value = "uid") String uid, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser owner = appUserService.getAppUserById(uid);
		if (owner == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		List<AppUserFollow> followList = appUserService.getFriends(owner.getUid(), page, pageSize);
		JsonArray friends = new JsonArray();
		for (AppUserFollow follow : followList) {
			friends.add(follow.getFollower().toJson());
		}
		ret.add("friends", friends);
		return ret.toJson();
	}

	/**
	 * 加载关注我的
	 * @param uid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/list_followers")
	@Produces( { MediaType.TEXT_HTML })
	public String listFollowers(@QueryParam(value = "uid") String uid, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser owner = appUserService.getAppUserById(uid);
		if (owner == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		List<AppUserFollow> followList = appUserService.getFollowsByOwner(owner.getUid(), page, pageSize);
		JsonArray friends = new JsonArray();
		for (AppUserFollow follow : followList) {
			friends.add(follow.getFollower().toJson());
		}
		ret.add("friends", friends);
		return ret.toJson();
	}

	/**
	 * 加载我的关注
	 * @param uid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path(value = "/list_my_follow")
	@Produces( { MediaType.TEXT_HTML })
	public String listMyFollow(@QueryParam(value = "uid") String uid, @QueryParam(value = "page") int page, @QueryParam(value = "page_size") int pageSize) {
		ResultMap ret = ResultMap.getResultMap();
		AppUser owner = appUserService.getAppUserById(uid);
		if (owner == null) {
			ret.setResult(RetCode.Faild, "登录信息异常，请重新登录");
			return ret.toJson();
		}
		List<AppUserFollow> followList = appUserService.getFollowsByFollower(owner.getUid(), page, pageSize);
		JsonArray friends = new JsonArray();
		for (AppUserFollow follow : followList) {
			friends.add(follow.getOwner().toJson());
		}
		ret.add("friends", friends);
		return ret.toJson();
	}
	
}
