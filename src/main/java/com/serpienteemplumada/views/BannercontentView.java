package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.BannersDTO;
import com.serpienteemplumada.views.beans.BannercontentViewBean;

@Scope("view")
@Component("bannercontent")
public class BannercontentView extends BannercontentViewBean{
	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		BannersDTO input = (BannersDTO) sessionMap.get("bannersDTO");
		
		if (input != null) {
			setBannersDTO(input);
			
			runner();
			
			sessionMap.remove("bannersDTO");
		}
	}

}
