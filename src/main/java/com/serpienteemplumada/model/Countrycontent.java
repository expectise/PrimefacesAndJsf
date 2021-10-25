package com.serpienteemplumada.model;

import java.io.Serializable;
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
@Table(name = "countrycontent", catalog = "serpiente")
public class Countrycontent implements Serializable {
	private static final long serialVersionUID = 1356188285076457297L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCountrycontent", unique = true, nullable = false)
	private Long idCountrycontent;
	
	@Column(name = "IdCountries", nullable = false)
	private Long idCountries;
	
	@Column(name = "IdLanguage", nullable = false)
	private Long idLanguage;
	
	@Column(name = "Country", length = 50)
	private String country;
	
	@Column(name = "ShipDetails", length = 50)
	private String shipDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Countries.class)
	@JoinColumn(name = "IdCountries", nullable = false, insertable = false, updatable = false)
	private Countries countries;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "IdLanguage", nullable = false, insertable = false, updatable = false)
	private Language language;
	
	@Column(name = "Active", nullable = false)
	@ColumnDefault("1")
	private Boolean active;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Updated")
	private Date updated;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created;

}
