package com.serpienteemplumada.views.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.ArtistspageDTO;

import com.serpienteemplumada.model.Artistspage;
import com.serpienteemplumada.repository.ArtistspageRepository;

import lombok.Data;

@Data
public abstract class ArtistpageViewBean {
	
	@Autowired
	private ArtistspageRepository artistspageRepository;
	
	private List<ArtistspageDTO> artistspageDTOs;
	
	private ArtistspageDTO artistspageDTO;
	
	private Boolean visible = false;
	

	
	protected void runner() {
		List<Artistspage> list = artistspageRepository.selectAll();
		
		List<ArtistspageDTO> lsout = new ArrayList<>();

		
		for (Artistspage c : list) {
			ArtistspageDTO out = new ArtistspageDTO();
			
			out.setContent(c.getContent());
			out.setIdArtistspage(c.getIdArtistspage());
			out.setIdLanguage(c.getIdLanguage());
			out.setLanguage(c.getLanguage().getLanguage());
			out.setTitle(c.getTitle());
			out.setCreated(c.getCreated());
			out.setUpdated(c.getUpdated());
			
			lsout.add(out);
		}
		
		setArtistspageDTOs(lsout);
	}
	
	public void cancel() {
		visible = false;
	
	}
	
	public void submit() {
		Artistspage art = artistspageRepository.findById(artistspageDTO.getIdArtistspage()).orElse(null);
		
		if (art != null) {
			art.setUpdated(new Date());
			art.setTitle(artistspageDTO.getTitle());
			art.setContent(artistspageDTO.getContent());
			
			artistspageRepository.save(art);
			
		}
		
		
		visible = false;
	}
	
	public void onSelected(ArtistspageDTO art){
		artistspageDTO = art;
		visible = true;
	 
	}
	
	public void close() {
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}

}
