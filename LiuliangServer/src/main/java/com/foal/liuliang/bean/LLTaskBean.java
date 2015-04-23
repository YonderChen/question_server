package com.foal.liuliang.bean;

import java.io.File;
import java.util.Date;

/**
 * @author cyd
 * @date 2015-4-10
 */
public class LLTaskBean extends Page {

	private String taskId;
	private String userId;
	private String shopId;
	private String goodsUrl;
	private String goodsName;
	private String goodsImg;
	private String keyword1;
	private int orderNumberOneDay1;
	private String keyword2;
	private int orderNumberOneDay2;
	private String keyword3;
	private int orderNumberOneDay3;
	private String keyword4;
	private int orderNumberOneDay4;
	private String keyword5;
	private int orderNumberOneDay5;
	private int searchSource;
	private int durationDay;
	private int pageStayType;
	private int visitTimeType;
	private int isQuickVerify;
	private int isQuickExecute;
	private int costScore;
	private Date createTime;
	private Date finishTime;
	private int status;
	
	private File goodsImgFile;
    private String goodsImgFileFileName;
    private String goodsImgFileContentType;
	
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
	public String getGoodsUrl() {
		return goodsUrl;
	}
	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public int getOrderNumberOneDay1() {
		return orderNumberOneDay1;
	}
	public void setOrderNumberOneDay1(int orderNumberOneDay1) {
		this.orderNumberOneDay1 = orderNumberOneDay1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public int getOrderNumberOneDay2() {
		return orderNumberOneDay2;
	}
	public void setOrderNumberOneDay2(int orderNumberOneDay2) {
		this.orderNumberOneDay2 = orderNumberOneDay2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public int getOrderNumberOneDay3() {
		return orderNumberOneDay3;
	}
	public void setOrderNumberOneDay3(int orderNumberOneDay3) {
		this.orderNumberOneDay3 = orderNumberOneDay3;
	}
	public String getKeyword4() {
		return keyword4;
	}
	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}
	public int getOrderNumberOneDay4() {
		return orderNumberOneDay4;
	}
	public void setOrderNumberOneDay4(int orderNumberOneDay4) {
		this.orderNumberOneDay4 = orderNumberOneDay4;
	}
	public String getKeyword5() {
		return keyword5;
	}
	public void setKeyword5(String keyword5) {
		this.keyword5 = keyword5;
	}
	public int getOrderNumberOneDay5() {
		return orderNumberOneDay5;
	}
	public void setOrderNumberOneDay5(int orderNumberOneDay5) {
		this.orderNumberOneDay5 = orderNumberOneDay5;
	}
	public int getSearchSource() {
		return searchSource;
	}
	public void setSearchSource(int searchSource) {
		this.searchSource = searchSource;
	}
	public int getDurationDay() {
		return durationDay;
	}
	public void setDurationDay(int durationDay) {
		this.durationDay = durationDay;
	}
	public int getPageStayType() {
		return pageStayType;
	}
	public void setPageStayType(int pageStayType) {
		this.pageStayType = pageStayType;
	}
	public int getVisitTimeType() {
		return visitTimeType;
	}
	public void setVisitTimeType(int visitTimeType) {
		this.visitTimeType = visitTimeType;
	}
	public int getIsQuickVerify() {
		return isQuickVerify;
	}
	public void setIsQuickVerify(int isQuickVerify) {
		this.isQuickVerify = isQuickVerify;
	}
	public int getIsQuickExecute() {
		return isQuickExecute;
	}
	public void setIsQuickExecute(int isQuickExecute) {
		this.isQuickExecute = isQuickExecute;
	}
	public int getCostScore() {
		return costScore;
	}
	public void setCostScore(int costScore) {
		this.costScore = costScore;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public File getGoodsImgFile() {
		return goodsImgFile;
	}
	public void setGoodsImgFile(File goodsImgFile) {
		this.goodsImgFile = goodsImgFile;
	}
	public String getGoodsImgFileFileName() {
		return goodsImgFileFileName;
	}
	public void setGoodsImgFileFileName(String goodsImgFileFileName) {
		this.goodsImgFileFileName = goodsImgFileFileName;
	}
	public String getGoodsImgFileContentType() {
		return goodsImgFileContentType;
	}
	public void setGoodsImgFileContentType(String goodsImgFileContentType) {
		this.goodsImgFileContentType = goodsImgFileContentType;
	}
}
