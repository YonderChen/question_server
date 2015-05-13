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
@Table(name = "ll_shop")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class LLShop implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6358906394155010990L;
	private String shopId;
	private ServerUser serverUser;
	private String bindPlat;
	private String bindName;
	private String shopUrl;
	private String verifyGoodsUrl;
	private String verifyCode;
	private Date createTime;
	private Date checkTime;
	private ServerUser checkAdmin;
	private int status;

	public static class Status {
		public static final int Create = 0;
		public static final int Success = 1;
		public static final int CheckFail = 2;
	}

	public static class BindPlat {
		public static final String TaoBao = "taobao";
		public static final String Tmall = "tmall";
		public static final String JD = "jd";
	}
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "shop_id_")
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id_")
	public ServerUser getServerUser() {
		return serverUser;
	}

	public void setServerUser(ServerUser serverUser) {
		this.serverUser = serverUser;
	}

	@Column(name = "bind_plat_")
	public String getBindPlat() {
		return bindPlat;
	}

	public void setBindPlat(String bindPlat) {
		this.bindPlat = bindPlat;
	}

	@Column(name = "bind_name_")
	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	@Column(name = "shop_url_")
	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	@Column(name = "verify_goods_url_")
	public String getVerifyGoodsUrl() {
		return verifyGoodsUrl;
	}

	public void setVerifyGoodsUrl(String verifyGoodsUrl) {
		this.verifyGoodsUrl = verifyGoodsUrl;
	}

	@Column(name = "verify_code_")
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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
		result = prime * result + ((bindName == null) ? 0 : bindName.hashCode());
		result = prime * result + ((bindPlat == null) ? 0 : bindPlat.hashCode());
		result = prime * result + ((checkAdmin == null) ? 0 : checkAdmin.hashCode());
		result = prime * result + ((checkTime == null) ? 0 : checkTime.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((serverUser == null) ? 0 : serverUser.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((shopUrl == null) ? 0 : shopUrl.hashCode());
		result = prime * result + status;
		result = prime * result + ((verifyCode == null) ? 0 : verifyCode.hashCode());
		result = prime * result + ((verifyGoodsUrl == null) ? 0 : verifyGoodsUrl.hashCode());
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
		LLShop other = (LLShop) obj;
		if (bindName == null) {
			if (other.bindName != null)
				return false;
		} else if (!bindName.equals(other.bindName))
			return false;
		if (bindPlat == null) {
			if (other.bindPlat != null)
				return false;
		} else if (!bindPlat.equals(other.bindPlat))
			return false;
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
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (serverUser == null) {
			if (other.serverUser != null)
				return false;
		} else if (!serverUser.equals(other.serverUser))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (shopUrl == null) {
			if (other.shopUrl != null)
				return false;
		} else if (!shopUrl.equals(other.shopUrl))
			return false;
		if (status != other.status)
			return false;
		if (verifyCode == null) {
			if (other.verifyCode != null)
				return false;
		} else if (!verifyCode.equals(other.verifyCode))
			return false;
		if (verifyGoodsUrl == null) {
			if (other.verifyGoodsUrl != null)
				return false;
		} else if (!verifyGoodsUrl.equals(other.verifyGoodsUrl))
			return false;
		return true;
	}

}
