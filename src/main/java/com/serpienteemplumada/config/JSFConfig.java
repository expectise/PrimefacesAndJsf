package com.serpienteemplumada.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class JSFConfig {

    @Bean
    public ServletRegistrationBean jsfServletRegistration (ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setMultipartConfig(new MultipartConfigElement(""));
        srb.setLoadOnStartup(1);
        return srb;
    }
    
    @Bean
  	public static CustomScopeConfigurer customScopeConfigurer() {
  		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
  		configurer.setScopes(Collections.<String, Object>singletonMap(ViewScope.NAME, new ViewScope()));
  		return configurer;
  	}
    

}
