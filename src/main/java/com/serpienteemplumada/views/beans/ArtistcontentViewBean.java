package com.serpienteemplumada.views.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.ArtistcontentDTO;
import com.serpienteemplumada.model.Artistcontent;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.repository.ArtistcontentRepository;
import com.serpienteemplumada.repository.ArtistsRepository;

import lombok.Data;

@Data
public abstract class ArtistcontentViewBean {
	
	private List<ArtistcontentDTO> artistcontentDTOs;
	
	private ArtistcontentDTO artistcontentDTO;
	
	private Long idArtists;
	
	private Boolean visible = false;
	
	@Autowired
	private ArtistcontentRepository artistcontentRepository;
	
	@Autowired
	private ArtistsRepository artistsRepository;

	protected void runner(Long id) {
		List<Artistcontent> list = artistcontentRepository.selectArtistcontent(id);
		
		List<ArtistcontentDTO> lsout = new ArrayList<>();
		
		idArtists = id;
		
		for (Artistcontent c : list) {
			ArtistcontentDTO out = new ArtistcontentDTO();
			out.setIdArtistcontent(c.getIdArtistcontent());
			out.setLanguage(c.getLanguage().getLanguage());
			out.setTitle(c.getTitle());
			out.setArtistpic(c.getArtistpic());
			out.setArtistwork(c.getArtistwork());
			out.setIdLanguage(c.getIdLanguage());
			out.setStatement(c.getStatement());
			
			lsout.add(out);
		}
		
		setArtistcontentDTOs(lsout);
	}
	
	
	public void cancel() {
			visible = false;
	}
	
	public void submit() {
		Artistcontent art = artistcontentRepository.findById(artistcontentDTO.getIdArtistcontent()).orElse(null);
		
		if (art != null) {
			art.setUpdated(new Date());
			art.setArtistpic(artistcontentDTO.getArtistpic());
			art.setArtistwork(artistcontentDTO.getArtistwork());
			art.setStatement(artistcontentDTO.getStatement());
			art.setTitle(artistcontentDTO.getTitle());
			
			artistcontentRepository.save(art);
			
			
			Artists artist = artistsRepository.findById(idArtists).orElse(null);
			
			artist.setUpdated(new Date());
			
			artistsRepository.save(artist);
		}
		
		
		visible = false;
	}
	
	
	public void onSelected(ArtistcontentDTO art){
		artistcontentDTO = art;
		visible = true;
	 
	}
}
