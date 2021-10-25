package com.serpienteemplumada.dto;

import java.util.Date;
import java.util.List;

import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Category;

import lombok.Data;

@Data
public class ProductsDTO {
	
	private String price;
	
	private String priceConsign;
	
	private String title;
	
	private String qr;
	
	private String stock;
	
	private String discount;
	
	private Long visitors;
	
	private Integer purchased;
	
	private String length;
	
	private String width;
	
	private String height;
	
	private String weight;
	
	private Boolean isRing;
	
	private Boolean active;
	
	private Date created;
	
	private Date updated;
	
	private Long idArtists;
	
	private Long idProducts;
	
	private Artists artists;
	
	private String[] categories;
	
	private String[] precategories;
	
}
