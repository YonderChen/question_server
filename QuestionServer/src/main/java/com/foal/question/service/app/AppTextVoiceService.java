package com.foal.question.service.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foal.question.bean.AppTextVoiceBean;
import com.foal.question.bean.PageBean;
import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppTextVoice;
import com.foal.question.pojo.AppTextVoiceComment;
import com.foal.question.pojo.AppTextVoicePraiseLog;
import com.foal.question.pojo.AppUser;
import com.foal.question.util.StringTools;
import com.foal.question.util.StringUtil;
import com.google.gson.JsonObject;

@SuppressWarnings("unchecked")
@Service(value = "appTextVoiceService")
public class AppTextVoiceService extends DaoSupport {

	@Autowired
	private AppUserService appUserService;
	
	public AppUserService getAppUserService() {
		return appUserService;
	}
	
	public boolean addRecord(AppTextVoice record) {
		this.hibernateDao.save(record);
		return true;
	}

	public AppTextVoice getRecord(int id) {
		return this.hibernateDao.get(AppTextVoice.class, id);
	}
	
	public void praise(AppTextVoice record, String uid) {
		AppTextVoicePraiseLog opLog = getPraiseLog(record.getId(), uid);
		if (opLog == null) {
			opLog = new AppTextVoicePraiseLog();
			opLog.setOpId(record.getId() + uid);
			opLog.setRecordId(record.getId());
			opLog.setUid(uid);
			opLog.setStatus(1);
			opLog.setOpTime(new Date());
			record.incPraiseCount();
			this.hibernateDao.save(opLog);
		} else {
			if (opLog.hasPraised()) {
				opLog.setStatus(0);
				opLog.setOpTime(new Date());
				record.decPraiseCount();
				this.hibernateDao.update(opLog);
			} else {
				opLog.setOpId(record.getId() + uid);
				opLog.setStatus(1);
				opLog.setOpTime(new Date());
				record.incPraiseCount();
				this.hibernateDao.update(opLog);
			}
		}
		this.hibernateDao.update(record);
	}

	private AppTextVoicePraiseLog getPraiseLog(int recordId, String uid) {
		return this.hibernateDao.get(AppTextVoicePraiseLog.class, recordId + uid);
	}

	public boolean hasPraised(int id, String uid) {
		if (StringTools.isBlank(uid)) {
			return false;
		}
		AppTextVoicePraiseLog opLog = getPraiseLog(id, uid);
		return opLog != null && opLog.hasPraised();
	}
	
	public void share(AppTextVoice record, String uid) {
		record.incShareCount();
		this.hibernateDao.update(record);
	}

	public boolean deleteRecord(AppTextVoice record) {
		this.hibernateDao.delete(record);
		return true;
	}

	/**
	 * 根据用户获取列表
	 * @param ownerId	所有者uid
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppTextVoice> getRecordByOwner(String ownerId, int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextVoice v where v.owner.uid = ? order by v.createTime desc";
		} else {
			queryHql = "from AppTextVoice v where v.owner.uid = ? order by v.praiseCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize, ownerId);
	}

	/**
	 * 获取关注用户的记录列表
	 * @param uid	用户uid
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppTextVoice> getRecordByFollow(String uid, int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextVoice v where v.owner.uid in (select uid from AppUserFollow f where f.follower.uid = ?) order by v.createTime desc";
		} else {
			queryHql = "from AppTextVoice v where v.owner.uid in (select uid from AppUserFollow f where f.follower.uid = ?) order by v.praiseCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize, uid);
	}

	/**
	 * 获取公共列表
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppTextVoice> getPublicRecord(int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextVoice v order by v.createTime desc";
		} else {
			queryHql= "from AppTextVoice v order by v.praiseCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize);
	}

	/**
	 * 获取对某条记录点过赞的用户列表（按时间，最新的在前面）
	 * @param recordId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppUser> getPraiseUsers(int recordId, int page, int pageSize) {
		String queryHql = "from AppTextVoicePraiseLog v where recordId = ? order by v.opTime desc";
		List<AppTextVoicePraiseLog> logList =  this.hibernateDao.queryList(queryHql, page, pageSize, recordId);
		List<AppUser> userList = new ArrayList<AppUser>();
		for (AppTextVoicePraiseLog log : logList) {
			userList.add(appUserService.getAppUserById(log.getUid()));
		}
		return userList;
	}

	public PageBean queryTextVoice(AppTextVoiceBean appTextVoiceBean) {
		String queryHql = "from AppTextVoice as r where 1 = 1";
		Map paramMap = new HashMap();
		if (!StringUtil.isEmpty(appTextVoiceBean.getId())) {
			queryHql += " and r.id = :id";
			paramMap.put("id", appTextVoiceBean.getId());
		}
		if (!StringUtil.isEmpty(appTextVoiceBean.getOwnerId())) {
			queryHql += " and r.owner.uid = :ownerId";
			paramMap.put("ownerId", appTextVoiceBean.getOwnerId());
		}
		int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		queryHql += " order by r.createTime desc";
		List list = this.hibernateDao.queryList(queryHql, appTextVoiceBean.getPage(), appTextVoiceBean.getPageSize(), paramMap);
		return new PageBean(list, allRow, appTextVoiceBean.getPage(), appTextVoiceBean.getPageSize());
	}
	
	public void addComment(AppUser owner, AppTextVoice record, String content) {
		AppTextVoiceComment comment = new AppTextVoiceComment();
		comment.setOwner(owner);
		comment.setRecord(record);
		comment.setContent(content);
		comment.setCreateTime(new Date());
		this.hibernateDao.save(comment);
	}
	
	public List<AppTextVoiceComment> getRecordComment(int recordId, int page, int pageSize) {
		String queryHql = "from AppTextVoiceComment v where v.record.id = ? order by v.createTime desc";
		return this.hibernateDao.queryList(queryHql, page, pageSize, recordId);
	}
	
	public int getRecordCommentCount(int recordId) {
		String queryHql = "select count(*) from AppTextVoiceComment as r where r.record.id = ?";
		return this.hibernateDao.getAllRow(queryHql, recordId);
	}
	
	public AppTextVoiceComment getRecordComment(int commentId) {
		return this.hibernateDao.get(AppTextVoiceComment.class, commentId);
	}
	
	public void delRecordComment(AppTextVoiceComment comment) {
		this.hibernateDao.delete(comment);
	}

	public JsonObject getRetRecordJson(AppTextVoice record, String uid) {
		boolean hasPraised = hasPraised(record.getId(), uid);
		return record.toJson(hasPraised, getRecordCommentCount(record.getId()));
	}
}
