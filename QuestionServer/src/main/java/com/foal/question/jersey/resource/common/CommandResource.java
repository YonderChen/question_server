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

import com.foal.question.config.QuestionException;
import com.foal.question.jersey.command.CommandRouter;
import com.foal.question.jersey.command.ICommand;
import com.foal.question.jersey.resource.tools.Param;
import com.foal.question.jersey.resource.tools.ResultMap;
import com.foal.question.jersey.resource.tools.APIConstants.RetCode;

@Component
@Path("/yjn")
public class CommandResource {

	private static final Logger logger = Logger.getLogger(CommandResource.class);
	
	@POST
	@Path("/post")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( { MediaType.TEXT_HTML })
	public String post(@Context HttpServletRequest request) {
		ResultMap ret = null;
		try {
			Param param = Param.getParam(request);
			short command = param.getCommand();
			ICommand service = CommandRouter.getHandlerService(command);
			if (service == null) {
				throw new QuestionException(QuestionException.UnKnowError);
			}
			ret = service.handle(param);
		} catch (QuestionException e) {
			logger.error("QuestionException", e);
			ret = ResultMap.getResultMap();
			ret.setResult(RetCode.Faild, "未知错误");
		} catch (Exception e) {
			logger.error("其他异常", e);
			ret = ResultMap.getResultMap();
			ret.setResult(RetCode.Faild, "未知错误");
		}
		return ret.toJson();
	}
}
