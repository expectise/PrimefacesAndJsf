package com.serpienteemplumada.views.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Artists;

import com.serpienteemplumada.repository.ArtistsRepository;

import lombok.Data;

@Data
public abstract class ArtistsViewBean {
	@Autowired
	private ArtistsRepository artistsRepository;

	private List<Artists> artists;
	
	public void showAddPopup() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("addartist", options, null);
		
	}
	
	
	public void showAddPopupB() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        options.put("contentHeight", "600");
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("artistpagelist", options, null);
		
	}
	
	
	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      if (out == 1) {
	    	  artists = artistsRepository.selectAll();
	      }else if(out == 2) {
	    	  artists = artistsRepository.selectAll();
	      }else {
	    	  artists = artistsRepository.selectAll();
	      }
	  }
	 
	 public void onSelected(Artists artist) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("artist", artist);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("updateartist", options, null);
	 }
	 
	 public void onActivate(Artists artist)
	 {
		    Artists arts = artistsRepository.findById(artist.getIdArtists()).orElse(null);
		    

		 if (arts != null) {
			 boolean active = arts.getActive();
			 
			 arts.setActive(!active);
			 
			 artistsRepository.save(arts);
			 
			 artists = artistsRepository.selectAll();
		 }
	 }
	
}
