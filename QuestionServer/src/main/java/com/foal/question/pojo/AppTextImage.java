package com.foal.question.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.foal.question.config.Constant;
import com.foal.question.util.GsonTools;
import com.foal.question.util.StringTools;
import com.google.gson.JsonObject;

@Entity
@Table(name = "app_text_image")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextImage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3012366411497097753L;
	private int id;
	private String ownerId;
	private String content;
	private String imageUrl;
	private String imageInfo;
	private Date createTime;
	private int praiseCount;
	private int shareCount;

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
	@Index(name = "owner_id_index")
	@Column(name = "owner_id_")
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	@Column(name = "image_info_")
	public String getImageInfo() {
		return imageInfo;
	}
	public void setImageInfo(String imageInfo) {
		this.imageInfo = imageInfo;
	}
	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "praise_count_")
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	public void incPraiseCount() {
		this.praiseCount++;
	}
	public void decPraiseCount() {
		this.praiseCount--;
	}
	@Column(name = "share_count_")
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public void incShareCount() {
		this.praiseCount++;
	}
	public void decShareCount() {
		this.praiseCount--;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((imageInfo == null) ? 0 : imageInfo.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + praiseCount;
		result = prime * result + shareCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppTextImage other = (AppTextImage) obj;
		return id == other.id
				&& StringTools.equalsStr(ownerId, other.ownerId)
				&& StringTools.equalsStr(content, other.content)
				&& StringTools.equalsStr(imageUrl, other.imageUrl)
				&& StringTools.equalsStr(imageInfo, other.imageInfo)
				&& createTime.getTime() == other.createTime.getTime()
				&& praiseCount == other.praiseCount
				&& shareCount == other.shareCount;
	}
	
	public JsonObject toJson(AppUser owner, boolean hasPraised) {
		JsonObject jo = GsonTools.parseJsonObject(this);
		jo.addProperty("ownerFigureurl", owner.getFigureurl());
		jo.addProperty("ownerName", owner.getName());
		jo.addProperty("imageUrl", Constant.CONTEXT_WEB_URL + this.imageUrl);
		jo.add("imageInfo", GsonTools.parseJsonObject(imageInfo));
		jo.addProperty("createTime", createTime.getTime());
		jo.addProperty("hasPraised", hasPraised);
		return jo;
	}
}
