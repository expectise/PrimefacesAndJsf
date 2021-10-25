package com.serpienteemplumada.views.beans;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.serpienteemplumada.dto.CartDTO;
import com.serpienteemplumada.dto.ProducthomeDTO;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Cartcontent;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.model.Pictureproduct;
import com.serpienteemplumada.model.Productcontent;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.repository.CartcontentRepository;
import com.serpienteemplumada.repository.PictureproductRepository;
import com.serpienteemplumada.repository.ProductcontentRepository;
import com.serpienteemplumada.utils.PathsProps;
import com.serpienteemplumada.utils.ScapeChars;

import lombok.Data;

@Data
public abstract class CartViewBean {
	private List<CartDTO> cartDTO;
	
	@Autowired
	private CartcontentRepository cartcontentRepository;
	
	
	@Autowired
	private PictureproductRepository pictureproductRepository;
	
	@Autowired
	private ProductcontentRepository productcontentRepository;
	
	@Autowired
	private ArtistsRepository artistsRepository;
	
	@Autowired
	private PathsProps pathsProps;
	
	private Long idCart;
	
	private BigDecimal total;
	
	private Integer products;
	
	private Long idLanguage;
	
	private String acrom;
	
	private Long id;

	public void addToCart(ProducthomeDTO product) {
		if (exist(product)) {
			Cartcontent content = cartcontentRepository.selectCartcontentProduct(idCart, product.getIdProducts());
			
			if (content != null)
			cartcontentRepository.delete(content);
			
		}else {
			Cartcontent content = new Cartcontent();
			
			content.setActive(true);
			content.setCreated(new Date());
			content.setIdCart(idCart);
			content.setIdProducts(product.getIdProducts());
			
			cartcontentRepository.save(content);
		}
		
		
		runner();
	}
	
	
	protected void runner() {
		Pageable pag = PageRequest.of(0, 1, Sort.by("idPictureproduct"));
		
		List<Cartcontent> list = cartcontentRepository.selectCartcontent(idCart);
		
		List<CartDTO> lsout = new ArrayList<>();
		
		BigDecimal whole = new BigDecimal(0);
		
		BigDecimal price;
		
		Integer prodn = 0;
		
		for (Cartcontent cart : list) {
			if (cart.getProducts().getStock() > 0) {
				CartDTO out = new CartDTO();
				
				price = cart.getProducts().getDiscount() > 0 ?  (new BigDecimal(100).subtract(new BigDecimal(cart.getProducts().getDiscount()))).divide(new BigDecimal(100)).multiply(cart.getProducts().getPrice()) : cart.getProducts().getPrice();
						
				Productcontent content = productcontentRepository.selectProductlang(cart.getIdProducts(), idLanguage);
				
				out.setPrice(price);
				
				whole = whole.add(price);
				
				
				
				
				out.setLink(pathsProps.getProperties().getUrl() + acrom.toLowerCase() + "/product/" + cart.getProducts().getIdProducts() + "/" + ScapeChars.stripDiacritics(content.getTitle()));
				
				List<Pictureproduct> pic = pictureproductRepository.selectFirstPicProduct(cart.getIdProducts(), pag);
				

				out.setIdProducts(cart.getIdProducts());
				out.setImageUrl(pathsProps.getProperties().getUrl() +  "images/product/5/" + pic.get(0).getIdPictureproduct() + "/" + ScapeChars.stripDiacritics("p-" + content.getTitle(), pic.get(0).getFile()));
			    out.setTitle(content.getTitle());
				
				lsout.add(out);
				
				prodn++;
			}else {
				cartcontentRepository.delete(cart);
			}
		}
		
		total = whole;
		
		products = prodn;
		
		cartDTO = lsout;
		
		
		
	}
	
	public boolean exist(ProducthomeDTO product) {
		
		for (CartDTO cart : cartDTO) {
			if (cart.getIdProducts().equals(product.getIdProducts())) {
				return true;
			}
		}

		
		return false;
	}
	
	public void delete(CartDTO cart) throws IOException, InterruptedException {
		
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String uri = request.getRequestURI();
		
		Cartcontent content = cartcontentRepository.selectCartcontentProduct(idCart, cart.getIdProducts());
		
		cartcontentRepository.delete(content);
		
		
		runner();
		
		
		
		
		if (uri.contains("index.xhtml")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pathsProps.getProperties().getUrl() +  acrom.toLowerCase() + "/");
		}else if(uri.contains("product.xhtml")) {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(pathsProps.getProperties().getUrl() +  acrom.toLowerCase() + "/product/" + id + "/" + ScapeChars.stripDiacritics(cart.getTitle()));
		}else if(uri.contains("artists.xhtml")) {

			
			Artists artists = artistsRepository.findById(id).orElse(null);

			if (artists != null)
			FacesContext.getCurrentInstance().getExternalContext().redirect(pathsProps.getProperties().getUrl() +  acrom.toLowerCase() + "/artists/" + artists.getIdArtists() + "/" + ScapeChars.stripDiacritics(artists.getMark()));
		}
		

	}
	
}
