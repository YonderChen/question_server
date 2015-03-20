package com.meat.question.service;

import java.util.List;

import com.meat.question.bean.SystemParamBean;
import com.meat.question.pojo.SystemParam;

public interface ISystemParamService {
	public List<SystemParam> querySystemParam();
	public void updateSystemParam(SystemParamBean paramBean);
}