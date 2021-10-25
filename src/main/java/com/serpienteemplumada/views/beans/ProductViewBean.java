package com.serpienteemplumada.views.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.modelmapper.ModelMapper;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;


import com.serpienteemplumada.dto.ProductsDTO;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Asigncategory;
import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.model.Productcontent;
import com.serpienteemplumada.model.Products;
import com.serpienteemplumada.repository.AsigncategoryRepository;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.repository.ProductcontentRepository;
import com.serpienteemplumada.repository.ProductsRepository;

import lombok.Data;

@Data
public abstract class ProductViewBean {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private AsigncategoryRepository asigncategoryRepository;
	
	private List<Artists> artists;
	
	private ProductsDTO productsDTO;
	
	private List<Category> categories;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private ProductcontentRepository productcontentRepository;
	
	
	public void submit() {
		Products product = modelMapper.map(productsDTO, Products.class);
		
		product.setIdArtists(productsDTO.getArtists().getIdArtists());
		
		product.setActive(true);
		product.setCreated(new Date());
		product.setVisitors(Long.parseLong("0"));
		product.setPurchased(0);
		
		Products prod = productsRepository.save(product);
		
		String[] cat = productsDTO.getCategories();
		
		for (int i = 0; i < cat.length; i++) {
			String x = cat[i];
			  
			Asigncategory asign = new Asigncategory();
			
			asign.setCreated(new Date());
			asign.setIdCategory(Long.parseLong(x));
			asign.setIdProducts(prod.getIdProducts());
			
			asigncategoryRepository.save(asign);
			
        }
		
		List<Language> languages = languageRepository.selectAll();
		
		for (Language lang : languages) {
			
			Productcontent content = new Productcontent();
			
			content.setActive(true);
			content.setCreated(new Date());
			content.setIdLanguage(lang.getIdLanguage());
			content.setIdProducts(prod.getIdProducts());
			
			productcontentRepository.save(content);
		}

		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(1);
	
		
	}
	
	
	public void update() {
		Products product = modelMapper.map(productsDTO, Products.class);
		
		product.setIdArtists(productsDTO.getArtists().getIdArtists());
		
		product.setUpdated(new Date());
		
		productsRepository.save(product);
		
		
		borraAsign();
		
		creaAsign();

		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(2);
	
		
	}
	
	
	private void borraAsign() {
		
		String[] pre = productsDTO.getPrecategories();
		
		for (int i = 0; i < pre.length; i++) {
			String x = pre[i];
		
			if (!existsCategory(x, productsDTO.getCategories())) {
				Asigncategory todel = asigncategoryRepository.selectIdbyCategoryAndProduct(Long.parseLong(x), productsDTO.getIdProducts());
				
				if (todel != null) {
					asigncategoryRepository.delete(todel);
				}
			}
		}
		
	}
	
	
	private void creaAsign() {
		
		String[] pre = productsDTO.getCategories();
		
		for (int i = 0; i < pre.length; i++) {
			String x = pre[i];
		
			if (!existsCategory(x, productsDTO.getPrecategories())) {
				Asigncategory cat = new Asigncategory();
				
				cat.setCreated(new Date());
				cat.setIdCategory(Long.parseLong(x));
				cat.setIdProducts(productsDTO.getIdProducts());
				
				asigncategoryRepository.save(cat);
			}
		}
		
	}
	
	
	private boolean existsCategory(String id, String[] cat) {
		
		for (int i = 0; i < cat.length; i++) {
			String x = cat[i];
			  
			if(x.equals(id))
				return true;
			
        }
		
		
		return false;
	}
	
	public void cancel() {
		
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().closeDynamic(0);
	}
	
	
	 public void showPictures(ProductsDTO product) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        options.put("contentHeight", "700");
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("product", product);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("pictures", options, null);
	 }
	 
	 
	 public void showList(ProductsDTO product) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        options.put("contentHeight", "700");
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("product", product);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("productcontent", options, null);
	 }
}
