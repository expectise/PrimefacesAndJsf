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
@Table(name = "categorycontent", catalog = "serpiente")
public class Categorycontent  implements Serializable {
	private static final long serialVersionUID = 1076249773363020063L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCategorycontent", unique = true, nullable = false)
	private Long idCategorycontent;
	
	@Column(name = "IdCategory", nullable = false)
	private Long idCategory;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
	@JoinColumn(name = "IdCategory", nullable = false, insertable = false, updatable = false)
	private Category category;
	
	@Column(name = "IdLanguage", nullable = false)
	private Long idLanguage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "IdLanguage", nullable = false, insertable = false, updatable = false)
	private Language language;

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
	
	@Column(name = "Title", unique = true, length = 50)
	private String title;
	
}
