package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.CountrycontentDTO;
import com.serpienteemplumada.views.beans.CountriecontentViewBean;

@Scope("view")
@Component("countriecontent")
public class CountrycontentView extends CountriecontentViewBean{
	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		CountrycontentDTO input = (CountrycontentDTO) sessionMap.get("countrycontentDTO");
		
		if (input != null) {
			setCountrycontentDTO(input);
			sessionMap.remove("countrycontentDTO");
		}
		
	}

}
