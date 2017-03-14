package com.foal.question.jersey.command;

import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;

public interface IShoppingCommand {
	public ResultMap handle(Param param) throws Exception;
	
	public static final short LoadGoodsListCommand = 10001;//加载商品列表
}
