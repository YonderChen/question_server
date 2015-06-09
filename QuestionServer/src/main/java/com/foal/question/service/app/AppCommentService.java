package com.foal.question.service.app;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppComment;
import com.foal.question.pojo.AppUser;

@SuppressWarnings("unchecked")
@Service(value = "appCommentService")
public class AppCommentService extends DaoSupport {

	public void addComment(AppUser owner, int type, int recordId, String content) {
		AppComment comment = new AppComment();
		comment.setOwner(owner);
		comment.setType(type);
		comment.setRecordId(recordId);
		comment.setContent(content);
		comment.setCreateTime(new Date());
		this.hibernateDao.save(comment);
	}
	
	public List<AppComment> getRecordComment(int type, int recordId, int page, int pageSize) {
		String queryHql = "from AppComment v where v.type = ? and v.recordId = ? order by v.createTime desc";
		return this.hibernateDao.queryList(queryHql, page, pageSize, type, recordId);
	}
	
	public List<AppComment> getRecordCommentByOwner(String ownerId, int page, int pageSize) {
		String queryHql = "from AppComment v where v.owner.uid = ? order by v.createTime desc";
		return this.hibernateDao.queryList(queryHql, page, pageSize, ownerId);
	}
	
	public int getRecordCommentCount(int recordId, int type) {
		String queryHql = "select count(*) from AppComment as r where r.type = ? and r.recordId = ?";
		return this.hibernateDao.getAllRow(queryHql, type, recordId);
	}
	
	public int getNotReadCommentCount(String ownerId) {
		String queryHql = "select count(*) from AppComment as r where r.owner.uid = ? and r.status = ?";
		return this.hibernateDao.getAllRow(queryHql, ownerId, AppComment.Status.NotRead);
	}
	
	public AppComment getRecordComment(int commentId) {
		return this.hibernateDao.get(AppComment.class, commentId);
	}
	
	public void delRecordComment(AppComment comment) {
		this.hibernateDao.delete(comment);
	}
}
