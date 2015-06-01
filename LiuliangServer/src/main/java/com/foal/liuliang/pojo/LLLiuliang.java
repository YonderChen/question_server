package com.foal.liuliang.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ll_liuliang")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class LLLiuliang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8008321844092597990L;
	private String id;
	private int thirdId;
	private LLTask llTask;
	private String goodId;
	private String keyword;
	private String shopType;
	private int times;
	private int path1;
	private int path2;
	private int path3;
	private int sleepTime;
	private int clickStart;
	private int clickEnd;
	private Date beginTime;
	private Date endTime;
	private int num;
	private Date createTime;
	private int status;
	
	private int doStatus;
	private int numCurrent;

	@Transient
	public int getDoStatus() {
		if (status == Status.Fail) {
			return 0;
		}
		long now = System.currentTimeMillis();
		if (now < beginTime.getTime()) {
			doStatus = DoStatus.Wait;
		}
		else if (now >= beginTime.getTime() && now < endTime.getTime()) {
			doStatus = DoStatus.Doing;
		} else {
			doStatus = DoStatus.Done;
		}
		return doStatus;
	}
	public void setDoStatus(int doStatus) {
		this.doStatus = doStatus;
	}

	@Transient
	public int getNumCurrent() {
		if (getDoStatus() == DoStatus.Doing) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    long from = df.parse(df.format(getBeginTime())).getTime();
			    long to = df.parse(df.format(new Date())).getTime();
			    if (from < to) {
			    	int days = (int)((to - from) / (1000 * 60 * 60 * 24));
			    	numCurrent = times * days;
				} else {
					numCurrent = 0;
				}
			} catch (Exception e) {
				numCurrent = 0;
			}
		} else if (getDoStatus() == DoStatus.Done) {
			numCurrent = num;
		} else {
			numCurrent = 0;
		}
		return numCurrent;
	}
	public void setNumCurrent(int numCurrent) {
		this.numCurrent = numCurrent;
	}

	public static class Status {
		public static final int Fail = 0;
		public static final int Success = 1;
	}
	
	public static class DoStatus {
		public static int Fail = 0;
		public static int Wait = 1;
		public static int Doing = 2;
		public static int Done = 3;
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
	@Column(name = "third_id_")
	public int getThirdId() {
		return thirdId;
	}
	public void setThirdId(int thirdId) {
		this.thirdId = thirdId;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_id_")
	public LLTask getLlTask() {
		return llTask;
	}
	public void setLlTask(LLTask llTask) {
		this.llTask = llTask;
	}
	@Column(name = "good_id_")
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	@Column(name = "keyword_")
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Column(name = "shop_type_")
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	@Column(name = "times_")
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	@Column(name = "path1_")
	public int getPath1() {
		return path1;
	}
	public void setPath1(int path1) {
		this.path1 = path1;
	}
	@Column(name = "path2_")
	public int getPath2() {
		return path2;
	}
	public void setPath2(int path2) {
		this.path2 = path2;
	}
	@Column(name = "path3_")
	public int getPath3() {
		return path3;
	}
	public void setPath3(int path3) {
		this.path3 = path3;
	}
	@Column(name = "sleep_time_")
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	@Column(name = "click_start_")
	public int getClickStart() {
		return clickStart;
	}
	public void setClickStart(int clickStart) {
		this.clickStart = clickStart;
	}
	@Column(name = "click_end_")
	public int getClickEnd() {
		return clickEnd;
	}
	public void setClickEnd(int clickEnd) {
		this.clickEnd = clickEnd;
	}
	@Column(name = "begin_time_")
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Column(name = "end_time_")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "num_")
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + clickEnd;
		result = prime * result + clickStart;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((goodId == null) ? 0 : goodId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((llTask == null) ? 0 : llTask.hashCode());
		result = prime * result + num;
		result = prime * result + path1;
		result = prime * result + path2;
		result = prime * result + path3;
		result = prime * result + ((shopType == null) ? 0 : shopType.hashCode());
		result = prime * result + sleepTime;
		result = prime * result + status;
		result = prime * result + thirdId;
		result = prime * result + times;
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
		LLLiuliang other = (LLLiuliang) obj;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (clickEnd != other.clickEnd)
			return false;
		if (clickStart != other.clickStart)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (goodId == null) {
			if (other.goodId != null)
				return false;
		} else if (!goodId.equals(other.goodId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (llTask == null) {
			if (other.llTask != null)
				return false;
		} else if (!llTask.equals(other.llTask))
			return false;
		if (num != other.num)
			return false;
		if (path1 != other.path1)
			return false;
		if (path2 != other.path2)
			return false;
		if (path3 != other.path3)
			return false;
		if (shopType == null) {
			if (other.shopType != null)
				return false;
		} else if (!shopType.equals(other.shopType))
			return false;
		if (sleepTime != other.sleepTime)
			return false;
		if (status != other.status)
			return false;
		if (thirdId != other.thirdId)
			return false;
		if (times != other.times)
			return false;
		return true;
	}

}
