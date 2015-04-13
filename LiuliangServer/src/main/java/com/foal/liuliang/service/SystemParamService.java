package com.foal.liuliang.service;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.SystemParamBean;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.SystemParam;

@SuppressWarnings("unchecked")
@Service(value = "systemParamService")
public class SystemParamService extends DaoSupport {

	@Autowired
	private GlobalConfigService globalConfigService;
	
	public List<SystemParam> querySystemParam() {
		String queryHql = "from SystemParam as t order by t.createTime desc";
		return hibernateDao.queryList(queryHql);
	}

	public void updateSystemParam(SystemParamBean paramBean, ServletContext context) {
		SystemParam param = this.hibernateDao.get(SystemParam.class, paramBean.getParamId());
		param.setValue(paramBean.getValue());
		param.setModifyTime(new Date());
		this.hibernateDao.update(param);
		globalConfigService.initSystemParam(context);
	}
	
	public SystemParam getSystemParam(String paramId) {
		return this.hibernateDao.get(SystemParam.class, paramId);
	}
	
	public int getIntSystemParam(String paramId) {
		SystemParam sp = getSystemParam(paramId);
		return NumberUtils.toInt(sp.getValue());
	}
	
	public String getStringSystemParam(String paramId) {
		SystemParam sp = getSystemParam(paramId);
		return sp.getValue();
	}

}
