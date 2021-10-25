package com.serpienteemplumada.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
    @GetMapping
    public String index(){
        return "index.xhtml";
    }
    
    @GetMapping("language")
    public String language(){
        return "language/index.xhtml";
    }
    
    @GetMapping("home")
    public String home(){
        return "home/index.xhtml";
    }
    
    @GetMapping("countries")
    public String countries(){
        return "countries/index.xhtml";
    }

    @GetMapping("category")
    public String category(){
        return "category/index.xhtml";
    }
    
    
    @GetMapping("tags")
    public String tags(){
        return "tags/index.xhtml";
    }
    
    
    @GetMapping("artists")
    public String artists(){
        return "artists/index.xhtml";
    }
    
    @GetMapping("products")
    public String products(){
        return "products/index.xhtml";
    }
    
    @GetMapping("banners")
    public String banners(){
        return "banners/index.xhtml";
    }

}
