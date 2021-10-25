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
@Table(name = "artistspage", catalog = "serpiente")
public class Artistspage implements Serializable {
	private static final long serialVersionUID = -11506876668800575L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdArtistspage", unique = true, nullable = false)
	private Long idArtistspage;
	
	@Column(name = "IdLanguage", nullable = false)
	private Long idLanguage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "IdLanguage", nullable = false, insertable = false, updatable = false)
	private Language language;
	
	@Column(name = "Title", length = 50)
	private String title;
	
	@Column(name = "Content", length = 2000)
	private String content;
	
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
