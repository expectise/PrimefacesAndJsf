package com.serpienteemplumada.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ArtistspageDTO {
	private Long idArtistspage;
	
	private String title;
	
	private String language;
	
	private Long idLanguage;
	
	private String content;
	
	private Date created;
	
	private Date updated;
}
