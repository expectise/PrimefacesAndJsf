package com.serpienteemplumada.dto;

import java.util.Date;


import com.serpienteemplumada.model.Countries;

import lombok.Data;

@Data
public class ArtistDTO {
	private Long idArtists;
	
	private String firstname;

	private String middlename;
	
	private String lastname;

	private String mark;
	
	private String email;
	
	private Date birthdate;

	private String cellphone;
	
	private String businesspone;
	
	private String logo;
	
	private String artistPic;

	
	private String workPic;
	
	
	private String rfc;
	
	private String bank;
	
	private String clabe;
	
	private String noaccount;
	
	private String naaccount;

	private String bsname;
	
	private Date created;
	
	private Date updated;
	
	private Countries country;
	
	private Boolean active;
	
	private Long idAddress;
	
	private Integer logoMaxQuality;
	
	private Integer artistMaxQuality;
	
	private Integer workMaxQuality;
	
	private String artistPicType;
	
	private String workPicType;
	
	private String logoType;
	
	private String statement;
	
	private String url;
}
