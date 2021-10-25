package com.serpienteemplumada.views;


import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.views.beans.ArtistpageViewBean;

@Scope("view")
@Component("artistpage")
public class ArtistpageView extends ArtistpageViewBean{
	

	@PostConstruct
	public void init() {
		runner();
	}
}
