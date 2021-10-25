package com.serpienteemplumada.dto;

import lombok.Data;

@Data
public class PictureproductDTO {
	private Long idPictureproduct;
	
	private String urlThumbnail;
	
	private String urltoImage;
	
	private String file;
	
	private String alt;
	
	private int index;
}