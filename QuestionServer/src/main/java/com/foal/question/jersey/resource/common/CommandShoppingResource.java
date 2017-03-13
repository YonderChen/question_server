package com.foal.question.jersey.resource.common;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.foal.question.config.Constant;
import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.ShoppingCommand;
import com.foal.question.jersey.command.ShoppingCommandRouter;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;

@Component
@Path("/shopping")
public class CommandShoppingResource {

	private static final Logger logger = Logger.getLogger(CommandShoppingResource.class);
	
	@POST
	@Path("/post")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String post(@Context HttpServletRequest request) {
		ResultMap ret = null;
		Param param = null;
		try {
			param = Param.getParam(request);
			short command = param.getCommand();
			ShoppingCommand service = ShoppingCommandRouter.getHandlerService(command);
			if (service == null) {
				throw new QuestionException(QuestionException.CommandNotFound, "没有找到命令");
			}
			//请求参数日志
			if (Constant.LOG_PARAM_SWITCH > 0) {
				logger.info("req【" + command + "】" + param.toJson());
			}
			ret = service.handle(param);
			//返回结果日志
			if (Constant.LOG_RESULT_SWITCH > 0) {
				logger.info("res【" + command + "】" + ret.toJson());
			}
		} catch (QuestionException e) {
			logger.error("QuestionException", e);
			ret = ResultMap.getResultMap();
			ret.setResult(e.getStatusCode(), e.getStatusMsg());
		} catch (Exception e) {
			logger.error("其他异常", e);
			ret = ResultMap.getResultMap();
			ret.setResult(QuestionException.UnKnowError, "未知错误");
		}
		return ret.toJson();
	}
}
