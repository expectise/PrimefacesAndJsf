package com.serpienteemplumada.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BannercontentDTO {
	private Long idBannercontent;
	
	private String language;
	
	private String titleBigBanner;
	
	private String subtitleBanner;
	
	private String titleShortBanner;
	
	private Date created;
	
	private Date updated;
}
