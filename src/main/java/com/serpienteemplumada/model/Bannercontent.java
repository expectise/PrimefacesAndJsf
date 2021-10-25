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
@Table(name = "bannercontent", catalog = "serpiente")
public class Bannercontent implements Serializable {
	private static final long serialVersionUID = 5490861475812487777L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdBannercontent", unique = true, nullable = false)
	private Long idBannercontent;
	
	@Column(name = "IdLanguage", nullable = false)
	private Long idLanguage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "IdLanguage", nullable = false, insertable = false, updatable = false)
	private Language language;
	
	@Column(name = "IdBanners", nullable = false)
	private Long idBanners;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Banners.class)
	@JoinColumn(name = "IdBanners", nullable = false, insertable = false, updatable = false)
	private Banners banners;
	
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
	
	@Column(name = "TitleBigBanner", length = 40)
	private String titleBigBanner;
	
	@Column(name = "SubtitleBigBanner", length = 40)
	private String subtitleBanner;
	
	@Column(name = "TitleShortBanner", length = 40)
	private String titleShortBanner;

}
