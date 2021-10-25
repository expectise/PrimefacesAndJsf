package com.serpienteemplumada.views.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.dto.ProductcontentDTO;
import com.serpienteemplumada.dto.ProductsDTO;
import com.serpienteemplumada.model.Productcontent;
import com.serpienteemplumada.repository.ProductcontentRepository;

import lombok.Data;

@Data
public abstract class ProductcontentViewBean {
	
	@Autowired
	private ProductcontentRepository productcontentRepository;
	
	private ProductsDTO productsDTO;
	
	private ProductcontentDTO productcontentDTO;
	
	private List<ProductcontentDTO> productcontentDTOs;
	
	private Boolean visible = false;
	
	protected void runner() {
		List<Productcontent> list = productcontentRepository.selectProductcontent(productsDTO.getIdProducts());
		
		List<ProductcontentDTO> listOut = new ArrayList<>();
		
		for (Productcontent prodc : list) {
			ProductcontentDTO nprodc = new ProductcontentDTO();
			
			nprodc.setContent(prodc.getContent());
			nprodc.setIdProductcontent(prodc.getIdProductcontent());
			nprodc.setLanguage(prodc.getLanguage().getLanguage());
			nprodc.setTitle(prodc.getTitle());
			nprodc.setMaterials(prodc.getMaterials());
			
			listOut.add(nprodc);
		}
		
		productcontentDTOs = listOut;
	}
	
	public void submit() {
		Productcontent prod = productcontentRepository.findById(productcontentDTO.getIdProductcontent()).orElse(null);
		
		if (prod != null) {
			prod.setUpdated(new Date());
			prod.setTitle(productcontentDTO.getTitle());
			prod.setContent(productcontentDTO.getContent());
			prod.setMaterials(productcontentDTO.getMaterials());
			
			productcontentRepository.save(prod);
		}
		runner();
		visible = false;
	}
	
	public void cancel() {
		visible = false;
	}
	
	public void onSelected(ProductcontentDTO prod) {
		productcontentDTO = prod;
		visible = true;
	}

}
