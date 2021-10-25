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

import com.serpienteemplumada.dto.TagscontentDTO;
import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.model.Tags;
import com.serpienteemplumada.model.Tagscontent;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.repository.TagsRepository;
import com.serpienteemplumada.repository.TagscontentRepository;

import lombok.Data;

@Data
public abstract class TagViewBean {
	
	@Autowired
	private TagscontentRepository tagscontentRepository;
	
	private Date created;
	private Date updated;
	private String title;
	private Tags tag;
	private Category category;
	
	private List<Category> categories;
	
	private List<TagscontentDTO> tagscontentDTO;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	
	protected void runner(Long id) {
		List<Tagscontent> list = tagscontentRepository.selectTagscontent(id);
		
		
		List<TagscontentDTO> lsout = new ArrayList<>();
		
		for (Tagscontent c : list) {
			TagscontentDTO out = new TagscontentDTO();
			out.setIdTagscontent(c.getIdTagscontent());
			out.setLanguage(c.getLanguage().getLanguage());
			out.setTitle(c.getTag());
			
			lsout.add(out);
		}
		
		setTagscontentDTO(lsout);
	}
	
	
	public void submit()  {
		
		Tags tag = new Tags();
		
		tag.setActive(true);
		tag.setTitle(title);
		tag.setCreated(new Date());
		tag.setIdCategory(category.getIdCategory());
		
		Tags t = tagsRepository.save(tag);

		
		List<Language> language = languageRepository.selectAll();
		
		for (Language l : language) {
			
			Tagscontent tagcontent = new Tagscontent();
			
			tagcontent.setIdLanguage(l.getIdLanguage());
			tagcontent.setCreated(new Date());
			tagcontent.setActive(true);
			tagcontent.setIdTags(category.getIdCategory());
			tagcontent.setIdTags(t.getIdTags());

			tagscontentRepository.save(tagcontent);
			
			
		}
		

		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	}
	
	public void cancel() {
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
	
	public void update() {
		Tags tags = tagsRepository.findById(tag.getIdTags()).orElse(null);
		
		if (tags != null) {
			tags.setUpdated(new Date());
			tags.setTitle(title);
			tags.setIdCategory(category.getIdCategory());
			
			
			tagsRepository.save(tags);
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(2);
		
	}
	
	
	public void onSelected(TagscontentDTO tagscontentDTO) {
		 Map<String,Object> options = new HashMap<String, Object>();
	       options.put("resizable", false);
	       options.put("positionType", "absolute");
	       options.put("styleClass", "dialogwindow");
	       
	       Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	       sessionMap.put("tagscontentDTO", tagscontentDTO);
	       
	       PrimeFaces instance = PrimeFaces.current();
		   instance.dialog().openDynamic("updatetagscontent", options, null);
	}
	
	public void onCloseDialog(SelectEvent selectEvent) {
	    int out = (int) selectEvent.getObject();
	    if (out == 1) {
	    	runner(tag.getIdTags());
	    }else if(out == 2) {
	    	runner(tag.getIdTags());
	    }
	}

}
