package com.foal.liuliang.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.foal.liuliang.config.Constant;
import com.foal.liuliang.listener.ServiceLocator;
import com.foal.liuliang.service.GlobalConfigService;

public class StartUpServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7706053448796526088L;

	private final Logger logger = Logger.getLogger(StartUpServlet.class);

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
		
		Constant.CONTEXT_WEB_URL = ServiceLocator.getMessage("context.web.url");
		this.getServletContext().setAttribute(Constant.CONTEXT_WEB_URL_KEY, Constant.CONTEXT_WEB_URL);
		
		GlobalConfigService globalConfigService = ServiceLocator.getBean(GlobalConfigService.class);
		// 执行增量脚本
		globalConfigService.runDbMigrate();
		globalConfigService.initSystemParam(this.getServletContext());
		
		logger.info("启动成功...");
	}
	
}
