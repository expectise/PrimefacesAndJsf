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
@Table(name = "artistcontent", catalog = "serpiente")
public class Artistcontent   implements Serializable {
	private static final long serialVersionUID = 2754502242577139035L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdArtistcontent", unique = true, nullable = false)
	private Long idArtistcontent;

	@Column(name = "IdArtists", nullable = false)
	private Long idArtists;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Artists.class)
	@JoinColumn(name = "IdArtists", nullable = false, insertable = false, updatable = false)
	private Artists artists;
	
	@Column(name = "IdLanguage", nullable = false)
	private Long idLanguage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "IdLanguage", nullable = false, insertable = false, updatable = false)
	private Language language;
	
	@Column(name = "Statement", length = 2000)
	private String statement;
	
	@Column(name = "Title", length = 50)
	private String title;
	
	@Column(name = "Artistpic", length = 30)
	private String artistpic;
	
	@Column(name = "Artistwork", length = 30)
	private String artistwork;
	
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
