package com.serpienteemplumada.views.beans;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.CountrycontentDTO;
import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.model.Countrycontent;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.CountriesRepository;
import com.serpienteemplumada.repository.CountrycontentRepository;
import com.serpienteemplumada.repository.LanguageRepository;

import lombok.Data;

@Data
public abstract class CountrieViewBean {
	private String shipPrice;
	private String excentPrice;
	private Date created;
	private Date updated;
	private String prefix;
	
	private List<CountrycontentDTO> countryC;
	
	private Countries countries;
	
	
	@Autowired
	private CountriesRepository countriesRepository;
	
	@Autowired
	private CountrycontentRepository countrycontentRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	
	
	
	public void submit()  {
		
	
		Countries country = new Countries();
		country.setActive(true);
		country.setCreated(new Date());
		country.setExcentPrice(new BigDecimal(excentPrice));
		country.setShipPrice(new BigDecimal(shipPrice));
		country.setPrefix(prefix);
		country.setActive(true);
		
		
		Countries c = countriesRepository.save(country);
		
		
		List<Language> language = languageRepository.selectAll();
		
		for (Language l : language) {
			Countrycontent cct = new Countrycontent();
			cct.setActive(true);
			cct.setIdCountries(c.getIdCountries());
			cct.setIdLanguage(l.getIdLanguage());
			cct.setCreated(new Date());
			
			countrycontentRepository.save(cct);
			
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	}
	
	public void cancel() {
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
	
	
	public void update() {
		
			Countries cc =  countriesRepository.findById(countries.getIdCountries()).orElse(null);
			
			if (cc != null) {
				cc.setUpdated(new Date());
				cc.setExcentPrice(new BigDecimal(excentPrice));
				cc.setPrefix(prefix);
				cc.setShipPrice(new BigDecimal(shipPrice));
				
				countriesRepository.save(cc);
			}
	
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().closeDynamic(2);
	}

	public void onSelected(CountrycontentDTO countrycontentDTO)
		{
	
		 Map<String,Object> options = new HashMap<String, Object>();
	       options.put("resizable", false);
	       options.put("positionType", "absolute");
	       options.put("styleClass", "dialogwindow");
	       
	       
	       Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	       sessionMap.put("countrycontentDTO", countrycontentDTO);
	       
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("updatecountrycontent", options, null);
		}

	public void onCloseDialog(SelectEvent selectEvent) {
	    int out = (int) selectEvent.getObject();
	    if (out == 1) {
	    	runner(countries.getIdCountries());
	    }else if(out == 2) {
	    	runner(countries.getIdCountries());
	    }
	}

	protected void runner(Long id) {
		List<Countrycontent> list = countrycontentRepository.selectCountrycontent(id);
		
		List<CountrycontentDTO> lsout = new ArrayList<>();
		
		for (Countrycontent c : list) {
			CountrycontentDTO out = new CountrycontentDTO();
			out.setIdCountrycontent(c.getIdCountrycontent());
			out.setCountry(c.getCountry());
			out.setLanguage(c.getLanguage().getLanguage());
			out.setShipDetails(c.getShipDetails());
			
			lsout.add(out);
		}
		
		setCountryC(lsout);
	}

}
