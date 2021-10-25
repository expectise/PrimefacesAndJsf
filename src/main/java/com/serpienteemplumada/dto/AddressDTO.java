package com.serpienteemplumada.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AddressDTO {
	private Long idAddress;
	
	private Long idCountries;
	
	private Date created;
	
	private Date updated;
	
	private Boolean active;
	
	private Boolean current;
	
	private String state;
	
	private String postalCode;
	
	private String city;
	
	private String street;
	
	private String extNo;
	
	private String intNo;
	
	private String firstname;
	
	private String middlename;
	
	private String lastname;
	
	private String phone;
	
	private String observations;
}
