package com.serpienteemplumada.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.utils.props.properties;

import lombok.Getter;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component("PathsProps")
public class PathsProps  implements Serializable{
	private static final long serialVersionUID = 5389063585221771155L;

	private InputStream inputStream;
	
	@Getter
	private properties properties;
 
	public PathsProps() throws IOException {
		properties props = new properties();
		Properties prop = new Properties();
 
		try {
			
			String propFileName = "paths.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			
			props.setImgRoot(prop.getProperty("img.root"));
			
			props.setUrl(prop.getProperty("url"));
			
			properties = props;
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
	}
}