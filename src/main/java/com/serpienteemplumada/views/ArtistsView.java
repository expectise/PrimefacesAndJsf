package com.serpienteemplumada.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.views.beans.ArtistsViewBean;

@Scope("view")
@Component("artists")
public class ArtistsView extends ArtistsViewBean{
	@Autowired
	private ArtistsRepository artistsRepository;
	
	@PostConstruct
	public void init() {
		List<Artists> list = artistsRepository.selectAll();
		
		setArtists(list);
	}

}
