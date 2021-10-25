package com.serpienteemplumada.views.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.PictureproductDTO;
import com.serpienteemplumada.dto.ProducthomeDTO;
import com.serpienteemplumada.model.Pictureproduct;
import com.serpienteemplumada.model.Productcontent;
import com.serpienteemplumada.repository.PictureproductRepository;
import com.serpienteemplumada.repository.ProductcontentRepository;
import com.serpienteemplumada.utils.ScapeChars;

import lombok.Data;

@Data
public abstract class ProductshomeViewBean {
	
	@Autowired
	private ProductcontentRepository productcontentRepository;
	
	@Autowired
	private PictureproductRepository pictureproductRepository;
	
	private Long idCart;
	
	private Long idLanguage;
	
	private String acrom;
	
	private Long idProducts;
	
	private ProducthomeDTO producthomeDTO;
	
	private List<PictureproductDTO> pictureproductDTOs;
	
	protected void runner() {
		producthomeDTO = new ProducthomeDTO();
		Productcontent prod = productcontentRepository.selectProductlang(idProducts, idLanguage);
		
		BigDecimal price = prod.getProducts().getDiscount() > 0 ?  (new BigDecimal(100).subtract(new BigDecimal(prod.getProducts().getDiscount()))).divide(new BigDecimal(100)).multiply(prod.getProducts().getPrice()) : prod.getProducts().getPrice();
		
		producthomeDTO.setTitle(prod.getTitle());
		producthomeDTO.setContent(prod.getContent());
		producthomeDTO.setMaterials(prod.getMaterials());
		producthomeDTO.setPrice(price);
		producthomeDTO.setOrgPrice(prod.getProducts().getPrice());
		producthomeDTO.setDiscount(prod.getProducts().getDiscount());
		producthomeDTO.setQr(prod.getProducts().getQr());
		producthomeDTO.setStock(prod.getProducts().getStock());
		producthomeDTO.setMark(prod.getProducts().getArtists().getMark());
		producthomeDTO.setIdProducts(prod.getIdProducts());
		
		List<Pictureproduct> pics = pictureproductRepository.findByIdProducts(prod.getProducts().getIdProducts());
		
		int i = 0;
		
		List<PictureproductDTO> lsout = new ArrayList<>();
		for (Pictureproduct pic : pics) {
			PictureproductDTO out = new PictureproductDTO();
			
			
			out.setUrltoImage("../../../images/product/8/" + pic.getIdPictureproduct() + "/" + ScapeChars.stripDiacritics("p-" + prod.getProducts().getQr(), pic.getFile()));
			
			lsout.add(out);
			
			if (i == 0)
				producthomeDTO.setImageUrl("../../../images/product/8/" + pic.getIdPictureproduct() + "/" + ScapeChars.stripDiacritics("p-" + prod.getProducts().getQr(), pic.getFile()));
				
			out.setIndex(i);
			
			i++;
		}
		
		pictureproductDTOs = lsout;
	}

}
