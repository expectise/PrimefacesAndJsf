package com.serpienteemplumada.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@Table(name = "language", catalog = "serpiente")
public class Language implements Serializable {
	private static final long serialVersionUID = 2157252937443625212L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdLanguage", unique = true, nullable = false)
	private Long idLanguage;

	@Column(name = "Acrom", unique = true, nullable = false, length = 2)
	private String acrom;

	@Column(name = "Language", nullable = false, length = 20)
	private String language;

	@Column(name = "Icon", unique = true, nullable = false, length = 50)
	private String icon;
	
	@Column(name = "Type", nullable = false, length = 14)
	private String type;
	
	@Column(name = "OrgName", nullable = false, length = 50)
	private String orgName;
	
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
