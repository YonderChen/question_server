package com.foal.liuliang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLShop;

@SuppressWarnings("unchecked")
@Service(value = "llShopService")
public class LLShopService extends DaoSupport {

	public void add(LLShopBean shopBean) {
		LLShop llShop = new LLShop();
		llShop.setServerUser(shopBean.getOperator());
		llShop.setBindPlat(shopBean.getBindPlat());
		llShop.setBindName(shopBean.getBindName());
		llShop.setShopUrl(shopBean.getShopUrl());
		llShop.setVerifyCode(shopBean.getVerifyCode());
		llShop.setVerifyGoodsUrl(shopBean.getVerifyGoodsUrl());
		llShop.setStatus(Constant.Status.Create);
        this.hibernateDao.save(llShop);
    }
	
	public List queryLLShop(String userId) {
        String queryHql = "from LLShop as s where s.serverUser.userId = ?";
        return this.hibernateDao.queryList(queryHql, userId);
    }
}

