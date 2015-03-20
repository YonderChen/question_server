package com.meat.question.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.meat.question.service.akka.AkkaService;
import com.meat.question.service.elasticsearch.ElasticsearchService;

public class ApplicationContextListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ServiceLocator.setApplicationContext(context);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextDestroyed(event);
		AkkaService.getInstance().dispose();
		ElasticsearchService.getInstance().stop();
	}

}
