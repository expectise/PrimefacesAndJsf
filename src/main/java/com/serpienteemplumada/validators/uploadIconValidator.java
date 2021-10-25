package com.serpienteemplumada.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


import org.primefaces.model.file.UploadedFileWrapper;

import com.serpienteemplumada.utils.ContainWords;



@FacesValidator("uploadIconValidator")
public class uploadIconValidator implements Validator{
	@Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UploadedFileWrapper file = (UploadedFileWrapper) value;
		
		String[] fileType = {"jpeg", "png"};
		

        FacesMessage message=null;

        try {
        	if (file.getFileName() != null) {
	            if (!ContainWords.Contains(file.getContentType(), fileType)) {
	                message=new FacesMessage("Solo se aceptan jpg o png");
	            }
        	}else {
        		message=new FacesMessage("El archivo no puede estar nulo");
        	}
        	
            if (message!=null && !message.getDetail().isEmpty())
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }
    }
	
	
	

}
