package com.foal.question.service.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppTextImagePraiseLog;
import com.foal.question.pojo.AppTextImageShareLog;
import com.foal.question.pojo.AppUser;
import com.foal.question.util.StringTools;

@SuppressWarnings("unchecked")
@Service(value = "appTextImageService")
public class AppTextImageService extends DaoSupport {

	@Autowired
	private AppUserService appUserService;
	
	public AppUserService getAppUserService() {
		return appUserService;
	}
	
	public boolean addRecord(AppTextImage record) {
		this.hibernateDao.save(record);
		return true;
	}

	public AppTextImage getRecord(int id) {
		return this.hibernateDao.get(AppTextImage.class, id);
	}
	
	public void praise(AppTextImage record, String uid) {
		AppTextImagePraiseLog opLog = getPraiseLog(record.getId(), uid);
		if (opLog == null) {
			opLog = new AppTextImagePraiseLog();
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

	private AppTextImagePraiseLog getPraiseLog(int recordId, String uid) {
		return this.hibernateDao.get(AppTextImagePraiseLog.class, recordId + uid);
	}

	public boolean hasPraised(int id, String uid) {
		if (StringTools.isBlank(uid)) {
			return false;
		}
		AppTextImagePraiseLog opLog = getPraiseLog(id, uid);
		return opLog != null && opLog.hasPraised();
	}
	
	public void share(AppTextImage record, String uid) {
		AppTextImageShareLog opLog = getShareLog(record.getId(), uid);
		if (opLog == null) {
			opLog = new AppTextImageShareLog();
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

	private AppTextImageShareLog getShareLog(int recordId, String uid) {
		return this.hibernateDao.get(AppTextImageShareLog.class, recordId + uid);
	}

	public boolean hasShared(int id, String uid) {
		if (StringTools.isBlank(uid)) {
			return false;
		}
		AppTextImageShareLog opLog = getShareLog(id, uid);
		return opLog != null && opLog.hasPraised();
	}

	public boolean deleteRecord(AppTextImage record) {
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
	public List<AppTextImage> getRecordByOwner(String ownerId, int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextImage v where v.ownerId = ? order by v.createTime desc";
		} else {
			queryHql = "from AppTextImage v where v.ownerId = ? order by v.praiseCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize, ownerId);
	}

	/**
	 * 获取公共列表
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppTextImage> getPublicRecord(int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextImage v order by v.createTime desc";
		} else {
			queryHql= "from AppTextImage v order by v.praiseCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize);
	}

	/**
	 * 获取分享的列表（按时间，最新的在前面）
	 * @param ownerId	分享者uid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppTextImage> getShareRecordByOwner(String ownerId, int page, int pageSize) {
		String queryHql = "from AppTextImageShareLog v where v.uid = ? and status > 0 order by v.opTime desc";
		List<AppTextImageShareLog> logList =  this.hibernateDao.queryList(queryHql, page, pageSize, ownerId);
		List<AppTextImage> recordList = new ArrayList<AppTextImage>();
		for (AppTextImageShareLog log : logList) {
			recordList.add(getRecord(log.getRecordId()));
		}
		return recordList;
	}
	/**
	 * 获取对某条记录点过赞的用户列表（按时间，最新的在前面）
	 * @param recordId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppUser> getPraiseUsers(int recordId, int page, int pageSize) {
		String queryHql = "from AppTextImagePraiseLog v where recordId = ? and status > 0 order by v.opTime desc";
		List<AppTextImagePraiseLog> logList =  this.hibernateDao.queryList(queryHql, page, pageSize, recordId);
		List<AppUser> userList = new ArrayList<AppUser>();
		for (AppTextImagePraiseLog log : logList) {
			userList.add(appUserService.getAppUserById(log.getUid()));
		}
		return userList;
	}
}
