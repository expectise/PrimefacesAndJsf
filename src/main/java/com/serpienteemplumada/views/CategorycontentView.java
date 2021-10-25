package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.CategorycontentDTO;
import com.serpienteemplumada.views.beans.CategorycontentViewBean;

@Scope("view")
@Component("categorycontent")
public class CategorycontentView extends CategorycontentViewBean{
	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		CategorycontentDTO input = (CategorycontentDTO) sessionMap.get("categorycontentDTO");
		
		if (input != null) {
			setCategorycontentDTO(input);
			sessionMap.remove("categorycontentDTO");
		}
		
	}
}
