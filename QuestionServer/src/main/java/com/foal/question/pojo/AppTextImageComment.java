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
import com.google.gson.JsonObject;

@Entity
@Table(name = "app_text_image_comment")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextImageComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 443912254333965178L;
	private String id;
	private AppTextImage record;
	private AppUser owner;
	private String content;
	private Date createTime;

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id_")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Index(name = "record_id_index")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "record_id_")
	public AppTextImage getRecord() {
		return record;
	}
	public void setRecord(AppTextImage record) {
		this.record = record;
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
	@JoinColumn(name = "content_")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((record == null) ? 0 : record.hashCode());
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
		AppTextImageComment other = (AppTextImageComment) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (record == null) {
			if (other.record != null)
				return false;
		} else if (!record.equals(other.record))
			return false;
		return true;
	}
	
	public JsonObject toJson() {
		JsonObject jo = new JsonObject();
		jo.addProperty("id", id);
		jo.addProperty("ownerId", owner.getUid());
		if (owner.getUserType() == AppUser.UserType.Local) {
			jo.addProperty("ownerFigureurl", Constant.CONTEXT_WEB_URL + owner.getFigureurl());
		} else {
			jo.addProperty("ownerFigureurl", owner.getFigureurl());
		}
		jo.addProperty("ownerName", owner.getName());
		jo.addProperty("content", content);
		jo.addProperty("createTime", createTime.getTime());
		return jo;
	}
}
