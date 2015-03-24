package com.foal.question.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foal.question.bean.SystemParamBean;
import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.SystemParam;
import com.foal.question.service.IGlobalConfigService;
import com.foal.question.service.ISystemParamService;

@SuppressWarnings("unchecked")
@Service(value = "systemParamService")
public class SystemParamServiceImpl extends DaoSupport implements ISystemParamService {

	@Autowired
	private IGlobalConfigService globalConfigService;
	
	public List<SystemParam> querySystemParam() {
		String queryHql = "from SystemParam as t order by t.createTime desc";
		return hibernateDao.queryList(queryHql);
	}

	public void updateSystemParam(SystemParamBean paramBean) {
		SystemParam param = this.hibernateDao.get(SystemParam.class, paramBean.getParamId());
		param.setValue(paramBean.getValue());
		param.setModifyTime(new Date());
		this.hibernateDao.update(param);
		globalConfigService.initSystemParam();
	}

}
