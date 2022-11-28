package com.abhijit.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
      Class[] configClass= {MyAppConfig.class};
		return configClass;
	}

	@Override
	protected String[] getServletMappings() {
		String[] mappings= {"/"};
		return mappings;
	}

}
