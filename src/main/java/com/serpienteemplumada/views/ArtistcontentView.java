package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.views.beans.ArtistcontentViewBean;

@Scope("view")
@Component("artistcontent")
public class ArtistcontentView extends ArtistcontentViewBean{

	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Long input = (Long) sessionMap.get("idArtists");
		
		if (input != null) {
			
			runner(input);
			
			sessionMap.remove("idArtists");
		}
	
	}

}
