package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.AddressDTO;
import com.serpienteemplumada.model.Address;
import com.serpienteemplumada.views.beans.AddressartistViewBean;


@Scope("view")
@Component("addressartist")
public class AddressartistView extends AddressartistViewBean{
	@Autowired
	private ModelMapper modelMapper;
	
	
	@PostConstruct
	public void init() {
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Address input = (Address) sessionMap.get("address");
		
		if (input != null) {
			
		
			AddressDTO dto = modelMapper.map(input, AddressDTO.class);
			
			setAddressDTO(dto);
			
			sessionMap.remove("address");
		}
	
	}
}
