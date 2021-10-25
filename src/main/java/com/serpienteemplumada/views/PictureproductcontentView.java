package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.PictureproductDTO;
import com.serpienteemplumada.views.beans.PictureproductcontentViewBean;

@Scope("view")
@Component("pictureproductcontent")
public class PictureproductcontentView extends PictureproductcontentViewBean{

	@PostConstruct
	public void init() {
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		PictureproductDTO input = (PictureproductDTO) sessionMap.get("pictureproduct");
		
		if (input != null) {
			
			setPictureproductDTO(input);
			
			runner();
			
			sessionMap.remove("pictureproduct");
		}
	}
}
