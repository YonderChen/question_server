package com.foal.question.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.foal.question.config.Constant;
import com.foal.question.util.GsonTools;
import com.google.gson.JsonObject;

@Entity
@Table(name = "app_text_image")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextImage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3559410446984912861L;
	private int id;
	private AppUser owner;
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id_")
	public AppUser getOwner() {
		return owner;
	}
	public void setOwner(AppUser owner) {
		this.owner = owner;
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
		this.shareCount++;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageInfo == null) ? 0 : imageInfo.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (imageInfo == null) {
			if (other.imageInfo != null)
				return false;
		} else if (!imageInfo.equals(other.imageInfo))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (praiseCount != other.praiseCount)
			return false;
		if (shareCount != other.shareCount)
			return false;
		return true;
	}
	public JsonObject toJson(boolean hasPraised, int commentCount) {
		JsonObject jo = GsonTools.parseJsonObject(this);
		jo.remove("owner");
		jo.addProperty("ownerId", owner.getUid());
		if (owner.getUserType() == AppUser.UserType.Local) {
			jo.addProperty("ownerFigureurl", Constant.CONTEXT_WEB_URL + owner.getFigureurl());
		} else {
			jo.addProperty("ownerFigureurl", owner.getFigureurl());
		}
		jo.addProperty("ownerName", owner.getName());
		jo.addProperty("imageUrl", Constant.CONTEXT_WEB_URL + this.imageUrl);
		jo.add("imageInfo", GsonTools.parseJsonObject(imageInfo));
		jo.addProperty("createTime", createTime.getTime());
		jo.addProperty("hasPraised", hasPraised ? 1 : 0);
		jo.addProperty("commentCount", commentCount);
		return jo;
	}
}
