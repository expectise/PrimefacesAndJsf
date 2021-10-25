package com.serpienteemplumada.views;


import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.views.beans.CountrieViewBean;

@Scope("view")
@Component("countrie")
public class CountrieView extends CountrieViewBean{

	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Countries input = (Countries) sessionMap.get("countrie");
		
		if (input != null) {
			
			setCountries(input);
			
			setPrefix(input.getPrefix());
			
			setShipPrice(input.getShipPrice().toString());
			
			setExcentPrice(input.getExcentPrice().toString());
			
			setUpdated(input.getUpdated());
			
			setCreated(input.getCreated());
			
			runner(input.getIdCountries());
			
			
			sessionMap.remove("countrie");
			
		}
	}

}
