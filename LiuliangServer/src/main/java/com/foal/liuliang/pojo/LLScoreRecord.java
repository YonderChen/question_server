package com.foal.liuliang.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	public static class ScoreRecordType {
		public static final int Buy = 1;//购买
		public static final int Cost = 2;//消费
		public static final int AdminEdit = 3;//管理员修改
		public static final int TaskReturn = 4;//任务消费返还
	}
	
	public static Map<Integer, String> ScoreRecordTypeMap = new HashMap<Integer, String>();
	static {
		ScoreRecordTypeMap.put(ScoreRecordType.Buy, "购买");
		ScoreRecordTypeMap.put(ScoreRecordType.Cost, "消费");
		ScoreRecordTypeMap.put(ScoreRecordType.AdminEdit, "管理员修改");
		ScoreRecordTypeMap.put(ScoreRecordType.TaskReturn, "任务消费返还");
	}
	
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
