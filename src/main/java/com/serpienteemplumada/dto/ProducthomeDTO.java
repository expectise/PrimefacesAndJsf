package com.serpienteemplumada.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProducthomeDTO {
	private Long idProducts;
	
	private String title;
	
	private BigDecimal price;
	
	private String imageUrl;
	
	private String link;
	
	private Integer discount;
	
	private String alt;
	
	private BigDecimal orgPrice;
	
	private boolean del;
	
	private Integer stock;
	
	private String content;
	
	private String materials;
	
	private String qr;
	
	private String mark;

}
