package com.serpienteemplumada.views.beans;

import java.util.Date;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.TagscontentDTO;
import com.serpienteemplumada.model.Tags;
import com.serpienteemplumada.model.Tagscontent;
import com.serpienteemplumada.repository.TagsRepository;
import com.serpienteemplumada.repository.TagscontentRepository;

import lombok.Data;

@Data
public abstract class TagscontentViewBean {

	private TagscontentDTO tagscontentDTO;
	
	@Autowired
	private TagscontentRepository tagscontentRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	public void submit()  {
		
		Tagscontent tcc = tagscontentRepository.findById(tagscontentDTO.getIdTagscontent()).orElse(null);
		
		if (tcc != null) {
			tcc.setUpdated(new Date());
			tcc.setTag(tagscontentDTO.getTitle());
			
			Tags tag = tagsRepository.findById(tcc.getIdTags()).orElse(null);
			tag.setUpdated(new Date());
			
			tagsRepository.save(tag);
			
			tagscontentRepository.save(tcc);
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	}
	
	public void cancel() {
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
	
}