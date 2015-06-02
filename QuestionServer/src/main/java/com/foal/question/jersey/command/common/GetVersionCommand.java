package com.foal.question.jersey.command.common;

import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.service.SystemParamService;

public class GetVersionCommand implements ICommand {

	private SystemParamService systemParamService = ServiceLocator.getBean(SystemParamService.class);;
	
	@Override
	public ResultMap handle(Param param) {
		ResultMap ret = ResultMap.getResultMap();
		String version = param.getVersion();
		String versionNew = systemParamService.getSystemParam("client_version").getValue();
		ret.setResult(RetCode.Success);
		if (!versionNew.equals(version)) {
			ret.add("update", true);
			ret.add("version", versionNew);
			String versionInfo = systemParamService.getSystemParam("client_version_info").getValue();
			ret.add("version_info", versionInfo);
		} else {
			ret.add("update", false);
		}
		return ret;
	}

}
