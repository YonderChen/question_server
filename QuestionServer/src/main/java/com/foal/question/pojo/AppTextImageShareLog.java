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

import com.foal.question.util.StringTools;

@Entity
@Table(name = "app_text_image_share_log")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextImageShareLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9007006388915615315L;
	private String opId;
	private int recordId;
	private String uid;
	private int status;
	private Date opTime;

	@GenericGenerator(name = "generator", strategy = "assigned")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "op_id_")
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	@Column(name = "record_id_")
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	@Column(name = "uid_")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Column(name = "status_")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name = "op_time_")
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opId == null) ? 0 : opId.hashCode());
		result = prime * result + recordId;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + status;
		result = prime * result + opTime.hashCode();
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
		AppTextImageShareLog other = (AppTextImageShareLog) obj;
		return StringTools.equalsStr(opId, other.opId)
			&& recordId == other.recordId
			&& StringTools.equalsStr(uid, other.uid)
			&& status == other.status
			&& opTime.getTime() == other.opTime.getTime();
	}

	public boolean hasPraised() {
		return status > 0;
	}
}
