package com.serpienteemplumada.views.beans;

import java.util.Date;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.CategorycontentDTO;
import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.model.Categorycontent;
import com.serpienteemplumada.repository.CategoryRepository;
import com.serpienteemplumada.repository.CategorycontentRepository;

import lombok.Data;

@Data
public abstract class CategorycontentViewBean {
	
	private CategorycontentDTO categorycontentDTO;
	
	@Autowired
	private CategorycontentRepository categorycontentRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void submit()  {
		
		Categorycontent ccontent = categorycontentRepository.findById(categorycontentDTO.getIdCategorycontent()).orElse(null);
		
		if(ccontent != null) {
			
			ccontent.setUpdated(new Date());
			ccontent.setTitle(categorycontentDTO.getTitle());

			Category cat = categoryRepository.findById(ccontent.getIdCategory()).orElse(null);
			
			cat.setUpdated(new Date());
			
			categoryRepository.save(cat);
					
			categorycontentRepository.save(ccontent);
			
			
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	}
	
	public void cancel() {
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}

}
