package com.serpienteemplumada.views;


import java.util.List;


import javax.annotation.PostConstruct;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.repository.CategoryRepository;
import com.serpienteemplumada.views.beans.CategoriesViewBean;

@Scope("view")
@Component("categories")
public class CategoriesView extends CategoriesViewBean{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostConstruct
	public void init() {
		List<Category> cat = categoryRepository.selectAll();
		
		setCategories(cat);
	}

	
}
