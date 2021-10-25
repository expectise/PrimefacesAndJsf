package com.serpienteemplumada.converters;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;


import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.repository.CategoryRepository;


@Named("categoryConverter")
@ApplicationScoped
public class CategoryConverter implements Converter{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
            try {
                return categoryRepository.findById(Long.parseLong(value)).orElse(null);

            } catch (Exception e) {
                throw new ConverterException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Objeto no válido"));
            }
        } else {
            return null;
        }

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Category cat = (Category) value;
		// TODO Auto-generated method stub
		return cat.getIdCategory().toString();
	}

}
