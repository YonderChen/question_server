package com.foal.liuliang.pojo;

import java.io.Serializable;

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
@Table(name = "ll_shop")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class LLShop implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6358906394155010990L;
	private String shopId;
	private ServerUser serverUser;
	private int shopType;
	private String shopOwnerAccount;
	private String shopUrl;
	private String verifyGoodsUrl;
	private String verifyCode;
	private int status;

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
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

	@Column(name = "shop_type_")
	public int getShopType() {
		return shopType;
	}

	public void setShopType(int shopType) {
		this.shopType = shopType;
	}

	@Column(name = "shop_owner_account_")
	public String getShopOwnerAccount() {
		return shopOwnerAccount;
	}

	public void setShopOwnerAccount(String shopOwnerAccount) {
		this.shopOwnerAccount = shopOwnerAccount;
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
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((serverUser == null) ? 0 : serverUser.hashCode());
		result = prime * result + shopType;
		result = prime * result + ((shopOwnerAccount == null) ? 0 : shopOwnerAccount.hashCode());
		result = prime * result + ((shopUrl == null) ? 0 : shopUrl.hashCode());
		result = prime * result + ((verifyGoodsUrl == null) ? 0 : verifyGoodsUrl.hashCode());
		result = prime * result + ((verifyCode == null) ? 0 : verifyCode.hashCode());
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
		LLShop other = (LLShop) obj;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (serverUser == null) {
			if (other.serverUser != null)
				return false;
		} else if (!serverUser.equals(other.serverUser))
			return false;
		if (shopType != other.shopType)
			return false;
		if (shopOwnerAccount == null) {
			if (other.shopOwnerAccount != null)
				return false;
		} else if (!shopOwnerAccount.equals(other.shopOwnerAccount))
			return false;
		if (shopUrl == null) {
			if (other.shopUrl != null)
				return false;
		} else if (!shopUrl.equals(other.shopUrl))
			return false;
		if (verifyGoodsUrl == null) {
			if (other.verifyGoodsUrl != null)
				return false;
		} else if (!verifyGoodsUrl.equals(other.verifyGoodsUrl))
			return false;
		if (verifyCode == null) {
			if (other.verifyCode != null)
				return false;
		} else if (!verifyCode.equals(other.verifyCode))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
