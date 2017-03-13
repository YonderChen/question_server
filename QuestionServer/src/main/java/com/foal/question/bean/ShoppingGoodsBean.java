package com.foal.question.bean;



public class ShoppingGoodsBean extends Page {
	private int id;	//id
	private int goodType;	//商品类型
	private String title;	//标题
	private String content;	//描述内容
	private String imageUrl;	//图片url
	private long createAt;	//创建时间
	private int sourceStore;	//来源商店（淘宝、天猫）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoodType() {
		return goodType;
	}
	public void setGoodType(int goodType) {
		this.goodType = goodType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public long getCreateAt() {
		return createAt;
	}
	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}
	public int getSourceStore() {
		return sourceStore;
	}
	public void setSourceStore(int sourceStore) {
		this.sourceStore = sourceStore;
	}
}
