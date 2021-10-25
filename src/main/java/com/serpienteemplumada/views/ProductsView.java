package com.serpienteemplumada.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Products;
import com.serpienteemplumada.repository.ProductsRepository;
import com.serpienteemplumada.views.beans.ProductsViewBean;

@Scope("view")
@Component("products")
public class ProductsView extends ProductsViewBean{
	
	@Autowired
	private ProductsRepository productsRepository;
	

	@PostConstruct
	public void init() {
		List<Products> list = productsRepository.selectAll();
		
		setProducts(list);
		
	}
}
