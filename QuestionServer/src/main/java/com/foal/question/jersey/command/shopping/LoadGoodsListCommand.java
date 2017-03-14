package com.foal.question.jersey.command.shopping;

import java.util.List;

import com.foal.question.jersey.command.IShoppingCommand;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.pojo.ShoppingGoods;
import com.foal.question.service.shopping.ShoppingService;
import com.google.gson.JsonArray;

public class LoadGoodsListCommand implements IShoppingCommand {
	
	private ShoppingService shoppingService = ServiceLocator.getBean(ShoppingService.class);
	
	@Override
	public ResultMap handle(Param param) throws Exception {
		ResultMap ret = ResultMap.getResultMap();
		int page = param.getInt("page");
		int pageSize = param.getInt("pageSize");
		int goodType = param.getInt("goodType", 0);
		String title = param.get("title", "");
		String content = param.get("content", "");
		String sourceStore = param.get("sourceStore", "");
		List<ShoppingGoods> goodsList = shoppingService.getPublicRecord(page, pageSize, goodType, title, content, sourceStore);
		JsonArray goodsJa = new JsonArray();
		for (ShoppingGoods goods : goodsList) {
			goodsJa.add(goods.toJson());
		}
		ret.add("goodsJa", goodsJa);
		ret.setResult(RetCode.Success);
		return ret;
	}

}
