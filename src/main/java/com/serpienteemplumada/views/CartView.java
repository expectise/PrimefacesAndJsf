package com.serpienteemplumada.views;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.model.Cart;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.repository.CartRepository;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.views.beans.CartViewBean;

@Scope("view")
@Component("cart")
public class CartView extends CartViewBean{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@PostConstruct
	public void init() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		if (req.getParameter("lang") != null) {
		String l = req.getParameter("lang").toString();
		
		Language lang = languageRepository.selectLanguage(l);
		
		
		setIdLanguage(lang.getIdLanguage());
		
		setAcrom(lang.getAcrom());
		}
		
		if (req.getParameter("id") != null) {
			String l = req.getParameter("id").toString();
			
			setId(Long.parseLong(l));
		}
		
			Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
		    Cookie cookie = (Cookie) cookies.get("cart");
	   if(cookie != null){
		    	setIdCart(Long.parseLong(cookie.getValue()));
	    	
	    }else {
	    	
	    		 Cart ca = new Cart();
	    		 ca.setActive(true);
	    		 ca.setCreated(new Date());
	    		 
	    		 Cart can = cartRepository.save(ca);
	    	
	    	 	 FacesContext facesContext = FacesContext.getCurrentInstance();

		    	 
		    	 cookie = new Cookie("cart", can.getIdCart().toString());
		    	 cookie.setMaxAge(9890000);
		    	 
		    	 HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		    	 response.addCookie(cookie);
		    	 
		    	 setIdCart(can.getIdCart());
	    }

	   runner();
	}
}
