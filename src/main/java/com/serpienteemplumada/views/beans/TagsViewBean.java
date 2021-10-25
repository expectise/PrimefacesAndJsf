package com.serpienteemplumada.views.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Tags;
import com.serpienteemplumada.repository.TagsRepository;

import lombok.Data;

@Data
public abstract class TagsViewBean {
	
	@Autowired
	private TagsRepository tagsRepository;

	public List<Tags> tags;
	
	public void showAddPopup() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("addtag", options, null);
		
	}
	
	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      if (out == 1) {
	    	  tags = tagsRepository.selectAll();
	      }else if(out == 2) {
	    	  tags = tagsRepository.selectAll();
	      }else {
	    	  tags = tagsRepository.selectAll();
	      }
	  }
	 
	 public void onActivate(Tags tagsa) {
		 
		 Tags tag = tagsRepository.findById(tagsa.getIdTags()).orElse(null);
		 
		 if (tag != null) {
			 boolean active = tag.getActive();
			 
			 tag.setActive(!active);
			 
			 tagsRepository.save(tag);
			 
			 tags = tagsRepository.selectAll();
		 }
		 
	 }
	 
	 public void onSelected(Tags tag) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("tags", tag);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("updatetag", options, null);
	 }
	 
}
