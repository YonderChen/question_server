package com.foal.question.service.app;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppTextImageOpLog;
import com.foal.question.util.StringTools;

@SuppressWarnings("unchecked")
@Service(value = "appTextImageService")
public class AppTextImageService extends DaoSupport {

	public boolean addAppTextImage(AppTextImage textImage) {
		this.hibernateDao.save(textImage);
		return true;
	}

	public AppTextImage getAppTextImage(int id) {
		return this.hibernateDao.get(AppTextImage.class, id);
	}
	
	public void updateAppTextImage(AppTextImage textImage) {
		this.hibernateDao.update(textImage);
	}
	
	public void praise(AppTextImage textImage, String uid) {
		AppTextImageOpLog opLog = getOpLog(textImage.getId(), uid);
		if (opLog == null) {
			opLog = new AppTextImageOpLog();
			opLog.setOpId(textImage.getId() + uid);
			opLog.setStatus(1);
			textImage.incPraiseCount();
			this.hibernateDao.save(opLog);
		} else {
			if (opLog.hasPraised()) {
				opLog.setStatus(0);
				textImage.decPraiseCount();
				this.hibernateDao.update(opLog);
			} else {
				opLog.setOpId(textImage.getId() + uid);
				opLog.setStatus(1);
				textImage.incPraiseCount();
				this.hibernateDao.update(opLog);
			}
		}
		this.hibernateDao.update(textImage);
	}

	public boolean deleteAppTextImage(AppTextImage textImage) {
		this.hibernateDao.delete(textImage);
		return true;
	}

	private AppTextImageOpLog getOpLog(int id, String uid) {
		return this.hibernateDao.get(AppTextImageOpLog.class, id + uid);
	}

	public boolean hasPraised(int id, String uid) {
		if (StringTools.isBlank(uid)) {
			return false;
		}
		AppTextImageOpLog opLog = getOpLog(id, uid);
		return opLog != null && opLog.hasPraised();
	}

	/**
	 * 
	 * @param ownerId	所有者uid
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppTextImage> getAppTextImageByOwner(String ownerId, int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextImage v where v.ownerId = ? order by v.createTime desc";
		} else {
			queryHql = "from AppTextImage v where v.ownerId = ? order by v.praiseCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize, ownerId);
	}

	/**
	 * 
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AppTextImage> getPublicAppTextImage(int orderBy, int page, int pageSize) {
		String queryHql;
		if (orderBy == 0) {
			queryHql = "from AppTextImage v order by v.createTime desc";
		} else {
			queryHql= "from AppTextImage v order by v.praiseCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize);
	}

}
