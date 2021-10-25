package com.serpienteemplumada.views.beans;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.CategoriesDTO;
import com.serpienteemplumada.model.Categorycontent;
import com.serpienteemplumada.repository.CategorycontentRepository;
import com.serpienteemplumada.utils.PathsProps;
import com.serpienteemplumada.utils.ScapeChars;

import lombok.Data;

@Data
public abstract class CategoriesbarViewBean {
	
	@Autowired
	private CategorycontentRepository categorycontentRepository;
	
	 
	@Autowired
	private PathsProps pathsProps;
	
	private Long idLanguage;
	
	private String acrom;
	
	private List<CategoriesDTO> categoriesDTO;
	
	protected void runner() {
		List<Categorycontent> list = categorycontentRepository.selectCategorycontentByLang(idLanguage);
		
		List<CategoriesDTO> lsout = new ArrayList<>();
		
		for (Categorycontent content : list) {
			CategoriesDTO out = new CategoriesDTO();
			
			out.setIdCategory(content.getIdCategory());
			out.setTitle(content.getTitle());
			out.setUrl(pathsProps.getProperties().getUrl() + acrom.toLowerCase() + "/category/" + content.getIdCategory() + "/" + ScapeChars.stripDiacritics(content.getTitle()));
			
			lsout.add(out);
		}
		
		categoriesDTO = lsout;
	}

}
