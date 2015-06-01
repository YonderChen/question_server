package com.foal.liuliang.bean;

import java.util.Date;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLLiuliangBean extends Page {

	private String keyword;
	private String taskId;
	private String bindName;
	private String username;
	private Date beginTime;
	private Date endTime;
	private String doStatus;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getBindName() {
		return bindName;
	}
	public void setBindName(String bindName) {
		this.bindName = bindName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getDoStatus() {
		return doStatus;
	}
	public void setDoStatus(String doStatus) {
		this.doStatus = doStatus;
	}
    
}
