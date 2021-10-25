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

import com.serpienteemplumada.dto.ArtistDTO;
import com.serpienteemplumada.dto.ArtistcontentDTO;
import com.serpienteemplumada.model.Address;
import com.serpienteemplumada.model.Artistcontent;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.AddressRepository;
import com.serpienteemplumada.repository.ArtistcontentRepository;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.utils.CopyFile;
import com.serpienteemplumada.utils.PathsProps;

import lombok.Data;

@Data
public abstract class ArtistViewBean {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ArtistsRepository artistsRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ArtistcontentRepository artistcontentRepository;
	

	
	@Autowired
	private LanguageRepository languageRepository;
	
	
	private ArtistDTO artistDTO;
	
	@Autowired
	private CopyFile copyFile;
	 
	@Autowired
	private PathsProps pathsProps;
	
	private List<Countries> countries;
	
	
	
	private Address address;
	
	private UploadedFile logoFile;
	private UploadedFile artistPicFile;
	
	private UploadedFile workPicFile;
	
	private String artisturl;
	
	private String joburl;
	
	private String logourl;
	
	
	

	
	public void onSelected(ArtistcontentDTO artistcontentDTO) {
		 Map<String,Object> options = new HashMap<String, Object>();
	      options.put("resizable", false);
	      options.put("positionType", "absolute");
	      options.put("styleClass", "dialogwindow");
	      
	      Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	      sessionMap.put("artistcontentDTO", artistcontentDTO);
	      
	      PrimeFaces instance = PrimeFaces.current();
		   instance.dialog().openDynamic("updateartistcontent", options, null);
	}
	
	public void submit() throws IOException {
		Artists artist =  modelMapper.map(artistDTO, Artists.class);
		
		String logo = saveLogo();
		
		artist.setLogo(logo);
		
		artist.setLogoType(logoFile.getContentType());
		
		int maxWidthLogo = copyFile.generateResized(logo, "logos", pathsProps.getProperties().getImgRoot(), logoFile.getContentType());
		
		artist.setLogoMaxQuality(maxWidthLogo);

			
		String artistPic = saveArtist();
			
		int maxWidthArtist = copyFile.generateResized(artistPic, "artist", pathsProps.getProperties().getImgRoot(), artistPicFile.getContentType());
			
		artist.setArtistPic(artistPic);
		
		artist.setArtistMaxQuality(maxWidthArtist);
		
		artist.setArtistPicType(artistPicFile.getContentType());
		
		

		String jobPic = saveJob();
			
		int maxWidthJob = copyFile.generateResized(jobPic, "job", pathsProps.getProperties().getImgRoot(), workPicFile.getContentType());
			
		artist.setWorkMaxQuality(maxWidthJob);
		artist.setWorkPic(jobPic);
		artist.setWorkPicType(workPicFile.getContentType());
		
	

		Address address = new Address();
			
		address.setActive(true);
		address.setCreated(new Date());
		address.setCurrent(true);
		address.setIdCountries(artistDTO.getCountry().getIdCountries());
			
		Address a = addressRepository.save(address);
			
		artist.setIdAddress(a.getIdAddress());
		
		
		
		
		artist.setActive(true);
		


		artist.setCreated(new Date());
			
		
		Artists arts = artistsRepository.save(artist);
		
		
		List<Language> languages = languageRepository.selectAll();
		
		for(Language lang : languages) {
			Artistcontent artscont = new Artistcontent();
			
			artscont.setActive(true);
			artscont.setIdLanguage(lang.getIdLanguage());
			artscont.setIdArtists(arts.getIdArtists());
			artscont.setCreated(new Date());
			
			artistcontentRepository.save(artscont);
		}
		
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
		
		
	}
	
	
	public void update() throws IOException {
		Artists artist =  modelMapper.map(artistDTO, Artists.class);
		
		Address address = addressRepository.findById(artist.getIdAddress()).orElse(null);
		
		if (logoFile.getFileName() != null) {
			
			copyFile.deleteFile("logos", artist.getLogo(), pathsProps.getProperties().getImgRoot());
			
			String logo = saveLogo();
			
			int maxWidthLogo = copyFile.generateResized(logo, "logos", pathsProps.getProperties().getImgRoot(), logoFile.getContentType());
		
			artist.setLogo(logo);
			
			artist.setLogoMaxQuality(maxWidthLogo);
		
			artist.setLogoType(logoFile.getContentType());
		
		}
		
		
		if (artistPicFile.getFileName() != null) {
			
			copyFile.deleteFile("artist", artist.getArtistPic(), pathsProps.getProperties().getImgRoot());
			
			String artistPic = saveArtist();
			
			int maxWidthArtist = copyFile.generateResized(artistPic, "artist", pathsProps.getProperties().getImgRoot(), artistPicFile.getContentType());
			
			artist.setArtistPic(artistPic);
			
			artist.setArtistMaxQuality(maxWidthArtist);
			
			artist.setArtistPicType(artistPicFile.getContentType());
		}
		
		if (workPicFile.getFileName() != null) {
			copyFile.deleteFile("job", artist.getWorkPic(), pathsProps.getProperties().getImgRoot());
			
			
			String jobPic = saveJob();
			
			int maxWidthJob = copyFile.generateResized(jobPic, "job", pathsProps.getProperties().getImgRoot(), workPicFile.getContentType());
			
			artist.setWorkMaxQuality(maxWidthJob);
			artist.setWorkPic(jobPic);
			artist.setWorkPicType(workPicFile.getContentType());
		}
	
		
		
		
		artist.setUpdated(new Date());
		
		address.setIdCountries(artistDTO.getCountry().getIdCountries());
		
		addressRepository.save(address);
			
		
		artistsRepository.save(artist);
		
		
		
	
		
		
		
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
		
		
	}
	
	
	private String saveLogo() throws IOException {
		String ext = "";
		
		if (logoFile.getContentType().contains("jpeg")) {
			ext="jpg";
		}else{
			ext="png";
		}
		
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
		
		copyFile.copyFile("logos", name, logoFile.getInputStream(), pathsProps.getProperties().getImgRoot());
	
		
		return name;
	}
	
	
	private String saveArtist() throws IOException {
		String ext = "";
		
		if (artistPicFile.getContentType().contains("jpeg")) {
			ext="jpg";
		}else{
			ext="png";
		}
		
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
		
		copyFile.copyFile("artist", name, artistPicFile.getInputStream(), pathsProps.getProperties().getImgRoot());
	
		
		
		return name;
	}
	
	
	private String saveJob() throws IOException {
		String ext = "";
		
		if (workPicFile.getContentType().contains("jpeg")) {
			ext="jpg";
		}else{
			ext="png";
		}
		
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
		
		copyFile.copyFile("job", name, workPicFile.getInputStream(), pathsProps.getProperties().getImgRoot());
	
		
		
		return name;
	}
	
	
	
	
	public void cancel() {
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}

	public void showAddress(Address address) {
		 Map<String,Object> options = new HashMap<String, Object>();
	       options.put("resizable", false);
	       options.put("positionType", "absolute");
	       options.put("styleClass", "dialogwindow");
	       
	       Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	       sessionMap.put("address", address);
	       
	       PrimeFaces instance = PrimeFaces.current();
		   instance.dialog().openDynamic("updateaddress", options, null);
	}
	
	 public void showList(Long idArtists) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        options.put("contentHeight", "700");
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("idArtists", idArtists);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("artistcontentlist", options, null);
	 }
	 
	 public void onCloseDialog(SelectEvent selectEvent) {
	      Address add = addressRepository.findById(address.getIdAddress()).orElse(null);
	      
	      address = add;
	  }

}
