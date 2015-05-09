package com.foal.liuliang.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLScoreOrder;
import com.foal.liuliang.pojo.LLScoreRecord;
import com.foal.liuliang.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "llScoreOrderService")
public class LLScoreOrderService extends DaoSupport {
	
	public void add(LLDealOrderBean orderBean) {
		LLScoreOrder order = new LLScoreOrder();
		order.setServerUser(orderBean.getOperator());
		order.setNum(orderBean.getNum());
		order.setPrice(orderBean.getPrice());
		order.setPayImgUrl(orderBean.getPayImgUrl());
		order.setDealId(orderBean.getDealId());
		order.setReason(orderBean.getReason());
		order.setPayTime(orderBean.getPayTime());
		order.setCreateTime(new Date());
		order.setStatus(LLScoreOrder.Status.Create);
        this.hibernateDao.save(order);
    }

	public PageBean queryLLScoreOrder(LLDealOrderBean llOrderBean) {
        String queryHql = "from LLScoreOrder as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llOrderBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llOrderBean.getUserId() );
        }
        List list = this.hibernateDao.queryList(queryHql, llOrderBean.getPage(), llOrderBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llOrderBean.getPage(), llOrderBean.getPageSize());
    }
	
	public LLScoreOrder checkScoreOrder(LLDealOrderBean llOrderBean) {
		LLScoreOrder order = hibernateDao.get(LLScoreOrder.class, llOrderBean.getOrderId());
		if (order == null) {
			return null;
		}
		if (order.getStatus() == llOrderBean.getStatus()) {
			return order;
		}
		order.setCheckAdmin(llOrderBean.getOperator());
		order.setCheckTime(new Date());
		if(llOrderBean.getStatus() == LLScoreOrder.Status.Success){
			order.setStatus(LLScoreOrder.Status.Success);
			//给用户增加积分
			Date now = new Date();
			order.getServerUser().incScore(order.getNum());
			order.getServerUser().setModifyTime(now);
			this.hibernateDao.update(order.getServerUser());
			
			LLScoreRecord record = new LLScoreRecord();
			record.setServerUser(order.getServerUser());
			record.setNum(order.getNum());
			record.setType(LLScoreRecord.ScoreRecordType.Buy);
			record.setRemain(order.getServerUser().getScore());
			record.setCreateTime(now);
			record.setRemark("");
			this.hibernateDao.save(record);
		} else if (llOrderBean.getStatus() == LLScoreOrder.Status.Create) {
			order.setStatus(LLScoreOrder.Status.Create);
		} else {
			order.setStatus(LLScoreOrder.Status.CheckFail);
		}
        this.hibernateDao.update(order);
        return order;
    }
}

