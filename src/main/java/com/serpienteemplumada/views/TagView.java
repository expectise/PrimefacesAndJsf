package com.serpienteemplumada.views;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.model.Tags;
import com.serpienteemplumada.repository.CategoryRepository;
import com.serpienteemplumada.views.beans.TagViewBean;

@Scope("view")
@Component("tag")
public class TagView extends TagViewBean{
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Tags input = (Tags) sessionMap.get("tags");
		

		if (input != null) {
			
			setTag(input);
			
			setUpdated(input.getUpdated());
			
			setCreated(input.getCreated());
			
			setTitle(input.getTitle());
			
			setCategory(input.getCategory());
			
			runner(input.getIdTags());
			
			sessionMap.remove("tags");
		}
		
		List<Category> lista = categoryRepository.selectAll();
		
		setCategories(lista);
	}
}
