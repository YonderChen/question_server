package com.foal.liuliang.bean;

import java.util.Date;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLPayRecordBean extends Page {

	private String recordId;
	private String userId;
	private int status = -1;
	private int recordType = 1;
	
	private Date beginTime;
	private Date endTime;
	
	/**
	 * 充值类型
	 * @author cyd
	 * @date 2015-5-15
	 */
	public static class RecordType {
		public static final int Score = 1;//购买积分
		public static final int Vip = 2;//vip续费
	}
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRecordType() {
		return recordType;
	}
	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
