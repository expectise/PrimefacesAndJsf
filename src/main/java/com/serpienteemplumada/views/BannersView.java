package com.serpienteemplumada.views;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.views.beans.BannersViewBean;

@Scope("view")
@Component("banners")
public class BannersView extends BannersViewBean{
	
	@PostConstruct
	public void init() {
		runner();
	}

}
