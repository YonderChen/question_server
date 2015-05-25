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
	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
