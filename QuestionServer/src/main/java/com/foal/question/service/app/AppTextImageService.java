package com.foal.question.service.app;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppTextImage;
import com.foal.question.pojo.AppTextImageOpLog;

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
	
	public void incPositiveCount(AppTextImage textImage, String uid) {
		textImage.incPositiveCount();
		this.hibernateDao.update(textImage);
		AppTextImageOpLog opLog = new AppTextImageOpLog();
		opLog.setOpId(textImage.getId() + uid);
		this.hibernateDao.save(opLog);
	}

	public boolean deleteAppTextImage(int id) {
		AppTextImage textImage = getAppTextImage(id);
		this.hibernateDao.delete(textImage);
		return true;
	}

	public AppTextImageOpLog getOpLog(int id, String uid) {
		return this.hibernateDao.get(AppTextImageOpLog.class, id + uid);
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
			queryHql = "from AppTextImage v where v.ownerId = ? order by v.positiveCount desc";
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
			queryHql= "from AppTextImage v order by v.positiveCount desc";
		}
		return this.hibernateDao.queryList(queryHql, page, pageSize);
	}

}
