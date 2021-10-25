package com.serpienteemplumada.views.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.serpienteemplumada.dto.ArtistDTO;
import com.serpienteemplumada.dto.ProducthomeDTO;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Asigncategory;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.model.Pictureproduct;
import com.serpienteemplumada.model.Productcontent;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.repository.ArtistspageRepository;
import com.serpienteemplumada.repository.AsigncategoryRepository;
import com.serpienteemplumada.repository.CategorycontentRepository;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.repository.PictureproductRepository;
import com.serpienteemplumada.repository.PictureproductcontentRepository;
import com.serpienteemplumada.repository.ProductcontentRepository;
import com.serpienteemplumada.repository.ProductsRepository;
import com.serpienteemplumada.utils.ScapeChars;

import lombok.Data;

@Data
public abstract class HomeViewBean {
	
	private List<ProducthomeDTO> pcat1;
	
	private List<ProducthomeDTO> pcat2;
	
	private List<ProducthomeDTO> pcat3;
	
	private List<ProducthomeDTO> pcat4;
	
	private List<ProducthomeDTO> pcat5;
	
	private List<ProducthomeDTO> pcat6;
	
	private List<ProducthomeDTO> pcat7;
	
	private List<ProducthomeDTO> pcat8;
	
	private List<ProducthomeDTO> pcat9;
	
	private Long idLanguage;
	
	private String cat1;
	
	private String cat2;
	
	private String cat3;
	
	private String cat4;
	
	private String cat5;
	
	private String cat6;
	
	private String cat7;
	
	private String cat8;
	
	private String cat9;
	
	private String artistText;
	
	private List<ArtistDTO> artistDTOs;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private ProductcontentRepository productcontentRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private PictureproductRepository pictureproductRepository;
	
	@Autowired
	private AsigncategoryRepository asigncategoryRepository;
	
	@Autowired
	private PictureproductcontentRepository pictureproductcontentRepository;

	@Autowired
	private CategorycontentRepository categorycontentRepository;
	
	@Autowired
	private ArtistspageRepository artistspageRepository;
	
	@Autowired
	private ArtistsRepository artistsRepository;
	
	
	
	protected void runner() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String l = req.getParameter("lang").toString();
		
		Language lang = languageRepository.selectLanguage(l);
		
		idLanguage = lang.getIdLanguage();
		
		

		
		cat1 = AsignCat(Long.parseLong("1"));
		
		cat2 = AsignCat(Long.parseLong("2"));
		
		cat3 = AsignCat(Long.parseLong("3"));
		
		cat4 = AsignCat(Long.parseLong("4"));
		
		cat5 = AsignCat(Long.parseLong("5"));
		
		cat6 = AsignCat(Long.parseLong("6"));
		
		cat7 = AsignCat(Long.parseLong("7"));
		
		cat8 = AsignCat(Long.parseLong("8"));
		
		cat9 = AsignCat(Long.parseLong("9"));
	
		
		pcat1 = AsignShop(Long.parseLong("1"));
		pcat2 = AsignShop(Long.parseLong("2"));
		pcat3 = AsignShop(Long.parseLong("3"));
		
		pcat4 = AsignShop(Long.parseLong("4"));
		pcat5 = AsignShop(Long.parseLong("5"));
		pcat6 = AsignShop(Long.parseLong("6"));
		
		pcat7 = AsignShop(Long.parseLong("7"));
		pcat8 = AsignShop(Long.parseLong("8"));
		pcat9 = AsignShop(Long.parseLong("9"));
		
		
		artistText = artistspageRepository.selectArtistpage(idLanguage).getContent();
		
		artistDTOs = asignArtists();
	}
	
	private String AsignCat(Long id) {
		return categorycontentRepository.findByIdCategoryAndIdLanguage(id, idLanguage).orElse(null).getTitle();
	}
	
	
	private List<ProducthomeDTO> AsignShop(Long id){
		Pageable pag = PageRequest.of(0, 15, Sort.by("idProducts"));
		
		Pageable pag2 = PageRequest.of(0, 1, Sort.by("idPictureproduct"));
		
		List<Asigncategory> cat = asigncategoryRepository.selectCategoryproduct(id, pag);
		
		List<ProducthomeDTO> lm = new ArrayList<>();
		
		
		for (Asigncategory mu : cat) {
			if (mu.getProducts().getStock() > 0) {
				ProducthomeDTO m = new ProducthomeDTO();
				
				List<Pictureproduct> pic = pictureproductRepository.selectFirstPicProduct(mu.getIdProducts(), pag2);
				
				m.setStock(mu.getProducts().getStock());
				
				m.setDiscount(mu.getProducts().getDiscount());
				m.setIdProducts(mu.getIdProducts());
				m.setPrice(mu.getProducts().getDiscount() > 0 ?  (new BigDecimal(100).subtract(new BigDecimal(mu.getProducts().getDiscount()))).divide(new BigDecimal(100)).multiply(mu.getProducts().getPrice()) : mu.getProducts().getPrice());
				m.setOrgPrice(mu.getProducts().getPrice());
				m.setDel(mu.getProducts().getDiscount() > 0 ? true : false);
				
				if (pic != null) {
				String alt = pictureproductcontentRepository.findByIdPictureproductAndIdLanguage(pic.get(0).getIdPictureproduct(), idLanguage).orElse(null).getDescription();
				m.setAlt(alt);
				
				m.setImageUrl("../images/product/4/" + pic.get(0).getIdPictureproduct() + "/" + ScapeChars.stripDiacritics("p-" + mu.getProducts().getQr(), pic.get(0).getFile()));
				
				}
				
				Productcontent content = productcontentRepository.selectProductlang(mu.getIdProducts(), idLanguage);
				
				if (content != null) {
					m.setLink("product/" + mu.getProducts().getIdProducts() +"/" + ScapeChars.stripDiacritics(content.getTitle()));
					m.setTitle(content.getTitle());
				}
				
				
				lm.add(m);
			}
		}
	
		return lm;
	}
	
	
	private List<ArtistDTO> asignArtists() {
		List<Artists> artists = artistsRepository.selectAllActive();
		
		List<ArtistDTO> lsout = new ArrayList<>();
		
		for (Artists art : artists) {
			ArtistDTO out = new ArtistDTO();
			
			out.setMark(art.getMark());
			out.setArtistPic("../images/artists/8/" + art.getIdArtists() + "/" + ScapeChars.stripDiacritics(art.getFirstname() + " " + art.getLastname() + " " + art.getMiddlename(), art.getArtistPic()));
			
			out.setUrl("artists/" + art.getIdArtists() + "/" + ScapeChars.stripDiacritics(art.getMark()));
			
			lsout.add(out);
		}
		
		return lsout;
	}

}
