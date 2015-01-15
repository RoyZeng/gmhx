package com.gome.common.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringUtil  implements ServletContextListener{
	
	private static ServletContext context = null; 

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		this.context = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	public static Object getBean(String beanName){
		return WebApplicationContextUtils.getWebApplicationContext(context).getBean(beanName);
	}
	
	public static Object getBean(Class beanType){
		return WebApplicationContextUtils.getWebApplicationContext(context).getBean(beanType);
	}
	
	

}
