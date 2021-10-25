package com.serpienteemplumada.views;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.views.beans.HomeViewBean;

@Scope("view")
@Component("home")
public class HomeView extends HomeViewBean{
	
	@PostConstruct
	public void init() {
		runner();
	}
	
	

}
