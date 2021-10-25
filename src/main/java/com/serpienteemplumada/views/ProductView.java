package com.serpienteemplumada.views;


import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.ProductsDTO;
import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Asigncategory;
import com.serpienteemplumada.model.Category;
import com.serpienteemplumada.model.Products;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.repository.AsigncategoryRepository;
import com.serpienteemplumada.repository.CategoryRepository;
import com.serpienteemplumada.views.beans.ProductViewBean;

@Scope("view")
@Component("product")
public class ProductView extends ProductViewBean{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ArtistsRepository artistsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AsigncategoryRepository asigncategoryRepository;
	
	@PostConstruct
	public void init(){
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Products input = (Products) sessionMap.get("product");
		
		List<Artists> artists = artistsRepository.selectAll();
		
		setArtists(artists);
		
		List<Category> categories = categoryRepository.selectAll();
		
		
		

		setCategories(categories);
		
		if (input != null) {
			
			ProductsDTO productsDTO = modelMapper.map(input, ProductsDTO.class);
			
			List<Asigncategory> asign = asigncategoryRepository.selectAsignbyproduct(productsDTO.getIdProducts());
			
			
			String[] cat = new String[asign.size()];
			
			int i = 0;
			for (Asigncategory a : asign) {
				cat[i] = a.getIdCategory().toString();
				
				i++;
			}
			
			productsDTO.setCategories(cat);
			productsDTO.setPrecategories(cat);
			
			setProductsDTO(productsDTO);
			
			sessionMap.remove("product");
		}else {
			
			setProductsDTO(new ProductsDTO());
		}
	}
	

}
