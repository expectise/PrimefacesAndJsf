package com.serpienteemplumada.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Data
@Table(name = "products", catalog = "serpiente")
public class Products implements Serializable{
	private static final long serialVersionUID = 4722496732342338277L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdProducts", unique = true, nullable = false)
	private Long idProducts;
	
	@Column(name = "IdArtists", nullable = false)
	private Long idArtists;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Artists.class)
	@JoinColumn(name = "IdArtists", nullable = false, insertable = false, updatable = false)
	private Artists artists;
	
	@Column(name = "Price", precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(name = "PriceConsign", precision = 10, scale = 2)
	private BigDecimal priceConsign;
	
	@Column(name = "Title", length = 40, nullable = false)
	private String title;
	
	@Column(name = "Qr", length = 6, nullable = false, unique = true)
	private String qr;
	
	@Column(name = "Stock")
	private Integer stock;
	
	@Column(name = "Discount")
	private Integer discount;
	
	@Column(name = "Visitors")
	private Long visitors;
	
	@Column(name = "Purchased")
	private Integer purchased;
	
	@Column(name = "Length", precision = 6, scale = 2)
	private BigDecimal length;
	
	@Column(name = "Width", precision = 6, scale = 2)
	private BigDecimal width;
	
	@Column(name = "Height", precision = 6, scale = 2)
	private BigDecimal height;
	
	@Column(name = "Weight", precision = 6, scale = 2)
	private BigDecimal weight;
	
	@Column(name = "IsRing", nullable = false)
	@ColumnDefault("0")
	private Boolean isRing;
	
	@Column(name = "Active", nullable = false)
	@ColumnDefault("1")
	private Boolean active;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Updated")
	private Date updated;
}
