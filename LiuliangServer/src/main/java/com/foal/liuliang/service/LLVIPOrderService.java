package com.foal.liuliang.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLVIPOrder;

@SuppressWarnings("unchecked")
@Service(value = "llVipOrderService")
public class LLVIPOrderService extends DaoSupport {
	
	public void add(LLDealOrderBean orderBean) {
		LLVIPOrder order = new LLVIPOrder();
		order.setServerUser(orderBean.getOperator());
		order.setNum(orderBean.getNum());
		order.setPrice(orderBean.getPrice());
		order.setDealId(orderBean.getDealId());
		order.setCreateTime(new Date());
		order.setStatus(Constant.Status.Create);
        this.hibernateDao.save(order);
    }

	public PageBean queryLLScoreOrder(String userId, LLDealOrderBean llOrderBean) {
        String queryHql = "from LLVIPOrder as s where s.serverUser.userId = ?";
        List list = this.hibernateDao.queryList(queryHql, llOrderBean.getPage(), llOrderBean.getPageSize(), userId);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, userId);
		return new PageBean(list, allRow, llOrderBean.getPage(), llOrderBean.getPageSize());
    }
}

