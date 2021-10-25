package com.serpienteemplumada.views.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.CategorycontentDTO;
import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.model.Categorycontent;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.CategoryRepository;
import com.serpienteemplumada.repository.CategorycontentRepository;
import com.serpienteemplumada.repository.LanguageRepository;

import lombok.Data;

@Data
public abstract class CategoryViewBean {
	private Date created;
	private Date updated;
	private String title;
	
	private Category category;
	
	private List<CategorycontentDTO> categorycontentDTO;
	
	
	@Autowired
	private CategorycontentRepository categorycontentRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private LanguageRepository languageRepository;

	protected void runner(Long id) {
		List<Categorycontent> list = categorycontentRepository.selectCategorycontent(id);
		
		List<CategorycontentDTO> lsout = new ArrayList<>();
		
		for (Categorycontent c : list) {
			CategorycontentDTO out = new CategorycontentDTO();
			out.setIdCategorycontent(c.getIdCategorycontent());
			out.setLanguage(c.getLanguage().getLanguage());
			out.setTitle(c.getTitle());
			
			lsout.add(out);
		}
		
		setCategorycontentDTO(lsout);
	}
	
	public void submit()  {
		
		Category category = new Category();
		
		category.setActive(true);
		category.setCreated(new Date());
		category.setTitle(getTitle());
	
		Category c = categoryRepository.save(category);
		
		List<Language> language = languageRepository.selectAll();
		
		for (Language l : language) {
			
			Categorycontent cct = new Categorycontent();
			
			cct.setActive(true);
			cct.setCreated(new Date());
			cct.setIdCategory(c.getIdCategory());
			cct.setIdLanguage(l.getIdLanguage());
			
			categorycontentRepository.save(cct);
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	}
	
	public void cancel() {
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
	
	public void update() {
		Category cc = categoryRepository.findById(category.getIdCategory()).orElse(null);
		

		if (cc != null) {
			cc.setUpdated(new Date());
			cc.setTitle(title);
			
			categoryRepository.save(cc);
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(2);
	
	}
	
	public void onSelected(CategorycontentDTO categorycontentDTO) {
		 Map<String,Object> options = new HashMap<String, Object>();
	      options.put("resizable", false);
	      options.put("positionType", "absolute");
	      options.put("styleClass", "dialogwindow");
	      
	      Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	      sessionMap.put("categorycontentDTO", categorycontentDTO);
	      
	      PrimeFaces instance = PrimeFaces.current();
		   instance.dialog().openDynamic("updatecategorycontent", options, null);
	}
	
	public void onCloseDialog(SelectEvent selectEvent) {
	    int out = (int) selectEvent.getObject();
	    if (out == 1) {
	    	runner(category.getIdCategory());
	    }else if(out == 2) {
	    	runner(category.getIdCategory());
	    }
	}

	
}
