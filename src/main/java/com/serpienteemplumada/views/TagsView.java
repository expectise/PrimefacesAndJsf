package com.serpienteemplumada.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Tags;
import com.serpienteemplumada.repository.TagsRepository;
import com.serpienteemplumada.views.beans.TagsViewBean;

@Scope("view")
@Component("tags")
public class TagsView extends TagsViewBean{

	@Autowired
	private TagsRepository tagsRepository;
	
	@PostConstruct
	public void init() {
		
		List<Tags> tags = tagsRepository.selectAll();
		
		setTags(tags);
	}

}
