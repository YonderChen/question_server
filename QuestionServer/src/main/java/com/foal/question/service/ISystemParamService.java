package com.foal.question.service;

import java.util.List;

import com.foal.question.bean.SystemParamBean;
import com.foal.question.pojo.SystemParam;

public interface ISystemParamService {
	public List<SystemParam> querySystemParam();
	public void updateSystemParam(SystemParamBean paramBean);
}