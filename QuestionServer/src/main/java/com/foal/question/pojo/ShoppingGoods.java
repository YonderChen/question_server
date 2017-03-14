package com.foal.question.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.foal.question.util.GsonTools;
import com.google.gson.JsonObject;

@Entity
@Table(name = "shopping_goods")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingGoods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3730227929657765007L;
	private int id;	//id
	private int goodType;	//商品类型
	private String title;	//标题
	private String content;	//描述内容
	private String imageUrl;	//图片url
	private long createAt;	//创建时间
	private int sourceStore;	//来源商店（淘宝、天猫）

	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id_")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "good_type_")
	public int getGoodType() {
		return goodType;
	}
	public void setGoodType(int goodType) {
		this.goodType = goodType;
	}
	@Column(name = "title_")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "content_")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "image_url_")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Column(name = "create_at_")
	public long getCreateAt() {
		return createAt;
	}
	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}
	@Column(name = "source_store_")
	public int getSourceStore() {
		return sourceStore;
	}
	public void setSourceStore(int sourceStore) {
		this.sourceStore = sourceStore;
	}
	public JsonObject toJson() {
		JsonObject jo = GsonTools.parseJsonObject(this);
		return jo;
	}
}
