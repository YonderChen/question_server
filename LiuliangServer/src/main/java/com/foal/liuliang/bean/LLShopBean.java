package com.foal.liuliang.bean;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLShopBean extends Page {

	private String shopId;
	private String bindPlat;
	private String bindName;
	private String shopUrl;
	private String verifyGoodsUrl;
	private String verifyCode;
	private int status;
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
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
}
