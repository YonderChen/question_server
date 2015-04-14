package com.foal.liuliang.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLVIPOrder;
import com.foal.liuliang.util.StringUtil;

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

	public PageBean queryLLScoreOrder(LLDealOrderBean llOrderBean) {
        String queryHql = "from LLVIPOrder as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llOrderBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llOrderBean.getUserId() );
        }
        List list = this.hibernateDao.queryList(queryHql, llOrderBean.getPage(), llOrderBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llOrderBean.getPage(), llOrderBean.getPageSize());
    }
	
	public int checkVIPOrder(LLDealOrderBean llOrderBean) {
		LLVIPOrder order = hibernateDao.get(LLVIPOrder.class, llOrderBean.getOrderId());
		order.setCheckAdmin(llOrderBean.getOperator());
		order.setCheckTime(new Date());
		if(llOrderBean.getStatus() == Constant.Status.Success){
			order.setStatus(Constant.Status.Success);
			//给用户推迟vip结束时间
			Date oldEndTime = order.getServerUser().getVipEndTime();
			if (oldEndTime == null) {
				oldEndTime = new Date();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(oldEndTime);
			cal.add(Calendar.MONTH, order.getNum());
			order.getServerUser().setVipEndTime(cal.getTime());
			this.hibernateDao.update(order.getServerUser());
		} else if (llOrderBean.getStatus() == Constant.Status.Create) {
			order.setStatus(Constant.Status.Create);
		} else {
			order.setStatus(Constant.Status.CheckFail);
		}
        this.hibernateDao.update(order);
        return order.getStatus();
    }
}

