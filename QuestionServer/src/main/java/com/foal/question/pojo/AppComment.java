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
@Table(name = "app_comment")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9132273889559563198L;
	private int id;
	private int type;
	private int recordId;
	private AppUser owner;
	private String content;
	private Date createTime;
	
	public static class Type {
		public static final int TextImageComment = 0;
		public static final int TextVoiceComment = 1;
	}

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
	@Index(name = "record_id_index")
	@Column(name = "record_id_")
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	@Column(name = "type_")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
		result = prime * result + id;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + recordId;
		result = prime * result + type;
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
		AppComment other = (AppComment) obj;
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
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (recordId != other.recordId)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	public JsonObject toJson() {
		JsonObject jo = new JsonObject();
		jo.addProperty("id", id);
		jo.addProperty("type", type);
		jo.addProperty("recordId", recordId);
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
