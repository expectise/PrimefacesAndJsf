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
@Table(name = "pictureproduct", catalog = "serpiente")
public class Pictureproduct implements Serializable {
	private static final long serialVersionUID = 8941060626293658640L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPictureproduct", unique = true, nullable = false)
	private Long idPictureproduct;
	
	@Column(name = "IdProducts", nullable = false)
	private Long idProducts;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Products.class)
	@JoinColumn(name = "IdProducts", nullable = false, insertable = false, updatable = false)
	private Products products;
	
	@Column(name = "File", length = 50, nullable = false)
	private String file;
	
	@Column(name = "fileMaxQuality", nullable = false)
	private Integer fileMaxQuality;
	
	@Column(name = "FileType", length = 50, nullable = false)
	private String fileType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created;
	
	@Column(name = "Active", nullable = false)
	@ColumnDefault("1")
	private Boolean active;

}
