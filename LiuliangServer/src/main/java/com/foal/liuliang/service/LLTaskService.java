package com.foal.liuliang.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLTaskBean;
import com.foal.liuliang.bean.LLTaskRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLScoreRecord;
import com.foal.liuliang.pojo.LLShop;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.util.StringTools;
import com.foal.liuliang.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "llTaskService")
public class LLTaskService extends DaoSupport {
	
	public LLTask getLLTask(String taskId) {
		return this.hibernateDao.get(LLTask.class, taskId);
	}
	
	public boolean add(LLTaskBean llTaskBean, ServerUser user) {
		int countOrderOneDay = llTaskBean.getOrderNumberOneDay1() 
			+ llTaskBean.getOrderNumberOneDay2() 
			+ llTaskBean.getOrderNumberOneDay3() 
			+ llTaskBean.getOrderNumberOneDay4() 
			+ llTaskBean.getOrderNumberOneDay5();
		int countOrder = countOrderOneDay * llTaskBean.getDurationDay();
		int costScore = countOrder * Constant.OneVisitCostScore 
			+ Constant.PageStayCostScoreMap.get(String.valueOf(llTaskBean.getPageStayType()))
			+ Constant.VisitTimeCostScoreMap.get(String.valueOf(llTaskBean.getVisitTimeType()));
		if (llTaskBean.getIsQuickVerify() > 0) {
			costScore += Constant.QuickVerifyCostScore;
		}
		if (llTaskBean.getIsQuickExecute() > 0) {
			costScore += Constant.QuickExecuteCostScore;
		}
		ServerUser serverUser = this.hibernateDao.get(ServerUser.class, user.getUserId());
		if (user.getScore() < costScore) {//积分不足
			return false;
		}
		
		Date now = new Date();
		
		user.costScore(costScore);
		user.incScoreUsed(costScore);
		serverUser.costScore(costScore);
		serverUser.incScoreUsed(costScore);
		serverUser.setModifyTime(now);
		this.hibernateDao.update(serverUser);
		
		LLScoreRecord record = new LLScoreRecord();
		record.setServerUser(serverUser);
		record.setNum(costScore);
		record.setType(Constant.ScoreRecordType.Cost);
		record.setRemain(serverUser.getScore());
		record.setCreateTime(now);
		record.setRemark("");
		this.hibernateDao.save(record);
		
		llTaskBean.setCostScore(costScore);
		LLTask llTask = new LLTask();
		llTask.setServerUser(llTaskBean.getOperator());
		llTask.setLlShop(this.hibernateDao.get(LLShop.class, llTaskBean.getShopId()));
		llTask.setGoodsUrl(llTaskBean.getGoodsUrl());
		llTask.setGoodsName(llTaskBean.getGoodsName());
		llTask.setGoodsImg(llTaskBean.getGoodsImg());
		if (StringTools.isBlank(llTaskBean.getKeyword1())) {
			llTask.setKeyword1("");
		} else {
			llTask.setKeyword1(llTaskBean.getKeyword1());
		}
		llTask.setOrderNumberOneDay1(llTaskBean.getOrderNumberOneDay1());
		if (StringTools.isBlank(llTaskBean.getKeyword2())) {
			llTask.setKeyword2("");
		} else {
			llTask.setKeyword2(llTaskBean.getKeyword2());
		}
		llTask.setOrderNumberOneDay2(llTaskBean.getOrderNumberOneDay2());
		if (StringTools.isBlank(llTaskBean.getKeyword3())) {
			llTask.setKeyword3("");
		} else {
			llTask.setKeyword3(llTaskBean.getKeyword3());
		}
		llTask.setOrderNumberOneDay3(llTaskBean.getOrderNumberOneDay3());
		if (StringTools.isBlank(llTaskBean.getKeyword4())) {
			llTask.setKeyword4("");
		} else {
			llTask.setKeyword4(llTaskBean.getKeyword4());
		}
		llTask.setOrderNumberOneDay4(llTaskBean.getOrderNumberOneDay4());
		if (StringTools.isBlank(llTaskBean.getKeyword5())) {
			llTask.setKeyword5("");
		} else {
			llTask.setKeyword5(llTaskBean.getKeyword5());
		}
		llTask.setOrderNumberOneDay5(llTaskBean.getOrderNumberOneDay5());
		llTask.setSearchSource(llTaskBean.getSearchSource());
		llTask.setDurationDay(llTaskBean.getDurationDay());
		llTask.setPageStayType(llTaskBean.getPageStayType());
		llTask.setVisitTimeType(llTaskBean.getVisitTimeType());
		llTask.setIsQuickVerify(llTaskBean.getIsQuickVerify());
		llTask.setIsQuickExecute(llTaskBean.getIsQuickExecute());
		llTask.setCostScore(llTaskBean.getCostScore());
		llTask.setCreateTime(now);
		llTask.setStatus(Constant.Status.Create);
        this.hibernateDao.save(llTask);
        return true;
    }
	
	public PageBean queryLLTask(LLTaskBean llTaskBean) {
        String queryHql = "from LLTask as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llTaskBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llTaskBean.getUserId() );
        }
        List list = this.hibernateDao.queryList(queryHql, llTaskBean.getPage(), llTaskBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llTaskBean.getPage(), llTaskBean.getPageSize());
    }
	
	public PageBean queryLLTaskRecord(LLTaskRecordBean llTaskRecordBean) {
        String queryHql = "from LLTask as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llTaskRecordBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llTaskRecordBean.getUserId() );
        }
        if (!StringUtil.isEmpty(llTaskRecordBean.getBindPlat())) {
            queryHql += " and s.llShop.bindPlat = :bindPlat";
            paramMap.put("bindPlat", llTaskRecordBean.getBindPlat() );
        }
        if (!StringUtil.isEmpty(llTaskRecordBean.getShopId())) {
            queryHql += " and s.llShop.shopId = :shopId";
            paramMap.put("shopId", llTaskRecordBean.getShopId() );
        }
        if (!StringUtil.isEmpty(llTaskRecordBean.getTaskType())) {
            queryHql += " and s.taskType = :taskType";
            paramMap.put("taskType", NumberUtils.toInt(llTaskRecordBean.getTaskType(), 0) );
        }
        if (!StringUtil.isEmpty(llTaskRecordBean.getStatus())) {
            queryHql += " and s.status = :status";
            paramMap.put("status", NumberUtils.toInt(llTaskRecordBean.getStatus(), 0) );
        }
        if (llTaskRecordBean.getBeginTime() != null) {
            queryHql += " and s.createTime >= :beginTime";
            paramMap.put("beginTime", llTaskRecordBean.getBeginTime() );
        }
        if (llTaskRecordBean.getEndTime() != null) {
            queryHql += " and s.createTime <= :endTime";
            paramMap.put("endTime", llTaskRecordBean.getEndTime() );
        }
        if (!StringUtil.isEmpty(llTaskRecordBean.getTaskId())) {
            queryHql += " and s.taskId = :taskId";
            paramMap.put("taskId", llTaskRecordBean.getTaskId() );
        }
        if (!StringUtil.isEmpty(llTaskRecordBean.getGoodsName())) {
            queryHql += " and s.goodsName = :goodsName";
            paramMap.put("goodsName", llTaskRecordBean.getGoodsName() );
        }
        List list = this.hibernateDao.queryList(queryHql, llTaskRecordBean.getPage(), llTaskRecordBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llTaskRecordBean.getPage(), llTaskRecordBean.getPageSize());
    }
}

