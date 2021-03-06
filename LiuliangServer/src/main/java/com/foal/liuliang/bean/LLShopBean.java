package com.foal.liuliang.bean;

import java.util.Date;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLShopBean extends Page {

	private String shopId;
	private String userId;
	private String bindPlat;
	private String bindName;
	private String shopUrl;
	private String verifyGoodsUrl;
	private String verifyCode;
	private int status;

	private String username;
	private Date beginTime;
	private Date endTime;
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBindPlat() {
		return bindPlat;
	}
	public void setBindPlat(String bindPlat) {
		this.bindPlat = bindPlat;
	}
	public String getBindName() {
		return bindName;
	}
	public void setBindName(String bindName) {
		this.bindName = bindName;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public String getVerifyGoodsUrl() {
		return verifyGoodsUrl;
	}
	public void setVerifyGoodsUrl(String verifyGoodsUrl) {
		this.verifyGoodsUrl = verifyGoodsUrl;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
}
