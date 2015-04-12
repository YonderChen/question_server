package com.foal.liuliang.bean;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLDealOrderBean extends Page {

	private String orderId;
	private String userId;
	private int num;
	private int price;
	private String dealId;
	private String checkAdminId;
	private int status;
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
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
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
}
