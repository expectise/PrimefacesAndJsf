package com.serpienteemplumada.views.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.BannercontentDTO;
import com.serpienteemplumada.dto.BannersDTO;
import com.serpienteemplumada.model.Bannercontent;
import com.serpienteemplumada.repository.BannercontentRepository;

import lombok.Data;

@Data
public abstract class BannercontentViewBean {
	
	@Autowired
	private BannercontentRepository bannercontentRepository;
	
	private BannersDTO bannersDTO;
	
	private BannercontentDTO bannercontentDTO;
	
	private List<BannercontentDTO> bannercontentDTOs;
	
	private Boolean visible = false;
	
	
	
	protected void runner() {
		List<Bannercontent> list = bannercontentRepository.selectBannercontent(bannersDTO.getIdBanners());
		
		List<BannercontentDTO> lsout = new ArrayList<>();
		
		for (Bannercontent cc : list) {
			BannercontentDTO ncc = new BannercontentDTO();
			
			ncc.setIdBannercontent(cc.getIdBannercontent());
			ncc.setLanguage(cc.getLanguage().getLanguage());
			ncc.setSubtitleBanner(cc.getSubtitleBanner());
			ncc.setTitleBigBanner(cc.getTitleBigBanner());
			ncc.setTitleShortBanner(cc.getTitleShortBanner());
			ncc.setCreated(cc.getCreated());
			ncc.setUpdated(cc.getUpdated());
			
			lsout.add(ncc);
		}
		
		bannercontentDTOs = lsout;
	}
	
	public void submit() {
		Bannercontent cc = bannercontentRepository.findById(bannercontentDTO.getIdBannercontent()).orElse(null);
		
		if (cc != null) {
			cc.setUpdated(new Date());
			cc.setSubtitleBanner(bannercontentDTO.getSubtitleBanner());
			cc.setTitleBigBanner(bannercontentDTO.getTitleBigBanner());
			cc.setTitleShortBanner(bannercontentDTO.getTitleShortBanner());
			
			bannercontentRepository.save(cc);
		}
		
		visible = false;
	
	}
	
	public void onSelected(BannercontentDTO cc) {
		bannercontentDTO = cc;
		visible = true;
	}
	
	public void cancel() {
		visible = false;
	}
}
