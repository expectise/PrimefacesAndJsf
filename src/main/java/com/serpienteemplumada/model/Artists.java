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
@Table(name = "artists", catalog = "serpiente")
public class Artists implements Serializable  {
	private static final long serialVersionUID = -4051366088020073920L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdArtists", unique = true, nullable = false)
	private Long idArtists;
	
	@Column(name = "IdAddress", nullable = false)
	private Long idAddress;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	@JoinColumn(name = "IdAddress", nullable = false, insertable = false, updatable = false)
	private Address address;
	
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
	
	@Column(name = "Firstname", length = 60)
	private String firstname;
	
	@Column(name = "Middlename", length = 60)
	private String middlename;
	
	@Column(name = "Lastname", length = 60)
	private String lastname;
	
	@Column(name = "Mark", length = 50)
	private String mark;
	
	@Column(name = "Email", length = 100)
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Birthdate", length = 10)
	private Date birthdate;
	
	@Column(name = "Cellphone", nullable = false, length = 15)
	private String cellphone;
	
	@Column(name = "Businessphone", length = 15)
	private String businesspone;
	
	@Column(name = "Logo", length = 50, nullable = false)
	private String logo;
	
	@Column(name = "LogoType", length = 50, nullable = false)
	private String logoType;
	
	@Column(name = "ArtistPic", length = 50, nullable = false)
	private String artistPic;
	
	@Column(name = "LogoMaxQuality", nullable = false)
	private Integer logoMaxQuality;
	
	@Column(name = "ArtistPicType", length = 50, nullable = false)
	private String artistPicType;
	
	@Column(name = "ArtistMaxQuality", nullable = false)
	private Integer artistMaxQuality;
	
	@Column(name = "WorkPic", length = 50, nullable = false)
	private String workPic;
	
	@Column(name = "WorkPicType", length = 50, nullable = false)
	private String workPicType;
	
	@Column(name = "WorkMaxQuality", nullable = false)
	private Integer workMaxQuality;
	
	@Column(name = "Rfc", nullable = false, length = 20)
	private String rfc;
	
	@Column(name = "Bank", nullable = false, length = 40)
	private String bank;
	
	@Column(name = "Clabe", nullable = false, length = 18)
	private String clabe;
	
	@Column(name = "Noaccount", nullable = false, length = 18)
	private String noaccount;
	
	@Column(name = "Naaccount", nullable = false, length = 140)
	private String naaccount;
	
	@Column(name = "Bsname", nullable = false, length = 70)
	private String bsname;
}
