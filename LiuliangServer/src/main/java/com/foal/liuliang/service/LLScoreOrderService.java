package com.foal.liuliang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLScoreOrder;

@SuppressWarnings("unchecked")
@Service(value = "llScoreOrderService")
public class LLScoreOrderService extends DaoSupport {
	
	public void add(LLDealOrderBean orderBean) {
		LLScoreOrder order = new LLScoreOrder();
		order.setServerUser(orderBean.getOperator());
		order.setNum(orderBean.getNum());
		order.setPrice(orderBean.getPrice());
		order.setDealId(orderBean.getDealId());
		order.setStatus(Constant.Status.Create);
        this.hibernateDao.save(order);
    }

	public PageBean queryLLScoreOrder(String userId, LLDealOrderBean llOrderBean) {
        String queryHql = "from LLScoreOrder as s where s.serverUser.userId = ?";
        List list = this.hibernateDao.queryList(queryHql, llOrderBean.getPage(), llOrderBean.getPageSize(), userId);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, userId);
		return new PageBean(list, allRow, llOrderBean.getPage(), llOrderBean.getPageSize());
    }
}

