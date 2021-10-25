package com.serpienteemplumada.views;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.ArtistDTO;
import com.serpienteemplumada.model.Address;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.repository.AddressRepository;
import com.serpienteemplumada.repository.CountriesRepository;
import com.serpienteemplumada.utils.ScapeChars;
import com.serpienteemplumada.views.beans.ArtistViewBean;

@Scope("view")
@Component("artist")
public class ArtistView extends ArtistViewBean{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CountriesRepository countriesRepository;
	
	
	@Autowired
	private AddressRepository addressRepository;
	
	@PostConstruct
	public void init(){
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Artists input = (Artists) sessionMap.get("artist");
		
		List<Countries> countries = countriesRepository.selectAllActive();
		
		setCountries(countries);
		
		if (input != null) {
			
			ArtistDTO artDTO = modelMapper.map(input, ArtistDTO.class);
			
			
			
		
			Address addrs = addressRepository.findById(input.getIdAddress()).orElse(null);
			
			setAddress(addrs);
			
			artDTO.setCountry(addrs.getCountries());
			
			setArtistDTO(artDTO);
			
			setArtisturl("../../images/artists/7/" + artDTO.getIdArtists() + "/" + ScapeChars.stripDiacritics(artDTO.getFirstname() + " " + artDTO.getLastname() + " " + artDTO.getMiddlename(), artDTO.getArtistPic()));
			
			
			setJoburl("../../images/job/7/" + artDTO.getIdArtists() + "/" + ScapeChars.stripDiacritics(artDTO.getMark(), artDTO.getWorkPic()));
			
			
			setLogourl("../../images/logo/7/" + artDTO.getIdArtists() + "/" + ScapeChars.stripDiacritics(artDTO.getMark(), artDTO.getLogo()));
			
			
			sessionMap.remove("artist");
			
		}else {
			setArtistDTO(new ArtistDTO());
		}
		
	}

}
