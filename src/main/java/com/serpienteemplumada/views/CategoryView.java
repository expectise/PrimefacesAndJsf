package com.serpienteemplumada.views;


import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.serpienteemplumada.model.Category;

import com.serpienteemplumada.views.beans.CategoryViewBean;

@Scope("view")
@Component("category")
public class CategoryView extends CategoryViewBean{

	@PostConstruct
	public void init() {
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Category input = (Category) sessionMap.get("category");
		
		if (input != null) {
			setCategory(input);
			
			setUpdated(input.getUpdated());
			
			setCreated(input.getCreated());
			
			setTitle(input.getTitle());
			
			runner(input.getIdCategory());
			
			sessionMap.remove("category");
		}
		
	}
		
	
}
