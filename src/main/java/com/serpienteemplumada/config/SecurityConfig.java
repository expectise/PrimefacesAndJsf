package com.serpienteemplumada.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    // require all requests to be authenticated except for the resources
		 http.authorizeRequests().antMatchers("/*").permitAll()
		 .and().authorizeRequests().antMatchers("/images/**").permitAll()
		 .and().authorizeRequests().antMatchers("/es/**").permitAll()
		 .and().authorizeRequests().antMatchers("/js/**").permitAll()
		 .and().authorizeRequests().antMatchers("/fonts/**").permitAll()
		 .and().authorizeRequests().antMatchers("/css/**").permitAll()
		 .and().authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll()
		  
		 
		 
	    .and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
	     .anyRequest().authenticated();
	     http.headers().frameOptions().sameOrigin();
	    // login
	    http.formLogin().loginPage("/admin/").permitAll()
	        .failureUrl("/admin/?error=true")
	        .defaultSuccessUrl("/admin/home", true)
	        .loginProcessingUrl("/admin/login");
	    

	   
	    
	    // logout
	    http.logout().logoutSuccessUrl("/admin/index.xhtml");
	    // not needed as JSF 2.2 is implicitly protected against CSRF
	    //http.csrf().disable();
	    
	  
	  }

	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth)
	      throws Exception {
	    auth.inMemoryAuthentication().withUser("admin")
	        .password("{noop}").roles("ADMIN");
	  }
}
