package com.serpienteemplumada.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Data
@Table(name = "countries", catalog = "serpiente")
public class Countries implements Serializable{
	private static final long serialVersionUID = 8851424984731709093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCountries", unique = true, nullable = false)
	private Long idCountries;
	
	@Column(name = "ShipPrice", precision = 8, scale = 2, nullable = false)
	private BigDecimal shipPrice;
	
	@Column(name = "ExcentPrice", precision = 10, scale = 2, nullable = false)
	private BigDecimal excentPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Updated")
	private Date updated;
	
	@Column(name = "Prefix", unique = true, length = 2)
	private String prefix;
	
	@Column(name = "Active", nullable = false)
	@ColumnDefault("1")
	private Boolean active;
	
}
