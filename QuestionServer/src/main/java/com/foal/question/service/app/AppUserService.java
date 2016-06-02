package com.foal.question.service.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foal.question.bean.AppUserBean;
import com.foal.question.bean.PageBean;
import com.foal.question.config.Constant;
import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppUser;
import com.foal.question.pojo.AppUserFollow;
import com.foal.question.util.MD5Tools;
import com.foal.question.util.StringTools;
import com.foal.question.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "appUserService")
public class AppUserService extends DaoSupport {

	public boolean addAppUser(AppUser appUser) {
		this.hibernateDao.save(appUser);
		return true;
	}

	public boolean isUsernameExist(String username) {
		String queryHql = "from AppUser as u where u.username = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, username);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public AppUser getAppUserByUsername(String username) {
		String queryHql = "from AppUser as u where u.username = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, username);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public AppUser getAppUserByUsernameAndPwd(String username, String password) {
		String queryHql = "from AppUser as u where u.username = ? and password = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, username, MD5Tools.hashToMD5(password + Constant.PASSWORD_SECRET_KEY));
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public boolean editPassword(AppUser appUser, String oldPassword, String newPassword) {
		if(!appUser.getPassword().equals(MD5Tools.hashToMD5(oldPassword + Constant.PASSWORD_SECRET_KEY))) {
			return false;
		}
		appUser.setPassword(MD5Tools.hashToMD5(newPassword + Constant.PASSWORD_SECRET_KEY));
		Date now = new Date();
		appUser.setUpdateAt(now);
		this.hibernateDao.update(appUser);
		return true;
	}

	public AppUser getAppUserByOpenId(String openId, int userType) {
		String queryHql = "from AppUser as u where u.openId = ? and userType = ?";
		List<AppUser> list = this.hibernateDao.queryList(queryHql, openId, userType);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public AppUser getAppUserById(String id) {
		if (StringTools.isBlank(id)) {
			return null;
		}
		return this.hibernateDao.get(AppUser.class, id);
	}

	public void updateAppUser(AppUser appUser) {
		this.hibernateDao.update(appUser);
	}
	
	public PageBean queryAppUser(AppUserBean appUserBean) {
		String queryHql = "from AppUser as u where 1 = 1";
		Map paramMap = new HashMap();
		if (!StringUtil.isEmpty(appUserBean.getUid())) {
			queryHql += " and u.uid = :uid";
			paramMap.put("uid", appUserBean.getUid());
		}
		if (!StringUtil.isEmpty(appUserBean.getName())) {
			queryHql += " and u.name like :name";
			paramMap.put("name", "%"+appUserBean.getName()+"%");
		}
		int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		queryHql += " order by u.updateAt desc";
		List list = this.hibernateDao.queryList(queryHql, appUserBean.getPage(), appUserBean.getPageSize(), paramMap);
		return new PageBean(list, allRow, appUserBean.getPage(), appUserBean.getPageSize());
	}

	public boolean updateAppUserStatus(AppUserBean appUserBean) {
		AppUser user = this.getAppUserById(appUserBean.getUid());
		if (user == null) {
			return false;
		}
		user.setStatus(appUserBean.getStatus());
		user.setUpdateAt(new Date());
		this.hibernateDao.update(user);
		return true;
	}
	/**
	 * 获取uid关注targetUid的follow对象
	 * @param uid
	 * @param targetUid
	 * @return
	 */
	public AppUserFollow getFollow(String uid, String targetUid) {
		String queryHql = "from AppUserFollow as u where u.owner.uid = ? and u.follower.uid = ?";
		List<AppUserFollow> list = this.hibernateDao.queryList(queryHql, targetUid, uid);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	/**
	 * 判断uid是否关注了targetUid
	 * @param uid
	 * @param targetUid
	 * @return
	 */
	public boolean hasFollow(String uid, String targetUid) {
		return getFollow(uid, targetUid) != null;
	}
	/**
	 * 获取用户关系
	 * @param uid
	 * @param targetUid
	 * @return
	 */
	public int getRelation(String uid, String targetUid) {
		if (StringTools.isBlank(uid)) {
			return AppUser.Relation.None;
		}
		boolean hasFollow = hasFollow(uid, targetUid);
		boolean hasBeFollowed = hasFollow(targetUid, uid);
		if (hasFollow && hasBeFollowed) {
			return AppUser.Relation.Friend;
		} else if (hasFollow && !hasBeFollowed) {
			return AppUser.Relation.Follow;
		} else if (!hasFollow && hasBeFollowed) {
			return AppUser.Relation.BeFollowed;
		} else {
			return AppUser.Relation.None;
		}
	}
	/**
	 * 关注某个用户
	 * @param follower
	 * @param owner
	 */
	public void addFollow(AppUser follower, AppUser owner) {
		AppUserFollow follow = new AppUserFollow();
		follow.setFollower(follower);
		follow.setOwner(owner);
		follow.setCreateTime(new Date());
		AppUserFollow ownerFollow = getFollow(owner.getUid(), follower.getUid());
		if (ownerFollow != null) {
			follow.setStatus(AppUserFollow.FollowStatus.Mutual);
			ownerFollow.setStatus(AppUserFollow.FollowStatus.Mutual);
			this.hibernateDao.update(ownerFollow);
		} else {
			follow.setStatus(AppUserFollow.FollowStatus.Single);
		}
		this.hibernateDao.save(follow);
	}
	/**
	 * 取消关注某个用户
	 * @param follower
	 * @param owner
	 */
	public void delFollow(String uid, String targetUid) {
		AppUserFollow follow = getFollow(uid, targetUid);
		if (follow != null) {
			this.hibernateDao.delete(follow);
			AppUserFollow ownerFollow = getFollow(targetUid, uid);
			if (ownerFollow != null) {
				ownerFollow.setStatus(AppUserFollow.FollowStatus.Single);
				this.hibernateDao.update(ownerFollow);
			}
		}
	}
	public List<AppTextImage> getRecordByOwner(String ownerId, int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextImage v where v.owner.uid = ? order by v.createTime desc";
		} else {
			queryHql = "from AppTextImage v where v.owner.uid = ? order by v.praiseCount desc";
		}
		return this.hibernateDao.queryListByPage(queryHql, page, pageSize, ownerId);
	}
	/**
	 * 获取关注某人的用户列表
	 */
	public List<AppUserFollow> getFollowsByOwner(String ownerId, int page, int pageSize) {
		String queryHql = "from AppUserFollow as u where u.owner.uid = ?";
		List<AppUserFollow> list = this.hibernateDao.queryListByPage(queryHql, page, pageSize, ownerId);
		return list;
	}
	/**
	 * 获取关注某人的用户数量
	 */
	public int getFollowCountByOwner(String ownerId) {
		String queryHql = "select count(*) from AppUserFollow as u where u.owner.uid = ?";
		return this.hibernateDao.getAllRow(queryHql, ownerId);
	}

	/**
	 * 获取新关注自己的用户列表
	 */
	public List<AppUserFollow> updateTimeAndLoadNewFollowsByOwner(String ownerId, int page, int pageSize) {
		AppUser user = getAppUserById(ownerId);
		String queryHql = "from AppUserFollow as u where u.owner.uid = ? and u.createTime > ?";
		List<AppUserFollow> list = this.hibernateDao.queryListByPage(queryHql, page, pageSize, ownerId, user.getLastLoadFollowersTime());
		user.setLastLoadFollowersTime(new Date());
		updateAppUser(user);
		return list;
	}

	/**
	 * 获取新关注自己的用户数量
	 */
	public int getMyNewFollowerCount(String uid) {
		AppUser user = getAppUserById(uid);
		String queryHql = "select count(*) from AppUserFollow as u where u.owner.uid = ? and u.createTime > ?";
		return this.hibernateDao.getAllRow(queryHql, uid, user.getLastLoadFollowersTime());
	}
	
	/**
	 * 获取某人关注的用户列表
	 */
	public List<AppUserFollow> getFollowsByFollower(String followerId, int page, int pageSize) {
		String queryHql = "from AppUserFollow as u where u.follower.uid = ?";
		List<AppUserFollow> list = this.hibernateDao.queryListByPage(queryHql, page, pageSize, followerId);
		return list;
	}
	
	/**
	 * 获取某人关注的用户数量
	 */
	public int getFollowCountByFollower(String followerId) {
		String queryHql = "select count(*) from AppUserFollow as u where u.follower.uid = ?";
		return this.hibernateDao.getAllRow(queryHql, followerId);
	}
	/**
	 * 获取相互关注的（好友）列表
	 */
	public List<AppUserFollow> getFriends(String ownerId, int page, int pageSize) {
		String queryHql = "from AppUserFollow as u where u.owner.uid = ? and u.status = ?";
		List<AppUserFollow> list = this.hibernateDao.queryListByPage(queryHql, page, pageSize, ownerId, AppUserFollow.FollowStatus.Mutual);
		return list;
	}
}
