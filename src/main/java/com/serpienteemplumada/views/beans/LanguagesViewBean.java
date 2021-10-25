package com.serpienteemplumada.views.beans;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.LanguageRepository;

import lombok.Getter;
import lombok.Setter;

public abstract class LanguagesViewBean {
	@Getter
	@Setter
	private List<Language> languages;
	
	@Autowired
	private LanguageRepository languageRepository;
	

	
	
	@PostConstruct
	public void init() {
		languages = languageRepository.selectAll();
	}
	
	
	public void showAddPopup() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("add", options, null);
		
	}
	
	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      
	      if (out == 1) {
	    	  languages = languageRepository.selectAll();
	      }else if(out == 2) {
	    	  languages = languageRepository.selectAll();
	      }
	  }
	 
	 public void onSelected(Language lang)
	 {
	 
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("language", lang);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("update", options, null);
	 }
	 
	 
	 public void onActivate(Language lang)
	 {
		 Language language = languageRepository.findById(lang.getIdLanguage()).orElse(null);
		 
		 if (language != null) {
			 boolean active = language.getActive();
			 
			 language.setActive(!active);
			 
			 languageRepository.save(language);
			 
			 languages = languageRepository.selectAll();
		 }
	 }

}
