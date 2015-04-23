package com.foal.liuliang.bean;

import java.util.Date;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLTaskRecordBean extends Page {

	private String taskId;
	private String userId;
	private String shopId;
	private String taskType;
	private String goodsName;
	private String status;
    private Date beginTime;
    private Date endTime;
    private String bindPlat;
    private String queryKey;
    private String queryValue;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getBindPlat() {
		return bindPlat;
	}
	public void setBindPlat(String bindPlat) {
		this.bindPlat = bindPlat;
	}
	public String getQueryKey() {
		return queryKey;
	}
	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
    
}
