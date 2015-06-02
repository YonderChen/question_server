package com.foal.liuliang.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.foal.liuliang.listener.ServiceLocator;
import com.foal.liuliang.service.LLTaskService;

@Entity
@Table(name = "ll_task")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class LLTask implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6441491868118568013L;
	private String taskId;
	private ServerUser serverUser;
	private LLShop llShop;
	private String clientType;
	private int taskType;
	private String goodsUrl;
	private String goodsName;
	private String goodsImg;
	private String keyword1;
	private int orderNumberOneDay1;
	private String keyword2;
	private int orderNumberOneDay2;
	private String keyword3;
	private int orderNumberOneDay3;
	private String keyword4;
	private int orderNumberOneDay4;
	private String keyword5;
	private int orderNumberOneDay5;
	private int searchSource;
	private int durationDay;
	private int pageStayType;
	private int visitTimeType;
	private int isQuickVerify;
	private int isQuickExecute;
	private int costScore;
	private Date createTime;
	private Date checkTime;
	private ServerUser checkAdmin;
	private Date finishTime;
	private int status;
	private String remark;
	
	private int finishDay;
	private long finishDayUpdateTime = 0;
	private int statusCurrent;
	private long statusCurrentUpdateTime = 0;

	@Transient
	public int getFinishDay() {
		if (System.currentTimeMillis() - finishDayUpdateTime < 1000 * 60) {//60秒更新一次
			return finishDay;
		}
		finishDay = ServiceLocator.getBean(LLTaskService.class).updateFinishDay(this);
		finishDayUpdateTime = System.currentTimeMillis();
		return finishDay;
	}
	public void setFinishDay(int finishDay) {
		this.finishDay = finishDay;
	}

	@Transient
	public int getStatusCurrent() {
		if (System.currentTimeMillis() - statusCurrentUpdateTime < 1000 * 60) {//60秒更新一次
			return statusCurrent;
		}
		if (status == Status.Executing) {
			statusCurrent = ServiceLocator.getBean(LLTaskService.class).updateTaskStatus(this, status);
		} else {
			statusCurrent = status;
		}
		statusCurrentUpdateTime = System.currentTimeMillis();
		return statusCurrent;
	}
	
	public void setStatusCurrent(int statusCurrent) {
		this.statusCurrent = statusCurrent;
	}

	public static class Status {
        public static final int Create = 0;//未发布
        public static final int Verify = 1;//待审核
        public static final int Executing = 2;//执行中
        public static final int Finish = 3;//已完成
        public static final int Cancel = 4;//已取消
        public static final int VerifyFaild = 5;//审核不通过
        public static final int Changed = 6;//任务修改,待审核
	}

	public static class ClientType {
		public static final String PC = "pc";
		public static final String Phone = "phone";
	}
	
	@GenericGenerator(name = "generator", strategy = "assigned")
	@Id
	@Column(name = "task_id_")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id_")
	public ServerUser getServerUser() {
		return serverUser;
	}

	public void setServerUser(ServerUser serverUser) {
		this.serverUser = serverUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shop_id_")
	public LLShop getLlShop() {
		return llShop;
	}

	public void setLlShop(LLShop llShop) {
		this.llShop = llShop;
	}

	@Column(name = "client_type_")
	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	@Column(name = "task_type_")
	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	@Column(name = "goods_url_")
	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	@Column(name = "goods_name_")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "goods_img_")
	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	@Column(name = "keyword_1_")
	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	@Column(name = "order_number_one_day_1_")
	public int getOrderNumberOneDay1() {
		return orderNumberOneDay1;
	}

	public void setOrderNumberOneDay1(int orderNumberOneDay1) {
		this.orderNumberOneDay1 = orderNumberOneDay1;
	}

	@Column(name = "keyword_2_")
	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	@Column(name = "order_number_one_day_2_")
	public int getOrderNumberOneDay2() {
		return orderNumberOneDay2;
	}

	public void setOrderNumberOneDay2(int orderNumberOneDay2) {
		this.orderNumberOneDay2 = orderNumberOneDay2;
	}

	@Column(name = "keyword_3_")
	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}

	@Column(name = "order_number_one_day_3_")
	public int getOrderNumberOneDay3() {
		return orderNumberOneDay3;
	}

	public void setOrderNumberOneDay3(int orderNumberOneDay3) {
		this.orderNumberOneDay3 = orderNumberOneDay3;
	}

	@Column(name = "keyword_4_")
	public String getKeyword4() {
		return keyword4;
	}

	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}

	@Column(name = "order_number_one_day_4_")
	public int getOrderNumberOneDay4() {
		return orderNumberOneDay4;
	}

	public void setOrderNumberOneDay4(int orderNumberOneDay4) {
		this.orderNumberOneDay4 = orderNumberOneDay4;
	}

	@Column(name = "keyword_5_")
	public String getKeyword5() {
		return keyword5;
	}

	public void setKeyword5(String keyword5) {
		this.keyword5 = keyword5;
	}

	@Column(name = "order_number_one_day_5_")
	public int getOrderNumberOneDay5() {
		return orderNumberOneDay5;
	}

	public void setOrderNumberOneDay5(int orderNumberOneDay5) {
		this.orderNumberOneDay5 = orderNumberOneDay5;
	}

	@Column(name = "search_source_")
	public int getSearchSource() {
		return searchSource;
	}

	public void setSearchSource(int searchSource) {
		this.searchSource = searchSource;
	}

	@Column(name = "duration_day_")
	public int getDurationDay() {
		return durationDay;
	}

	public void setDurationDay(int durationDay) {
		this.durationDay = durationDay;
	}

	@Column(name = "page_stay_type_")
	public int getPageStayType() {
		return pageStayType;
	}

	public void setPageStayType(int pageStayType) {
		this.pageStayType = pageStayType;
	}

	@Column(name = "visit_time_type_")
	public int getVisitTimeType() {
		return visitTimeType;
	}

	public void setVisitTimeType(int visitTimeType) {
		this.visitTimeType = visitTimeType;
	}

	@Column(name = "is_quick_verify_")
	public int getIsQuickVerify() {
		return isQuickVerify;
	}

	public void setIsQuickVerify(int isQuickVerify) {
		this.isQuickVerify = isQuickVerify;
	}

	@Column(name = "is_quick_execute_")
	public int getIsQuickExecute() {
		return isQuickExecute;
	}

	public void setIsQuickExecute(int isQuickExecute) {
		this.isQuickExecute = isQuickExecute;
	}

	@Column(name = "cost_score_")
	public int getCostScore() {
		return costScore;
	}

	public void setCostScore(int costScore) {
		this.costScore = costScore;
	}

	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "check_time_")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_admin_")
	public ServerUser getCheckAdmin() {
		return checkAdmin;
	}

	public void setCheckAdmin(ServerUser checkAdmin) {
		this.checkAdmin = checkAdmin;
	}

	@Column(name = "finish_time_")
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "status_")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "remark_")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkAdmin == null) ? 0 : checkAdmin.hashCode());
		result = prime * result + ((checkTime == null) ? 0 : checkTime.hashCode());
		result = prime * result + ((clientType == null) ? 0 : clientType.hashCode());
		result = prime * result + costScore;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + durationDay;
		result = prime * result + ((finishTime == null) ? 0 : finishTime.hashCode());
		result = prime * result + ((goodsImg == null) ? 0 : goodsImg.hashCode());
		result = prime * result + ((goodsName == null) ? 0 : goodsName.hashCode());
		result = prime * result + ((goodsUrl == null) ? 0 : goodsUrl.hashCode());
		result = prime * result + isQuickExecute;
		result = prime * result + isQuickVerify;
		result = prime * result + ((keyword1 == null) ? 0 : keyword1.hashCode());
		result = prime * result + ((keyword2 == null) ? 0 : keyword2.hashCode());
		result = prime * result + ((keyword3 == null) ? 0 : keyword3.hashCode());
		result = prime * result + ((keyword4 == null) ? 0 : keyword4.hashCode());
		result = prime * result + ((keyword5 == null) ? 0 : keyword5.hashCode());
		result = prime * result + ((llShop == null) ? 0 : llShop.hashCode());
		result = prime * result + orderNumberOneDay1;
		result = prime * result + orderNumberOneDay2;
		result = prime * result + orderNumberOneDay3;
		result = prime * result + orderNumberOneDay4;
		result = prime * result + orderNumberOneDay5;
		result = prime * result + pageStayType;
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + searchSource;
		result = prime * result + ((serverUser == null) ? 0 : serverUser.hashCode());
		result = prime * result + status;
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result + taskType;
		result = prime * result + visitTimeType;
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
		LLTask other = (LLTask) obj;
		if (checkAdmin == null) {
			if (other.checkAdmin != null)
				return false;
		} else if (!checkAdmin.equals(other.checkAdmin))
			return false;
		if (checkTime == null) {
			if (other.checkTime != null)
				return false;
		} else if (!checkTime.equals(other.checkTime))
			return false;
		if (clientType == null) {
			if (other.clientType != null)
				return false;
		} else if (!clientType.equals(other.clientType))
			return false;
		if (costScore != other.costScore)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (durationDay != other.durationDay)
			return false;
		if (finishTime == null) {
			if (other.finishTime != null)
				return false;
		} else if (!finishTime.equals(other.finishTime))
			return false;
		if (goodsImg == null) {
			if (other.goodsImg != null)
				return false;
		} else if (!goodsImg.equals(other.goodsImg))
			return false;
		if (goodsName == null) {
			if (other.goodsName != null)
				return false;
		} else if (!goodsName.equals(other.goodsName))
			return false;
		if (goodsUrl == null) {
			if (other.goodsUrl != null)
				return false;
		} else if (!goodsUrl.equals(other.goodsUrl))
			return false;
		if (isQuickExecute != other.isQuickExecute)
			return false;
		if (isQuickVerify != other.isQuickVerify)
			return false;
		if (keyword1 == null) {
			if (other.keyword1 != null)
				return false;
		} else if (!keyword1.equals(other.keyword1))
			return false;
		if (keyword2 == null) {
			if (other.keyword2 != null)
				return false;
		} else if (!keyword2.equals(other.keyword2))
			return false;
		if (keyword3 == null) {
			if (other.keyword3 != null)
				return false;
		} else if (!keyword3.equals(other.keyword3))
			return false;
		if (keyword4 == null) {
			if (other.keyword4 != null)
				return false;
		} else if (!keyword4.equals(other.keyword4))
			return false;
		if (keyword5 == null) {
			if (other.keyword5 != null)
				return false;
		} else if (!keyword5.equals(other.keyword5))
			return false;
		if (llShop == null) {
			if (other.llShop != null)
				return false;
		} else if (!llShop.equals(other.llShop))
			return false;
		if (orderNumberOneDay1 != other.orderNumberOneDay1)
			return false;
		if (orderNumberOneDay2 != other.orderNumberOneDay2)
			return false;
		if (orderNumberOneDay3 != other.orderNumberOneDay3)
			return false;
		if (orderNumberOneDay4 != other.orderNumberOneDay4)
			return false;
		if (orderNumberOneDay5 != other.orderNumberOneDay5)
			return false;
		if (pageStayType != other.pageStayType)
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (searchSource != other.searchSource)
			return false;
		if (serverUser == null) {
			if (other.serverUser != null)
				return false;
		} else if (!serverUser.equals(other.serverUser))
			return false;
		if (status != other.status)
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (taskType != other.taskType)
			return false;
		if (visitTimeType != other.visitTimeType)
			return false;
		return true;
	}

}
