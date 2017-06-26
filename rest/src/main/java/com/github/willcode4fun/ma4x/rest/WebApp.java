package com.github.willcode4fun.ma4x.rest;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebApp extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{BusinessConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/rest/*" };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        servletContext.addListener(new RequestContextListener());

        //FilterRegistration.Dynamic staticResourcesFilter = servletContext.addFilter("StaticResourcesFilter", new DelegatingFilterProxy());
        //staticResourcesFilter.addMappingForUrlPatterns(null, false, "/*");

    }
    
    @Override
    protected Filter[] getServletFilters() {
		return new Filter[]{};
    }
}
