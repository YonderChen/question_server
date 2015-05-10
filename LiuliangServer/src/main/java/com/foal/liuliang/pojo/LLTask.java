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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

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

	public static class Status {
        public static final int Create = 0;//未发布
        public static final int Verify = 1;//待审核
        public static final int Executing = 2;//执行中
        public static final int Finish = 3;//已完成
        public static final int Cancel = 4;//已取消
        public static final int VerifyFaild = 5;//审核不通过
        public static final int Changed = 6;//任务修改,待审核
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


}
