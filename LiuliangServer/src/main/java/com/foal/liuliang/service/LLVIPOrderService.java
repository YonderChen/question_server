package com.foal.liuliang.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLDealOrderBean;
import com.foal.liuliang.bean.LLPayRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLVIPOrder;
import com.foal.liuliang.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "llVipOrderService")
public class LLVIPOrderService extends DaoSupport {
	
	@Autowired
	ServerUserService serverUserService;

	public LLVIPOrder getOrder(String id) {
		return this.hibernateDao.get(LLVIPOrder.class, id);
	}
	
	public void updateOrder(LLVIPOrder order) {
		this.hibernateDao.update(order);
	}
	
	public void add(LLDealOrderBean orderBean) {
		LLVIPOrder order = new LLVIPOrder();
		order.setServerUser(orderBean.getOperator());
		order.setNum(orderBean.getNum());
		order.setPrice(orderBean.getPrice());
		order.setPayImgUrl(orderBean.getPayImgUrl());
		order.setDealId(orderBean.getDealId());
		order.setReason(orderBean.getReason());
		order.setPayTime(orderBean.getPayTime());
		order.setCreateTime(new Date());
		order.setStatus(LLVIPOrder.Status.Create);
        this.hibernateDao.save(order);
    }

	public PageBean queryLLPayOrder(LLPayRecordBean payRecordBean) {
        String queryHql = "from LLVIPOrder as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(payRecordBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", payRecordBean.getUserId() );
        }
        if (payRecordBean.getStatus() >= 0) {
            queryHql += " and s.status = :status";
            paramMap.put("status", payRecordBean.getStatus() );
        }
        if (payRecordBean.getBeginTime() != null) {
            queryHql += " and s.createTime >= :beginTime";
            paramMap.put("beginTime", payRecordBean.getBeginTime() );
        }
        if (payRecordBean.getEndTime() != null) {
            queryHql += " and s.createTime <= :endTime";
            paramMap.put("endTime", payRecordBean.getEndTime() );
        }
        List list = this.hibernateDao.queryList(queryHql, payRecordBean.getPage(), payRecordBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, payRecordBean.getPage(), payRecordBean.getPageSize());
    }

	public PageBean queryLLScoreOrder(LLDealOrderBean llOrderBean) {
        String queryHql = "from LLVIPOrder as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llOrderBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llOrderBean.getUserId() );
        }
        if (!StringUtil.isEmpty(llOrderBean.getUsername())) {
            queryHql += " and s.serverUser.username = :username";
            paramMap.put("username", llOrderBean.getUsername() );
        }
        if (llOrderBean.getStatus() >= 0) {
            queryHql += " and s.status = :status";
            paramMap.put("status", llOrderBean.getStatus() );
        }
        if (llOrderBean.getBeginTime() != null) {
            queryHql += " and s.createTime >= :beginTime";
            paramMap.put("beginTime", llOrderBean.getBeginTime() );
        }
        if (llOrderBean.getEndTime() != null) {
            queryHql += " and s.createTime <= :endTime";
            paramMap.put("endTime", llOrderBean.getEndTime() );
        }
        List list = this.hibernateDao.queryList(queryHql, llOrderBean.getPage(), llOrderBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llOrderBean.getPage(), llOrderBean.getPageSize());
    }
	
	public LLVIPOrder updateVIPOrder(LLDealOrderBean llOrderBean, AjaxBean ajaxBean) {
		LLVIPOrder order = hibernateDao.get(LLVIPOrder.class, llOrderBean.getOrderId());
		if (order == null) {
			ajaxBean.setSuccess(false);
			ajaxBean.setMsg("找不到订单");
	        return null;
		}
		if (order.getStatus() == LLVIPOrder.Status.Success) {
			ajaxBean.setSuccess(false);
			ajaxBean.setMsg("订单当前状态不可编辑");
	        return order;
		}
		order.setNum(llOrderBean.getNum());
		if(llOrderBean.getStatus() == LLVIPOrder.Status.Success){
			order.setCheckAdmin(llOrderBean.getOperator());
			order.setCheckTime(new Date());
			order.setStatus(LLVIPOrder.Status.Success);
			//给用户推迟vip结束时间
			Date oldEndTime = order.getServerUser().getVipEndTime();
			if (oldEndTime == null) {
				oldEndTime = new Date();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(oldEndTime);
			cal.add(Calendar.MONTH, order.getNum());
			order.getServerUser().setVipEndTime(cal.getTime());
        	serverUserService.updateServerUser(order.getServerUser());
		} else if (llOrderBean.getStatus() == LLVIPOrder.Status.Create) {
			order.setStatus(LLVIPOrder.Status.Create);
		} else {
			order.setStatus(LLVIPOrder.Status.CheckFail);
		}
        this.updateOrder(order);
		ajaxBean.setSuccess(true);
		ajaxBean.setMsg("编辑成功");
        return order;
    }
}

