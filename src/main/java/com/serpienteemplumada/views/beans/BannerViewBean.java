package com.serpienteemplumada.views.beans;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.BannersDTO;
import com.serpienteemplumada.model.Address;
import com.serpienteemplumada.model.Bannercontent;
import com.serpienteemplumada.model.Banners;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.BannercontentRepository;
import com.serpienteemplumada.repository.BannersRepository;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.utils.CopyFile;
import com.serpienteemplumada.utils.PathsProps;

import lombok.Data;

@Data
public abstract class BannerViewBean {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BannersRepository bannersRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private BannercontentRepository bannercontentRepository;
	
	@Autowired
	private CopyFile copyFile;
	 
	@Autowired
	private PathsProps pathsProps;
	
	
	private BannersDTO bannersDTO;
	
	private Banners banner;
	
	private UploadedFile file1;
	
	private UploadedFile file2;
	
	private UploadedFile file3;
	
	private UploadedFile file4;
	
	private String file1url;
	
	private String file2url;
	
	private String file3url;
	
	private String file4url;
	
	
	public void submit() throws IOException {
		Banners banner = modelMapper.map(bannersDTO, Banners.class);
		
		banner.setActive(true);
		banner.setCreated(new Date());
		banner.setPrinted(Long.parseLong("0"));
		
	
		
		if (file1.getFileName() != null) {
			String file_1 = saveFile(file1);
			int maxWidthFile1 = copyFile.generateResized(file_1, "banner", pathsProps.getProperties().getImgRoot(), file1.getContentType()); 
			banner.setFile(file_1);
			banner.setFileMaxQuality(maxWidthFile1);
			banner.setFileType(file1.getContentType());
		}
		
		if(file2.getFileName() != null) {
			String file_2 = saveFile(file2);
			int maxWidthFile2 = copyFile.generateResized(file_2, "banner", pathsProps.getProperties().getImgRoot(), file2.getContentType()); 
			banner.setFile2(file_2);
			banner.setFileMaxQuality2(maxWidthFile2);
			banner.setFileType2(file2.getContentType());
		}
		
		if(file3.getFileName() != null) {
			String file_3 = saveFile(file3);
			int maxWidthFile3 = copyFile.generateResized(file_3, "banner", pathsProps.getProperties().getImgRoot(), file3.getContentType()); 
			banner.setFile3(file_3);
			banner.setFileMaxQuality3(maxWidthFile3);
			banner.setFileType3(file3.getContentType());
		}
		
		if(file4.getFileName() != null) {
			String file_4 = saveFile(file4);
			int maxWidthFile4 = copyFile.generateResized(file_4, "banner", pathsProps.getProperties().getImgRoot(), file4.getContentType()); 
			banner.setFile4(file_4);
			banner.setFileMaxQuality4(maxWidthFile4);
			banner.setFileType4(file4.getContentType());
		}
		
		
		Banners ban = bannersRepository.save(banner);
		
		List<Language> languages = languageRepository.selectAll();
		
		for (Language lang : languages) {
			
			Bannercontent content = new Bannercontent();
			
			content.setActive(true);
			content.setCreated(new Date());
			content.setIdBanners(ban.getIdBanners());
			content.setIdLanguage(lang.getIdLanguage());
			
			bannercontentRepository.save(content);
		}
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
		
		
	}
	
	
	private String saveFile(UploadedFile file) throws IOException {
		String ext = "";
		
		if (file.getContentType().contains("jpeg")) {
			ext="jpg";
		}else{
			ext="png";
		}
		
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
		
		copyFile.copyFile("banner", name, file.getInputStream(), pathsProps.getProperties().getImgRoot());
	
		
		
		return name;
	}
	
	public void update() throws IOException {
		Banners banner = modelMapper.map(bannersDTO, Banners.class);
		
		banner.setUpdated(new Date());
		
	
		
		if (file1.getFileName() != null) {
			copyFile.deleteFile("banner", banner.getFile(), pathsProps.getProperties().getImgRoot());
			
			String file_1 = saveFile(file1);
			int maxWidthFile1 = copyFile.generateResized(file_1, "banner", pathsProps.getProperties().getImgRoot(), file1.getContentType()); 
			banner.setFile(file_1);
			banner.setFileMaxQuality(maxWidthFile1);
			banner.setFileType(file1.getContentType());
		}
		
		if(file2.getFileName() != null) {
			copyFile.deleteFile("banner", banner.getFile2(), pathsProps.getProperties().getImgRoot());
			
			String file_2 = saveFile(file2);
			int maxWidthFile2 = copyFile.generateResized(file_2, "banner", pathsProps.getProperties().getImgRoot(), file2.getContentType()); 
			banner.setFile2(file_2);
			banner.setFileMaxQuality2(maxWidthFile2);
			banner.setFileType2(file2.getContentType());
		}
		
		if(file3.getFileName() != null) {
			copyFile.deleteFile("banner", banner.getFile3(), pathsProps.getProperties().getImgRoot());
			
			String file_3 = saveFile(file3);
			int maxWidthFile3 = copyFile.generateResized(file_3, "banner", pathsProps.getProperties().getImgRoot(), file3.getContentType()); 
			banner.setFile3(file_3);
			banner.setFileMaxQuality3(maxWidthFile3);
			banner.setFileType3(file3.getContentType());
		}
		
		if(file4.getFileName() != null) {
			copyFile.deleteFile("banner", banner.getFile4(), pathsProps.getProperties().getImgRoot());
			
			String file_4 = saveFile(file4);
			int maxWidthFile4 = copyFile.generateResized(file_4, "banner", pathsProps.getProperties().getImgRoot(), file4.getContentType()); 
			banner.setFile4(file_4);
			banner.setFileMaxQuality4(maxWidthFile4);
			banner.setFileType4(file4.getContentType());
		}
		
		
		bannersRepository.save(banner);
		
		
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(2);
		
	}
	
	
	public void showList(BannersDTO bannersDTO) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        options.put("contentHeight", "700");
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("bannersDTO", bannersDTO);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("bannercontent", options, null);
	 }

	
	public void cancel() {
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
	

}
