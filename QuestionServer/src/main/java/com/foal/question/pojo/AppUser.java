package com.foal.question.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.foal.question.util.GsonTools;
import com.foal.question.util.StringTools;
import com.google.gson.JsonObject;

@Entity
@Table(name = "app_user", uniqueConstraints = {@UniqueConstraint(columnNames={"open_id_"})})
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8286569436899179949L;
	private String uid;
	private String openId;
	private String name;
	private String gender;
	private String figureurl;
	private Date createTime;
	private Date updateAt;

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "uid_")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Column(name = "open_id_")
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Column(name = "name_")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "gender_")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "figureurl_")
	public String getFigureurl() {
		return figureurl;
	}
	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}
	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "update_at_")
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((figureurl == null) ? 0 : figureurl.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((updateAt == null) ? 0 : updateAt.hashCode());
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
		AppUser other = (AppUser) obj;
		return StringTools.equalsStr(uid, other.uid)
				&& StringTools.equalsStr(openId, other.openId)
				&& StringTools.equalsStr(name, other.name)
				&& StringTools.equalsStr(gender, other.gender)
				&& StringTools.equalsStr(figureurl, other.figureurl)
				&& createTime.getTime() == other.createTime.getTime()
				&& updateAt.getTime() == other.updateAt.getTime();
	}
	
	public JsonObject toJson() {
		JsonObject jo = GsonTools.parseJsonObject(this);
		jo.remove("openId");
		jo.remove("createTime");
		jo.remove("updateAt");
		return jo;
	}
}
