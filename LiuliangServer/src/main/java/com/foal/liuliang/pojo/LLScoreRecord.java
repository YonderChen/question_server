package com.foal.liuliang.pojo;

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

@Entity
@Table(name = "ll_score_record")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class LLScoreRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1051461448595889789L;
	private String recordId;
	private ServerUser serverUser;
	private int num;
	private int type;
	private int remain;
	private Date createTime;
	private String remark;

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "record_id_")
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id_")
	public ServerUser getServerUser() {
		return serverUser;
	}

	public void setServerUser(ServerUser serverUser) {
		this.serverUser = serverUser;
	}

	@Column(name = "num_")
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Column(name = "type_")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "remain_")
	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "remark_")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
