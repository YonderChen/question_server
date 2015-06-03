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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.foal.liuliang.config.Constant;
import com.foal.liuliang.listener.ServiceLocator;
import com.foal.liuliang.service.LLLiuliangService;

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
	private Date date;
	private int num;
	private int finishNum;
	private long finishNumUpdateTime = 0;
	private int isFinish;
	private Date createTime;
	private int status;
	
	private int doStatus;
	private long doStatusUpdateTime = 0;
	private int numCurrent;
	
	@Transient
	public int getDoStatus() {
		if (System.currentTimeMillis() - doStatusUpdateTime < Constant.SyncFinishNumInterval) {//60秒更新一次
			return doStatus;
		}
		if (status == Status.Fail) {
			return DoStatus.Fail;
		}
		if (isFinish > 0 || num == 0) {
			return DoStatus.Done;
		}
		numCurrent = getNumCurrent();
		if (numCurrent == 0) {
			doStatus = DoStatus.Wait;
		}
		else if (numCurrent < num) {
			doStatus = DoStatus.Doing;
		}
		else if (numCurrent >= num) {
			doStatus = DoStatus.Done;
		}
		doStatusUpdateTime = System.currentTimeMillis();
		return doStatus;
	}
	public void setDoStatus(int doStatus) {
		this.doStatus = doStatus;
	}

	@Transient
	public long getDoStatusUpdateTime() {
		return doStatusUpdateTime;
	}
	public void setDoStatusUpdateTime(long doStatusUpdateTime) {
		this.doStatusUpdateTime = doStatusUpdateTime;
	}
	
	@Transient
	public int getNumCurrent() {
		if (System.currentTimeMillis() - finishNumUpdateTime < 1000 * 60) {//60秒更新一次
			return finishNum;
		}
		if (status == Status.Success) {
			try {
				numCurrent = ServiceLocator.getBean(LLLiuliangService.class).updateCurrentNum(this);
			} catch (Exception e) {
				numCurrent = 0;
			}
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
	@Column(name = "date_")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name = "num_")
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Column(name = "finish_num_")
	public int getFinishNum() {
		return finishNum;
	}
	public void setFinishNum(int finishNum) {
		this.finishNum = finishNum;
	}
	@Column(name = "finish_num_update_time_")
	public long getFinishNumUpdateTime() {
		return finishNumUpdateTime;
	}
	public void setFinishNumUpdateTime(long finishNumUpdateTime) {
		this.finishNumUpdateTime = finishNumUpdateTime;
	}
	@Column(name = "is_finish_")
	public int getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
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
		result = prime * result + clickEnd;
		result = prime * result + clickStart;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (clickEnd != other.clickEnd)
			return false;
		if (clickStart != other.clickStart)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
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
