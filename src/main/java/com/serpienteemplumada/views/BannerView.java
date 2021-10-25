package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.BannersDTO;
import com.serpienteemplumada.model.Banners;
import com.serpienteemplumada.utils.ScapeChars;
import com.serpienteemplumada.views.beans.BannerViewBean;

@Scope("view")
@Component("banner")
public class BannerView extends BannerViewBean{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Banners input = (Banners) sessionMap.get("banner");
		
		if (input != null) {
			setBanner(input);
			
			BannersDTO dto = modelMapper.map(input, BannersDTO.class);
			
			setBannersDTO(dto);
			
			
			setFile1url("../../images/banner/7/"+ input.getIdBanners() +"/1/" + ScapeChars.stripDiacritics(input.getTitle(), input.getFile()));

			setFile2url("../../images/banner/7/"+ input.getIdBanners() +"/2/" + ScapeChars.stripDiacritics(input.getTitle(), input.getFile2()));
		
			setFile3url("../../images/banner/7/"+ input.getIdBanners() +"/3/" + ScapeChars.stripDiacritics(input.getTitle(), input.getFile3()));
		
			setFile4url("../../images/banner/7/"+ input.getIdBanners() +"/4/" + ScapeChars.stripDiacritics(input.getTitle(), input.getFile4()));
		
			 sessionMap.remove("banner");
		}else {
			setBannersDTO(new BannersDTO());
		}
	}

}
