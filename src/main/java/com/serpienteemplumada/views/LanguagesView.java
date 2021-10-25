package com.serpienteemplumada.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.views.beans.LanguagesViewBean;


@Scope("view")
@Component("languages")
public class LanguagesView extends LanguagesViewBean{
	@Autowired
	private LanguageRepository languageRepository;
	
	
	@PostConstruct
	public void init() {
		List<Language> languages = languageRepository.selectAll();
		this.setLanguages(languages);
	}
}
