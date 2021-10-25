package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.serpienteemplumada.dto.ProductsDTO;
import com.serpienteemplumada.views.beans.PictureproductViewBean;

@Scope("view")
@Component("pictureproduct")
public class PictureproductView extends PictureproductViewBean{
	
	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		ProductsDTO input = (ProductsDTO) sessionMap.get("product");
		
		if (input != null) {
			setProductsDTO(input);
		
			runner();
		
			sessionMap.remove("product");
		}
	
	}

}
