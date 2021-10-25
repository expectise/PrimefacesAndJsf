package com.serpienteemplumada.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartDTO {
	private Long idProducts;
	
	private String title;
	
	private String imageUrl;
	
	private String link;
	
	private BigDecimal price;

}
