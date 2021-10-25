package com.serpienteemplumada.views.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Products;
import com.serpienteemplumada.repository.ProductsRepository;

import lombok.Data;

@Data
public abstract class ProductsViewBean {
	
	@Autowired
	private ProductsRepository productsRepository;
	
	private List<Products> products;
	
	
	public void showAddPopup() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("positionType", "absolute");
        options.put("styleClass", "dialogwindow");
        
        
		PrimeFaces instance = PrimeFaces.current();
		instance.dialog().openDynamic("addproduct", options, null);
		
	}
	
	
	 public void onCloseDialog(SelectEvent selectEvent) {
	      int out = (int) selectEvent.getObject();
	      if (out == 1) {
	    	  products = productsRepository.selectAll();
	      }else if(out == 2) {
	    	  products = productsRepository.selectAll();
	      }else {
	    	  products = productsRepository.selectAll();
	      }
	  }
	 
	 
	 public void onSelected(Products product) {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("positionType", "absolute");
	        options.put("styleClass", "dialogwindow");
	        
	        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	        sessionMap.put("product", product);
	        
			PrimeFaces instance = PrimeFaces.current();
			instance.dialog().openDynamic("updateproduct", options, null);
	 }
	 
	 public void onActivate(Products product)
	 {
		    Products prod = productsRepository.findById(product.getIdProducts()).orElse(null);
		    

		 if (prod != null) {
			 boolean active = prod.getActive();
			 
			 prod.setActive(!active);
			 
			 productsRepository.save(prod);
			 
			 products = productsRepository.selectAll();
		 }
	 }

}
