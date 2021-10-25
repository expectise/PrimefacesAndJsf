package com.serpienteemplumada.views.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.PictureproductDTO;
import com.serpienteemplumada.dto.PictureproductcontentDTO;
import com.serpienteemplumada.model.Pictureproductcontent;
import com.serpienteemplumada.repository.PictureproductcontentRepository;

import lombok.Data;

@Data
public abstract class PictureproductcontentViewBean {
	
	@Autowired
	private PictureproductcontentRepository pictureproductcontentRepository;
	
	private PictureproductDTO pictureproductDTO;
	
	private PictureproductcontentDTO pictureproductcontentDTO;
	
	private List<PictureproductcontentDTO> PictureproductcontentDTOs;
	
	private Boolean visible = false;
	
	
	protected void runner() {
		List<Pictureproductcontent> list = pictureproductcontentRepository.selectPictureproductcontent(pictureproductDTO.getIdPictureproduct());
	
		
		List<PictureproductcontentDTO> listout = new ArrayList<>();
		
		for (Pictureproductcontent picc : list) {
			PictureproductcontentDTO npicc = new PictureproductcontentDTO();
			
			npicc.setLanguage(picc.getLanguage().getLanguage());
			npicc.setDescription(picc.getDescription());
			npicc.setIdPictureproductcontent(picc.getIdPictureproductcontent());
			
			listout.add(npicc);
		}
		
		PictureproductcontentDTOs = listout;
	}
	
	
	
	public void submit() {
		Pictureproductcontent pic = pictureproductcontentRepository.findById(pictureproductcontentDTO.getIdPictureproductcontent()).orElse(null);
		
		if (pic != null) {
			pic.setDescription(pictureproductcontentDTO.getDescription());
			pic.setUpdated(new Date());
			
			pictureproductcontentRepository.save(pic);
		}
		
		runner();
		visible = false;
	}
	
	public void cancel() {
		visible = false;
	}
	
	public void onSelected(PictureproductcontentDTO picturecontent) {
		pictureproductcontentDTO = picturecontent;
		visible = true;
	}

}
