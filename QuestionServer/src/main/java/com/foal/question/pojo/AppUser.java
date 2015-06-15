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
import org.hibernate.annotations.Index;

import com.foal.question.config.Constant;
import com.foal.question.util.GsonTools;
import com.google.gson.JsonObject;

@Entity
@Table(name = "app_user", uniqueConstraints = {@UniqueConstraint(columnNames={"open_id_"})})
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6089095898500368983L;
	private String uid;
	private int userType;
	private String openId;
	private String username;
	private String password;
	private String name;
	private String signature;
	private String gender;
	private String figureurl;
	private Date createTime;
	private Date updateAt;
	private String lastLoginIp;
	private Date lastLoadFollowersTime;
	private int status;

	public class Status {
		public static final int Normal = 0;//正常
		public static final int Silenced = 1;//禁言，不能发布
		public static final int Freeze = 2;//冻结（封号），不能登录
	}
	
	public static class UserType {
		public static final int Thrid = 0;//第三方账户
		public static final int Local = 1;//本地账户
	}
	
	public static class Relation {
		public static final int None = 0;//没有关系
		public static final int Follow = 1;//已关注此人
		public static final int BeFollowed = 2;//已被此人关注
		public static final int Friend = 3;//相互关注
	}

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
	@Column(name = "user_type_")
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	@Index(name = "open_id_index_")
	@Column(name = "open_id_")
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Index(name = "username_index_")
	@Column(name = "username_")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "password_")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "name_")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "signature_")
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
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
	@Column(name = "last_login_ip_")
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Column(name = "last_load_followers_time_")
	public Date getLastLoadFollowersTime() {
		return lastLoadFollowersTime;
	}
	public void setLastLoadFollowersTime(Date lastLoadFollowersTime) {
		this.lastLoadFollowersTime = lastLoadFollowersTime;
	}
	@Column(name = "status_")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((figureurl == null) ? 0 : figureurl.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastLoadFollowersTime == null) ? 0 : lastLoadFollowersTime.hashCode());
		result = prime * result + ((lastLoginIp == null) ? 0 : lastLoginIp.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + status;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((updateAt == null) ? 0 : updateAt.hashCode());
		result = prime * result + userType;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (figureurl == null) {
			if (other.figureurl != null)
				return false;
		} else if (!figureurl.equals(other.figureurl))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastLoadFollowersTime == null) {
			if (other.lastLoadFollowersTime != null)
				return false;
		} else if (!lastLoadFollowersTime.equals(other.lastLoadFollowersTime))
			return false;
		if (lastLoginIp == null) {
			if (other.lastLoginIp != null)
				return false;
		} else if (!lastLoginIp.equals(other.lastLoginIp))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openId == null) {
			if (other.openId != null)
				return false;
		} else if (!openId.equals(other.openId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (status != other.status)
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (updateAt == null) {
			if (other.updateAt != null)
				return false;
		} else if (!updateAt.equals(other.updateAt))
			return false;
		if (userType != other.userType)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public JsonObject toJson() {
		JsonObject jo = GsonTools.parseJsonObject(this);
		if (userType == AppUser.UserType.Local) {
			jo.addProperty("figureurl", Constant.CONTEXT_WEB_URL + figureurl);
		}
		jo.remove("openId");
		jo.remove("signature");
		jo.remove("createTime");
		jo.remove("updateAt");
		jo.remove("password");
		jo.remove("lastLoginIp");
		jo.remove("lastLoadFollowersTime");
		return jo;
	}
}
