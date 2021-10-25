package com.serpienteemplumada.views.beans;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.scheduler.CheckLanguages;
import com.serpienteemplumada.utils.CopyFile;
import com.serpienteemplumada.utils.PathsProps;

import lombok.Data;


@Data
public abstract class LanguageViewBean {
	
	private UploadedFile icon;
	
	private String acrom;
	
	private String language;
	
	private Language langobj;
	
	private String url;
	
	@Autowired
	private CopyFile copyFile;
	 
	@Autowired
	private PathsProps pathsProps;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private CheckLanguages checkLanguages;
	
	
	public void submit() throws IOException {
		
		String ext;
		
		if (icon.getContentType().contains("jpeg")) {
			ext="jpg";
		}else{
			ext="png";
		}
	
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
		
		copyFile.copyFile("icons", name, icon.getInputStream(), pathsProps.getProperties().getImgRoot());
	
		Language lang = new Language();
		
		lang.setAcrom(acrom);
		lang.setCreated(new Date());
		lang.setIcon(name);
		lang.setOrgName(icon.getFileName());
		lang.setType(icon.getContentType());
		lang.setLanguage(language);
		lang.setActive(true);
		
	
		Language l = languageRepository.save(lang);
		
		checkLanguages.updateLanguages(l.getIdLanguage());
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	}
	
	public void cancel() {
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
		
	
	public void update() throws IOException {
		
	
		if (icon.getFileName() != null) {
		String ext;
	
			if (icon.getContentType().contains("jpeg")) {
				ext="jpg";
			}else{
				ext="png";
			}
		
			if (langobj.getIcon() != null) {
				copyFile.deleteFile("icons", langobj.getIcon(), pathsProps.getProperties().getImgRoot());
			}
			
			String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
			
			copyFile.copyFile("icons", name, icon.getInputStream(), pathsProps.getProperties().getImgRoot());
		
			langobj.setIcon(name);
			langobj.setOrgName(icon.getFileName());
			langobj.setType(icon.getContentType());
		}
		
		langobj.setAcrom(acrom);
		langobj.setLanguage(language);
		langobj.setUpdated(new Date());
		
	
		languageRepository.save(langobj);
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(2);
	}

}
