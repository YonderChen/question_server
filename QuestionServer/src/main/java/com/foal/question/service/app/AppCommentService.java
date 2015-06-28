package com.foal.question.service.app;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppComment;
import com.foal.question.pojo.AppUser;
import com.foal.question.util.StringTools;

@SuppressWarnings("unchecked")
@Service(value = "appCommentService")
public class AppCommentService extends DaoSupport {

	public void addComment(AppUser owner, int type, int recordId, AppUser recordOwner, AppUser toUser, String content) {
		AppComment comment = new AppComment();
		comment.setOwner(owner);
		comment.setType(type);
		comment.setRecordId(recordId);
		comment.setRecordOwner(recordOwner);
		comment.setToUser(toUser);
		comment.setContent(content);
		comment.setCreateTime(new Date());
		if (StringTools.equalsStr(owner.getUid(), recordOwner.getUid())) {
			comment.setStatus(AppComment.Status.Read);
		}
		if (StringTools.equalsStr(owner.getUid(), toUser.getUid())) {
			comment.setToUserStatus(AppComment.Status.Read);
		}
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
	
	public List<AppComment> getRecordCommentByCareUser(String careUserId, int page, int pageSize) {
		String queryHql = "from AppComment v where (v.recordOwner.uid = ? or v.toUser.uid = ?) and v.owner.uid != ? order by v.createTime desc";
		return this.hibernateDao.queryList(queryHql, page, pageSize, careUserId, careUserId, careUserId);
	}
	
	public int getRecordCommentCount(int recordId, int type) {
		String queryHql = "select count(*) from AppComment as r where r.type = ? and r.recordId = ?";
		return this.hibernateDao.getAllRow(queryHql, type, recordId);
	}
	
	public int updateReadAllCommentByCareUser(String careUserId) {
		int updateNum = 0;
		String queryHqlRecordOwner = "update AppComment r set r.status = ? where (r.recordOwner.uid = ? and r.status = ?)";
		updateNum += this.hibernateDao.executeUpdate(queryHqlRecordOwner, AppComment.Status.Read, careUserId, AppComment.Status.NotRead);
		String queryHqlRoUser = "update AppComment r set r.toUserStatus = ? where (r.toUser.uid = ? and r.toUserStatus = ?)";
		updateNum += this.hibernateDao.executeUpdate(queryHqlRoUser, AppComment.Status.Read, careUserId, AppComment.Status.NotRead);
		return updateNum;
	}
	
	public int getNotReadCommentCountByCareUser(String careUserId) {
		String queryHql = "select count(*) from AppComment as r where (r.recordOwner.uid = ? and r.owner.uid != ? and r.status = ?) or (r.toUser.uid = ? and r.toUserStatus = ?)";
		return this.hibernateDao.getAllRow(queryHql, careUserId, careUserId, AppComment.Status.NotRead, careUserId, AppComment.Status.NotRead);
	}
	
	public AppComment getRecordComment(int commentId) {
		return this.hibernateDao.get(AppComment.class, commentId);
	}
	
	public void delRecordComment(AppComment comment) {
		this.hibernateDao.delete(comment);
	}
	
	public void updateRecordComment(AppComment comment) {
		this.hibernateDao.update(comment);
	}
}
