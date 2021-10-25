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
@Table(name = "productcontent", catalog = "serpiente")
public class Productcontent implements Serializable{
	private static final long serialVersionUID = -1041894262197196339L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdProductcontent", unique = true, nullable = false)
	private Long idProductcontent;
	
	@Column(name = "IdLanguage", nullable = false)
	private Long idLanguage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "IdLanguage", nullable = false, insertable = false, updatable = false)
	private Language language;
	
	@Column(name = "IdProducts", nullable = false)
	private Long idProducts;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Products.class)
	@JoinColumn(name = "IdProducts", nullable = false, insertable = false, updatable = false)
	private Products products;
	
	@Column(name = "Title", length = 50)
	private String title;
	
	@Column(name = "Content", length = 2000)
	private String content;
	
	@Column(name = "Materials", length = 50)
	private String materials;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Updated")
	private Date updated;
	
	@Column(name = "Active", nullable = false)
	@ColumnDefault("1")
	private Boolean active;

}
