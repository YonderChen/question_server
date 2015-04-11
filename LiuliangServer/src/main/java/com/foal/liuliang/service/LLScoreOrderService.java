package com.foal.liuliang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLScoreOrderBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLScoreOrder;

@SuppressWarnings("unchecked")
@Service(value = "llScoreOrderService")
public class LLScoreOrderService extends DaoSupport {
	
	public void add(LLScoreOrderBean orderBean) {
		LLScoreOrder order = new LLScoreOrder();
		order.setServerUser(orderBean.getOperator());
		order.setScoreNum(orderBean.getScoreNum());
		order.setPrice(orderBean.getPrice());
		order.setDealId(orderBean.getDealId());
		order.setStatus(Constant.Status.Create);
        this.hibernateDao.save(order);
    }

	public List queryLLScoreOrder(String userId) {
        String queryHql = "from LLScoreOrder as s where s.serverUser.userId = ?";
        return this.hibernateDao.queryList(queryHql, userId);
    }
}

