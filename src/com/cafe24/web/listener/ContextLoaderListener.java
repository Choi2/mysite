package com.cafe24.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// @WebListener
public class ContextLoaderListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 

    }


    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
        System.out.println("Container Starts..." + contextConfigLocation);
    }
	
}
