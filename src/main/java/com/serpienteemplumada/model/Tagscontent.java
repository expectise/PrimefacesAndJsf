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
@Table(name = "tagscontent", catalog = "serpiente")
public class Tagscontent implements Serializable{
	private static final long serialVersionUID = 7569204273406747018L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdTagscontent", unique = true, nullable = false)
	private Long idTagscontent;
	
	@Column(name = "IdLanguage", nullable = false)
	private Long idLanguage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "IdLanguage", nullable = false, insertable = false, updatable = false)
	private Language language;
	
	@Column(name = "IdTags", nullable = false)
	private Long idTags;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Tags.class)
	@JoinColumn(name = "IdTags", nullable = false, insertable = false, updatable = false)
	private Tags tags;
	
	@Column(name = "Tag", length = 50)
	private String tag;
	
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
