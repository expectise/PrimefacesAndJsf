package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.views.beans.LanguageViewBean;

@Scope("view")
@Component("language")
public class LanguageView extends LanguageViewBean{
		
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	    Language input = (Language) sessionMap.get("language");
	    
	    if (input != null) {
	    	
	        setLangobj(input);
	    	sessionMap.remove("language");
	    	
	    	String ext;
	    	
	    	if (getLangobj().getType().contains("jpeg")) {
				ext="jpg";
			}else{
				ext="png";
			}
	    	
	    	setLanguage(getLangobj().getLanguage());
	    	setAcrom(getLangobj().getAcrom());
	    	setUrl("../../images/icons/"+getLangobj().getIdLanguage()+"/"+getLangobj().getAcrom()+"."+ext);
	    }
	    
	    
	}
}
