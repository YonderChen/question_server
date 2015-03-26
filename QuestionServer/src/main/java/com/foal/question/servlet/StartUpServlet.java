package com.foal.question.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.foal.question.config.Constant;
import com.foal.question.listener.ServiceLocator;
import com.foal.question.service.IGlobalConfigService;

public class StartUpServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(StartUpServlet.class);

	private static final long serialVersionUID = 6487993737859386918L;
	
	private static ServletContext context;

	public void destroy() {
		super.destroy();
	}
	
	public static Object getApplicationValue(String name) {
		return context.getAttribute(name);
	}

	public void init() throws ServletException {
		context = this.getServletContext();
		
		Constant.PRO_CTX_VALUE = this.getServletContext().getContextPath();
		this.getServletContext().setAttribute(Constant.PRO_CTX_KEY, Constant.PRO_CTX_VALUE);
		
		Constant.TOMCAT_SERVICE_ADDRESS = this.getServletContext().getRealPath("");
		
		Constant.DATA_LOGO_SAVE_PATH_VALUE = ServiceLocator.getMessage("logo.real.address");
		Constant.DATA_LOGO_WEB_PATH_VALUE = ServiceLocator.getMessage("logo.web.url");
		this.getServletContext().setAttribute(Constant.DATA_LOGO_WEB_PATH_KEY, Constant.DATA_LOGO_WEB_PATH_VALUE);
		
		IGlobalConfigService globalConfigService = ServiceLocator.getBean(IGlobalConfigService.class);
		// 执行增量脚本
		globalConfigService.runDbMigrate();
		globalConfigService.initSystemParam();
		
		logger.info("启动成功...");
	}
	
}
