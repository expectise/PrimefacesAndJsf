package com.serpienteemplumada.converters;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.repository.CountriesRepository;

@Named("countryConverter")
@ApplicationScoped
public class CountryConverter implements Converter {
	@Autowired
	private CountriesRepository countriesrepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
            try {
            	return countriesrepository.findById(Long.parseLong(value)).orElse(null);
            } catch (Exception e) {
            throw new ConverterException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Objeto no válido"));
		}
		}else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Countries country = (Countries)value;
		
		
		return country.getIdCountries().toString();
	}

}
