package com.foal.liuliang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLScoreRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLScoreRecord;
import com.foal.liuliang.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "llScoreRecordService")
public class LLScoreRecordService extends DaoSupport {
	
	public void add(LLScoreRecord record) {
        this.hibernateDao.save(record);
    }

	public PageBean queryLLScoreRecord(LLScoreRecordBean llScoreRecordBean) {
        String queryHql = "from LLScoreRecord as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llScoreRecordBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llScoreRecordBean.getUserId() );
        }
        if (llScoreRecordBean.getType() != 0) {
            queryHql += " and s.type = :type";
            paramMap.put("type", llScoreRecordBean.getType() );
		}
        if (llScoreRecordBean.getBeginTime() != null) {
            queryHql += " and s.createTime >= :beginTime";
            paramMap.put("beginTime", llScoreRecordBean.getBeginTime() );
        }
        if (llScoreRecordBean.getEndTime() != null) {
            queryHql += " and s.createTime <= :endTime";
            paramMap.put("endTime", llScoreRecordBean.getEndTime() );
        }
        queryHql += " order by s.createTime desc";
        List list = this.hibernateDao.queryList(queryHql, llScoreRecordBean.getPage(), llScoreRecordBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llScoreRecordBean.getPage(), llScoreRecordBean.getPageSize());
    }

	public List queryExportLLScoreRecord(LLScoreRecordBean llScoreRecordBean) {
        String queryHql = "from LLScoreRecord as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llScoreRecordBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llScoreRecordBean.getUserId() );
        }
        if (llScoreRecordBean.getType() != 0) {
            queryHql += " and s.type = :type";
            paramMap.put("type", llScoreRecordBean.getType() );
		}
        if (llScoreRecordBean.getBeginTime() != null) {
            queryHql += " and s.createTime >= :beginTime";
            paramMap.put("beginTime", llScoreRecordBean.getBeginTime() );
        }
        if (llScoreRecordBean.getEndTime() != null) {
            queryHql += " and s.createTime <= :endTime";
            paramMap.put("endTime", llScoreRecordBean.getEndTime() );
        }
        return this.hibernateDao.queryList(queryHql, paramMap);
    }
	
}

