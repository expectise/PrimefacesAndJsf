package com.serpienteemplumada.views.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.PictureproductDTO;
import com.serpienteemplumada.dto.ProductsDTO;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.model.Pictureproduct;
import com.serpienteemplumada.model.Pictureproductcontent;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.repository.PictureproductRepository;
import com.serpienteemplumada.repository.PictureproductcontentRepository;
import com.serpienteemplumada.utils.CopyFile;
import com.serpienteemplumada.utils.PathsProps;
import com.serpienteemplumada.utils.ScapeChars;

import lombok.Data;

@Data
public abstract class PictureproductViewBean {
	
	private List<PictureproductDTO> pictureproductDTOs;
	
	
	private ProductsDTO productsDTO;

	@Autowired
	private PictureproductRepository pictureproductRepository;
	
	@Autowired
	private PictureproductcontentRepository pictureproductcontentRepository;
	
	@Autowired
	private CopyFile copyFile;
	 
	@Autowired
	private PathsProps pathsProps;
	
	private UploadedFile pictureFile;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	
	
	 public void onSelected(PictureproductDTO pictureproduct) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("pictureproduct", pictureproduct);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("picturecontent", options, null);
	 }
	 
	 public void onDelete(PictureproductDTO pictureproduct) {
		 
		 
		 Pictureproduct pic = pictureproductRepository.findById(pictureproduct.getIdPictureproduct()).orElse(null);
		 
		 if (pic != null) {
			 
			 copyFile.deleteFile("product", pic.getFile(), pathsProps.getProperties().getImgRoot());
			 
			 List<Pictureproductcontent> list = pictureproductcontentRepository.findByIdPictureproduct(pic.getIdPictureproduct());
			 
			 for (Pictureproductcontent picc : list) {
				 pictureproductcontentRepository.delete(picc);
			 }
			 
			 pictureproductRepository.delete(pic);
			 
			 runner();
		 }
	 }
	 

	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      if (out == 1) {
	    	  runner();
	      }else if(out == 2) {
	    	  runner();
	      }else {
	    	  runner();
	      }
	  }
	 
	 
	 protected void runner() {
		 List<Pictureproduct> pictures = pictureproductRepository.findByIdProducts(productsDTO.getIdProducts());
			
			
			List<PictureproductDTO> list = new ArrayList<>();
			
			int i = 1;
			for (Pictureproduct pic : pictures) {
					PictureproductDTO picdto = new PictureproductDTO();
					
					picdto.setIdPictureproduct(pic.getIdPictureproduct());
					picdto.setUrlThumbnail("../../images/product/6/" + pic.getIdPictureproduct() + "/" + ScapeChars.stripDiacritics("p-" + productsDTO.getQr() + "-" + i, pic.getFile()));
					picdto.setFile(pic.getFile());
					picdto.setUrltoImage("../../images/product/7/" + pic.getIdPictureproduct() + "/" + ScapeChars.stripDiacritics("p-" + productsDTO.getQr() + "-" + i, pic.getFile()));
				
					list.add(picdto);
					
					i++;
				}
			
			
			pictureproductDTOs = list;
	 
	 }
	 
	 public void submit() throws IOException {
			if (pictureFile.getFileName() != null) {

				String picture = savePicture();
				
				int maxWidthLogo = copyFile.generateResized(picture, "product", pathsProps.getProperties().getImgRoot(), pictureFile.getContentType());
			
				Pictureproduct picp = new Pictureproduct();
				
				
				
				picp.setActive(true);
				picp.setCreated(new Date());
				picp.setFile(picture);
				picp.setFileMaxQuality(maxWidthLogo);
				picp.setFileType(pictureFile.getContentType());
				picp.setIdProducts(productsDTO.getIdProducts());
		
				Pictureproduct crea = pictureproductRepository.save(picp);
				
				
				List<Language> list = languageRepository.selectAll();
				
				for (Language lang : list) {
					Pictureproductcontent picpc = new Pictureproductcontent();
					
					
					picpc.setCreated(new Date());
					picpc.setActive(true);
					picpc.setIdLanguage(lang.getIdLanguage());
					picpc.setIdPictureproduct(crea.getIdPictureproduct());
					
					pictureproductcontentRepository.save(picpc);
				}
				
				pictureFile = null;
				
				runner();
			
			}
	 }
	 
	 private String savePicture() throws IOException {
			String ext = "";
			
			if (pictureFile.getContentType().contains("jpeg")) {
				ext="jpg";
			}else{
				ext="png";
			}
			
			String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
			
			copyFile.copyFile("product", name, pictureFile.getInputStream(), pathsProps.getProperties().getImgRoot());
		
			
			return name;
		}
}
