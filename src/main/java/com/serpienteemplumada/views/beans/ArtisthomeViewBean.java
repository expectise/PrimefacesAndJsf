package com.serpienteemplumada.views.beans;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.ArtistDTO;
import com.serpienteemplumada.model.Artistcontent;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.repository.ArtistcontentRepository;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.repository.ArtistspageRepository;
import com.serpienteemplumada.utils.ScapeChars;

import lombok.Data;

@Data
public abstract class ArtisthomeViewBean {
	
	@Autowired
	private ArtistcontentRepository artistcontentRepository;
	
	@Autowired
	private ArtistspageRepository artistspageRepository;
	
	@Autowired
	private ArtistsRepository artistsRepository;
	
	private Long idLanguage;
	
	private String acrom;
	
	private Long idArtists;
	
	private ArtistDTO artistDTO = new ArtistDTO();
	
	private List<ArtistDTO> artistDTOs;
	
	private String artistText;
	
	
	

	protected void runner() {
		Artistcontent artist = artistcontentRepository.selectArtistcontentAndLanguage(idArtists, idLanguage);
		

		artistDTO.setArtistPic("../../../images/artists/4/" + artist.getIdArtists() + "/" + ScapeChars.stripDiacritics(artist.getArtists().getFirstname() + " " + artist.getArtists().getLastname() + " " + artist.getArtists().getMiddlename(), artist.getArtists().getArtistPic()));
		
		artistDTO.setWorkPic("../../../images/job/7/" + artist.getIdArtists() + "/" + ScapeChars.stripDiacritics(artist.getArtists().getMark(), artist.getArtists().getWorkPic()));
		
		artistDTO.setStatement(artist.getStatement());
		
		artistDTO.setMark(artist.getArtists().getMark());
		
		artistText = artistspageRepository.selectArtistpage(idLanguage).getContent();
		
		artistDTOs = asignArtists();
	}
	
	
	private List<ArtistDTO> asignArtists() {
		List<Artists> artists = artistsRepository.selectAllActive();
		
		List<ArtistDTO> lsout = new ArrayList<>();
		
		for (Artists art : artists) {
			ArtistDTO out = new ArtistDTO();
			
			out.setMark(art.getMark());
			out.setArtistPic("../../../images/artists/8/" + art.getIdArtists() + "/" + ScapeChars.stripDiacritics(art.getFirstname() + " " + art.getLastname() + " " + art.getMiddlename(), art.getArtistPic()));
			
			out.setUrl("../../artists/" + art.getIdArtists() + "/" + ScapeChars.stripDiacritics(art.getMark()));
			
			lsout.add(out);
		}
		
		return lsout;
	}

}
