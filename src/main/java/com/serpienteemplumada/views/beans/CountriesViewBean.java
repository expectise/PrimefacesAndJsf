package com.serpienteemplumada.views.beans;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.repository.CountriesRepository;
import com.serpienteemplumada.views.AddressView;

import lombok.Getter;
import lombok.Setter;



public abstract class CountriesViewBean {
	@Autowired
	AddressView address;
	
	
	@Getter
	@Setter
	private List<Countries> countries;
	
	@Autowired
	private CountriesRepository countriesRepository;
	
	
	
	public void showAddPopup() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("addcountry", options, null);
		
	}
	
	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      if (out == 1) {
	    	  countries = countriesRepository.selectAll();
	      }else if(out == 2) {
	    	  countries = countriesRepository.selectAll();
	      }else {
	    	  countries = countriesRepository.selectAll();
	      }
	  }
	 
	 public void onSelected(Countries countrie)
	 {
	 
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("countrie", countrie);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("updatecountry", options, null);
	 }
	 

	 public void onActivate(Countries countrie)
	 {
		 	Countries country = countriesRepository.findById(countrie.getIdCountries()).orElse(null);
		 
		 if (country != null) {
			 boolean active = country.getActive();
			 
			 country.setActive(!active);
			 
			 countriesRepository.save(country);
			 
			 countries = countriesRepository.selectAll();
		 }
	 }

}
