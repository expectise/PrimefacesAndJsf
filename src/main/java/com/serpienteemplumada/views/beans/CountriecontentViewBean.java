package com.serpienteemplumada.views.beans;



import java.util.Date;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.CountrycontentDTO;
import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.model.Countrycontent;
import com.serpienteemplumada.repository.CountriesRepository;
import com.serpienteemplumada.repository.CountrycontentRepository;

import lombok.Data;

@Data
public abstract class CountriecontentViewBean {
	
	private CountrycontentDTO countrycontentDTO;
	
	@Autowired
	private CountrycontentRepository countrycontentRepository;
	
	@Autowired
	private CountriesRepository countriesRepository;
	
	public void submit()  {
		
		Countrycontent ccontent = countrycontentRepository.findById(countrycontentDTO.getIdCountrycontent()).orElse(null);
		
		if(ccontent != null) {
			ccontent.setShipDetails(countrycontentDTO.getShipDetails());
			ccontent.setUpdated(new Date());
			ccontent.setCountry(countrycontentDTO.getCountry());
			
			Countries co = countriesRepository.findById(ccontent.getIdCountries()).orElse(null);
			co.setUpdated(new Date());
			countriesRepository.save(co);
			
			countrycontentRepository.save(ccontent);
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	}
	
	public void cancel() {
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
		

}
