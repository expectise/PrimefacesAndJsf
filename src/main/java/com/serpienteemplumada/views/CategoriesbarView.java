package com.serpienteemplumada.views;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.views.beans.CategoriesbarViewBean;

@Scope("view")
@Component("categoriesbar")
public class CategoriesbarView extends CategoriesbarViewBean{
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@PostConstruct
	public void init() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String l = req.getParameter("lang").toString();
		

		Language lang = languageRepository.selectLanguage(l);
		
		
		setIdLanguage(lang.getIdLanguage());
		setAcrom(lang.getAcrom());
		
		runner();
	}

}
