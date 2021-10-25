package com.serpienteemplumada.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.repository.CountriesRepository;
import com.serpienteemplumada.views.beans.CountriesViewBean;



@Scope("view")
@Component("countries")
public class CountriesView extends CountriesViewBean{
	@Autowired
	private CountriesRepository countriesRepository;

	@PostConstruct
	public void init() {
		List<Countries> countries = countriesRepository.selectAll();
		
		setCountries(countries);
	}
}
