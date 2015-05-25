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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "app_user_follow", uniqueConstraints = {@UniqueConstraint(columnNames={"owner_id_", "follower_uid_"})})
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUserFollow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7747478682035433452L;
	private String id;
	private AppUser owner;
	private AppUser follower;
	private Date createTime;
	private int status;

	public static class FollowStatus {
		public static final int Single = 0;
		public static final int Mutual = 1;
	}
	
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
	@Index(name = "owner_id_index")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id_")
	public AppUser getOwner() {
		return owner;
	}
	public void setOwner(AppUser owner) {
		this.owner = owner;
	}
	@Index(name = "follower_id_index")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "follower_id_")
	public AppUser getFollower() {
		return follower;
	}
	public void setFollower(AppUser follower) {
		this.follower = follower;
	}
	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "status_")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
