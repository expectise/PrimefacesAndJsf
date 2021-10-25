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
@Table(name = "asigncategory", catalog = "serpiente")
public class Asigncategory implements Serializable  {
	private static final long serialVersionUID = 4826465691846962910L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdAsigncategory", unique = true, nullable = false)
	private Long idAsigncategory;
	
	@Column(name = "IdProducts", nullable = false)
	private Long idProducts;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Products.class)
	@JoinColumn(name = "IdProducts", nullable = false, insertable = false, updatable = false)
	private Products products;
	
	@Column(name = "IdCategory", nullable = false)
	private Long idCategory;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
	@JoinColumn(name = "IdCategory", nullable = false, insertable = false, updatable = false)
	private Category category;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created;
	

}
