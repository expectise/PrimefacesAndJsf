package com.serpienteemplumada;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SerpienteemplumadaApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(SerpienteemplumadaApplication.class, args);
    }
    
    @Bean
	public ModelMapper modelMapper() {
		 ModelMapper modelMapper = new ModelMapper();
	        modelMapper.getConfiguration().setSkipNullEnabled(true);

	        return modelMapper;
	}

}
