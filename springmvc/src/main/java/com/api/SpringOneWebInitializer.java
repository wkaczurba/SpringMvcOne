package com.api;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.web.WebConfig;

public class SpringOneWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public String[] getServletMappings() {
		return new String[]{ "/", "/hello" };
	}
	
	@Override
	public Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ RootConfig.class }; 
		// RootConfig -> scans .com 
	}
	
	@Override
	public Class<?>[] getServletConfigClasses() {
		System.out.println("Servlet config classes output.");
		return new Class<?>[] { WebConfig.class };
	}
}
