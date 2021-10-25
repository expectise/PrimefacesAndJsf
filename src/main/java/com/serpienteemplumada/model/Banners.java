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

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Data
@Table(name = "banners", catalog = "serpiente")
public class Banners implements Serializable{
	private static final long serialVersionUID = 2802762410588821683L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdBanners", unique = true, nullable = false)
	private Long idBanners;
	
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
	
	@Column(name = "Title", length = 50)
	private String title;
	
	@Column(name = "Printed")
	private Long printed;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastPrinted")
	private Date lastPrinted;
	
	@Column(name = "File", length = 50, nullable = false)
	private String file;
	
	@Column(name = "FileType", length = 50, nullable = false)
	private String fileType;
	
	@Column(name = "FileMaxQuality", nullable = false)
	private Integer fileMaxQuality;
	
	@Column(name = "File2", length = 50, nullable = false)
	private String file2;
	
	@Column(name = "FileType2", length = 50, nullable = false)
	private String fileType2;
	
	@Column(name = "FileMaxQuality2", nullable = false)
	private Integer fileMaxQuality2;
	
	@Column(name = "File3", length = 50, nullable = false)
	private String file3;
	
	@Column(name = "FileType3", length = 50, nullable = false)
	private String fileType3;
	
	@Column(name = "FileMaxQuality3", nullable = false)
	private Integer fileMaxQuality3;
	
	@Column(name = "File4", length = 50, nullable = false)
	private String file4;
	
	@Column(name = "FileType4", length = 50, nullable = false)
	private String fileType4;
	
	@Column(name = "FileMaxQuality4", nullable = false)
	private Integer fileMaxQuality4;

}
