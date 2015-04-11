package com.foal.liuliang.pojo;

import java.io.Serializable;

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
@Table(name = "ll_score_order")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class LLScoreOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5599127033629963103L;
	private String orderId;
	private ServerUser serverUser;
	private int scoreNum;
	private int price;
	private String dealId;
	private ServerUser checkAdmin;
	private int status;

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "order_id_")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id_")
	public ServerUser getServerUser() {
		return serverUser;
	}

	public void setServerUser(ServerUser serverUser) {
		this.serverUser = serverUser;
	}

	@Column(name = "score_num_")
	public int getScoreNum() {
		return scoreNum;
	}

	public void setScoreNum(int scoreNum) {
		this.scoreNum = scoreNum;
	}

	@Column(name = "price_")
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "deal_id_")
	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_admin_id_")
	public ServerUser getCheckAdmin() {
		return checkAdmin;
	}

	public void setCheckAdmin(ServerUser checkAdmin) {
		this.checkAdmin = checkAdmin;
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
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((serverUser == null) ? 0 : serverUser.hashCode());
		result = prime * result + scoreNum;
		result = prime * result + price;
		result = prime * result + ((dealId == null) ? 0 : dealId.hashCode());
		result = prime * result + ((checkAdmin == null) ? 0 : checkAdmin.hashCode());
		result = prime * result + status;
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
		LLScoreOrder other = (LLScoreOrder) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (serverUser == null) {
			if (other.serverUser != null)
				return false;
		} else if (!serverUser.equals(other.serverUser))
			return false;
		if (scoreNum != other.scoreNum) {
			return false;
		}
		if (price != other.price) {
			return false;
		}
		if (dealId == null) {
			if (other.dealId != null)
				return false;
		} else if (!dealId.equals(other.dealId))
			return false;
		if (checkAdmin == null) {
			if (other.checkAdmin != null)
				return false;
		} else if (!checkAdmin.equals(other.checkAdmin))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
