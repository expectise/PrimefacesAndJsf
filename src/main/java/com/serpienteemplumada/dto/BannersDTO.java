package com.serpienteemplumada.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BannersDTO {
	private Long idBanners;
	
	private Date created;
	
	private Date updated;
	
	private Boolean active;
	
	private String title;
	
	private Long printed;
	
	private Date lastPrinted;

	private String file;

	private String fileType;
	
	private Integer fileMaxQuality;
	
	private String file2;
	
	private String fileType2;
	
	private Integer fileMaxQuality2;
	
	private String file3;
	
	private String fileType3;

	private Integer fileMaxQuality3;
	
	private String file4;
	
	private String fileType4;
	
	private Integer fileMaxQuality4;

}