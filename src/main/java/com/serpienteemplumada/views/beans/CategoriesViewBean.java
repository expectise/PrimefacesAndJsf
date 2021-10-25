package com.serpienteemplumada.views.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.repository.CategoryRepository;

import lombok.Data;

@Data
public abstract class CategoriesViewBean {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	private List<Category> categories;
	
	
	public void showAddPopup() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("addcategory", options, null);
		
	}
	
	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      if (out == 1) {
	    	  categories = categoryRepository.selectAll();
	      }else if(out == 2) {
	    	  categories = categoryRepository.selectAll();
	      }else {
	    	  categories = categoryRepository.selectAll();
	      }
	  }
	 
	 public void onSelected(Category category)
	 {
	 
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("category", category);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("updatecategory", options, null);
	 }
	 
	 public void onActivate(Category categorie)
	 {
		 	Category category = categoryRepository.findById(categorie.getIdCategory()).orElse(null);
		 
		 if (category != null) {
			 boolean active = category.getActive();
			 
			 category.setActive(!active);
			 
			 categoryRepository.save(category);
			 
			 categories = categoryRepository.selectAll();
		 }
	 }

}
