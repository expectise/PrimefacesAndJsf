package com.serpienteemplumada.scheduler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Artistcontent;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Artistspage;
import com.serpienteemplumada.model.Bannercontent;
import com.serpienteemplumada.model.Banners;
import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.model.Categorycontent;
import com.serpienteemplumada.model.Countries;
import com.serpienteemplumada.model.Countrycontent;
import com.serpienteemplumada.model.Pictureproduct;
import com.serpienteemplumada.model.Pictureproductcontent;
import com.serpienteemplumada.model.Productcontent;
import com.serpienteemplumada.model.Products;
import com.serpienteemplumada.model.Tags;
import com.serpienteemplumada.model.Tagscontent;
import com.serpienteemplumada.repository.ArtistcontentRepository;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.repository.ArtistspageRepository;
import com.serpienteemplumada.repository.BannercontentRepository;
import com.serpienteemplumada.repository.BannersRepository;
import com.serpienteemplumada.repository.CategoryRepository;
import com.serpienteemplumada.repository.CategorycontentRepository;
import com.serpienteemplumada.repository.CountriesRepository;
import com.serpienteemplumada.repository.CountrycontentRepository;
import com.serpienteemplumada.repository.PictureproductRepository;
import com.serpienteemplumada.repository.PictureproductcontentRepository;
import com.serpienteemplumada.repository.ProductcontentRepository;
import com.serpienteemplumada.repository.ProductsRepository;
import com.serpienteemplumada.repository.TagsRepository;
import com.serpienteemplumada.repository.TagscontentRepository;

@Component
public class CheckLanguages implements Serializable{
	private static final long serialVersionUID = 7963400272594866219L;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategorycontentRepository categorycontentRepository;
	
	@Autowired
	private CountriesRepository countriesRepository;
	
	@Autowired
	private CountrycontentRepository countrycontentRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	@Autowired
	private TagscontentRepository tagscontentRepository;
	
	@Autowired
	private ArtistsRepository artistsRepository;
	
	@Autowired
	private ArtistcontentRepository artistcontentRepository;
	
	@Autowired
	private ArtistspageRepository artistspageRepository;
	
	@Autowired
	private ProductcontentRepository productcontentRepository;
	
	@Autowired
	private PictureproductRepository pictureproductRepository;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private PictureproductcontentRepository pictureproductcontentRepository;
	
	@Autowired
	private BannersRepository bannersRepository;
	
	@Autowired
	private BannercontentRepository bannercontentRepository;
	
	
	public void updateLanguages(Long id) {
		processCategory(id);
		
		processCountry(id);
		
		processTags(id);
		
		processArtist(id);
		
		processArtistpage(id);
		
		processProductcontent(id);
		
		processPictureproductcontent(id);
		
		processBannercontent(id);
	}
	
	
	private void processCategory(Long id) {
		
		List<Category> categories = categoryRepository.selectAll();
		
		for (Category cat: categories) {
			Categorycontent catc = new Categorycontent();
			
			catc.setActive(true);
			catc.setCreated(new Date());
			catc.setIdCategory(cat.getIdCategory());
			catc.setIdLanguage(id);
			
			categorycontentRepository.save(catc);
		}
	}
	
	private void processCountry(Long id) {
		List<Countries> countries = countriesRepository.selectAll();
		
		for (Countries con : countries) {
			Countrycontent conc = new Countrycontent();
			
			conc.setActive(true);
			conc.setIdCountries(con.getIdCountries());
			conc.setIdLanguage(id);
			conc.setCreated(new Date());
			
			countrycontentRepository.save(conc);
		}
	}
	
	private void processTags(Long id) {
		
		List<Tags> tags = tagsRepository.selectAll();
		
		for (Tags tag : tags) {
			Tagscontent tagsc = new Tagscontent();
			
			tagsc.setActive(true);
			tagsc.setCreated(new Date());
			tagsc.setIdTags(tag.getIdTags());
			tagsc.setIdLanguage(id);
			
			tagscontentRepository.save(tagsc);
			
		}
	}
	
	private void processArtist(Long id) {
		
		List<Artists> artists = artistsRepository.selectAll();
		
		for (Artists art : artists) {
			Artistcontent artc = new Artistcontent();
			
			artc.setCreated(new Date());
			artc.setActive(true);
			artc.setIdLanguage(id);
			artc.setIdArtists(art.getIdArtists());
			
			artistcontentRepository.save(artc);
			
		}
	}
	
	
	private void processArtistpage(Long id) {
		
		Artistspage artistspage = new Artistspage();
		
		artistspage.setActive(true);
		artistspage.setCreated(new Date());
		artistspage.setIdLanguage(id);
		
		artistspageRepository.save(artistspage);
	}
	
	private void processProductcontent(Long id) {
		List<Products> list = productsRepository.selectAll();
		
		for (Products product : list) {
			Productcontent content = new Productcontent();
			
			content.setActive(true);
			content.setIdLanguage(id);
			content.setIdProducts(product.getIdProducts());
			content.setCreated(new Date());

			productcontentRepository.save(content);
		}
	}
	
	private void processPictureproductcontent(Long id) {
		List<Pictureproduct> list = pictureproductRepository.selectAll();
		
		for (Pictureproduct pic : list) {
			Pictureproductcontent content = new Pictureproductcontent();
			
			content.setActive(true);
			content.setCreated(new Date());
			content.setIdPictureproduct(pic.getIdPictureproduct());
			content.setIdLanguage(id);
			
			pictureproductcontentRepository.save(content);
		}
	}
	
	private void processBannercontent(Long id) {
		List<Banners> list = bannersRepository.selectAll();
		
		for (Banners ban : list) {
			Bannercontent content = new Bannercontent();
			content.setActive(true);
			content.setCreated(new Date());
			content.setIdBanners(ban.getIdBanners());
			content.setIdLanguage(id);
		
			bannercontentRepository.save(content);
		}
	}
}
