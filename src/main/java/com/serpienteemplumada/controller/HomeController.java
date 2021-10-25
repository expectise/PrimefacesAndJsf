package com.serpienteemplumada.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HomeController {

	  @RequestMapping("")
	  public RedirectView indexPre() {
	      RedirectView redirectView = new RedirectView();
	      redirectView.setUrl("/es/");
	      return redirectView;
	  }
	
	  @GetMapping("{lang}/")
	    public String index(@PathVariable(value="lang") String lang){
	        return "../index.xhtml?lang="+lang;
	    }
	  
	  @RequestMapping("admin")
	  public RedirectView admin() {
	      RedirectView redirectView = new RedirectView();
	      redirectView.setUrl("/admin/");
	      return redirectView;
	  }
	  
	  
	  @GetMapping("{lang}/product/{id}/{title}")
	    public String product(@PathVariable(value="lang") String lang, @PathVariable(value="id") Long id, @PathVariable(value="title") String title){
	        return "../../../product.xhtml?lang="+lang+"&id="+id;
	    }
	  
	  
	  
	  @GetMapping("{lang}/artists/{id}/{title}")
	    public String artists(@PathVariable(value="lang") String lang, @PathVariable(value="id") Long id, @PathVariable(value="title") String title){
	        return "../../../artists.xhtml?lang="+lang+"&id="+id;
	    }
	  
	  @GetMapping("{lang}/category/{id}/{title}")
	    public String category(@PathVariable(value="lang") String lang, @PathVariable(value="id") Long id, @PathVariable(value="title") String title){
	        return "../../../category.xhtml?lang="+lang+"&id="+id;
	    }

}
