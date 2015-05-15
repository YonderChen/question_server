package com.foal.liuliang.bean;

import java.io.File;
import java.util.Date;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLDealOrderBean extends Page {

	private String orderId;
	private String userId;
	private int num;
	private int price;
	private String payImgUrl;
	private String dealId;
	private String reason;
	private Date payTime;
	private String checkAdminId;
	private int status;
	
	private File payImgFile;
    private String payImgFileFileName;
    private String payImgFileContentType;

	private String username;
	private Date beginTime;
	private Date endTime;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPayImgUrl() {
		return payImgUrl;
	}
	public void setPayImgUrl(String payImgUrl) {
		this.payImgUrl = payImgUrl;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getCheckAdminId() {
		return checkAdminId;
	}
	public void setCheckAdminId(String checkAdminId) {
		this.checkAdminId = checkAdminId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public File getPayImgFile() {
		return payImgFile;
	}
	public void setPayImgFile(File payImgFile) {
		this.payImgFile = payImgFile;
	}
	public String getPayImgFileFileName() {
		return payImgFileFileName;
	}
	public void setPayImgFileFileName(String payImgFileFileName) {
		this.payImgFileFileName = payImgFileFileName;
	}
	public String getPayImgFileContentType() {
		return payImgFileContentType;
	}
	public void setPayImgFileContentType(String payImgFileContentType) {
		this.payImgFileContentType = payImgFileContentType;
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
