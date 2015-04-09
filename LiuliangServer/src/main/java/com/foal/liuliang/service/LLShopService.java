package com.foal.liuliang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.liuliang.dao.DaoSupport;

@SuppressWarnings("unchecked")
@Service(value = "llShopService")
public class LLShopService extends DaoSupport {

	public List queryLLShop(String userId) {
        String queryHql = "from LLShop as s where s.serverUser.userId = ?";
        return this.hibernateDao.queryList(queryHql, userId);
    }
}

