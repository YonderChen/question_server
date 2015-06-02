package com.foal.question.jersey.command;

import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;

public interface ICommand {
	public ResultMap handle(Param param) throws Exception;
	
	public static final short GetVersion = 10001;//获取版本信息
}
