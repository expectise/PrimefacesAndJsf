package com.serpienteemplumada.views.beans;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.AddressDTO;
import com.serpienteemplumada.model.Address;
import com.serpienteemplumada.repository.AddressRepository;

import lombok.Data;

@Data
public abstract class AddressartistViewBean {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	private AddressDTO addressDTO;
	
	public void submit() {
		
		Address address = modelMapper.map(addressDTO, Address.class);
		
		address.setUpdated(new Date());
		
		addressRepository.save(address);
		

		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(2);
		
	}
	
	
	public void cancel() {		

		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(2);
		
	}
}
