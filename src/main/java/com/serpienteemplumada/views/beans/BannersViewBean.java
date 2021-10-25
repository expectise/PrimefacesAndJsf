package com.serpienteemplumada.views.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Banners;
import com.serpienteemplumada.repository.BannersRepository;

import lombok.Data;

@Data
public abstract class BannersViewBean {
	
	List<Banners> banners;
	
	@Autowired
	private BannersRepository bannersRepository;
	
	protected void runner() {
		banners = bannersRepository.selectAll();
	}

	public void showAddPopup() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("addbanner", options, null);
		
	}
	
	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      if (out == 1) {
	    	  banners = bannersRepository.selectAll();
	      }else if(out == 2) {
	    	  banners = bannersRepository.selectAll();
	      }else {
	    	  banners = bannersRepository.selectAll();
	      }
	  }
	 
	 public void onSelected(Banners banner)
	 {
	 
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("banner", banner);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("updatebanner", options, null);
	 }
	 
	 public void onActivate(Banners banner)
	 {
		 	Banners ban = bannersRepository.findById(banner.getIdBanners()).orElse(null);
		 
		 if (ban != null) {
			 boolean active = ban.getActive();
			 
			 ban.setActive(!active);
			 
			 bannersRepository.save(ban);
			 
			 banners = bannersRepository.selectAll();
		 }
	 }
}
