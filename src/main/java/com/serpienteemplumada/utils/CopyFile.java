package com.serpienteemplumada.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component("CopyFile")
public class CopyFile implements Serializable{
	private static final long serialVersionUID = 279637073986280683L;

	private static String root; 
	 
	private static String icons;
	
	private static String logos;
	
	private static String job;
	
	private static String artist;
	
	private static String product;
	
	private static String banner;
	
	
	
	public String getFilePath(String type, String path, String filename) {
		root = path;
		
		icons = root  + "icons/original/";
		
		logos = root + "logos/original/";
		
		job = root + "job/original/";
		
		artist = root + "artist/original/";
		
		product = root + "product/original/";
		
		banner = root + "banner/original/";
		
		String route;
		
		if (type.equals("icons")) {
			route = icons + filename;
			
			return route;
		}
		
		if (type.equals("logos")) {
			route = logos + filename;
			
			return route;
		}
		
		if (type.equals("artist")) {
			route = artist + filename;
			
			return route;
		}
		
		
		if (type.equals("job")) {
			route = job + filename;
			
			return route;
		}
		
		if (type.equals("product")) {
			route = product + filename;
			
			return route;
		}
		
		if (type.equals("banner")) {
			route = banner + filename;
			
			return route;
		}
		
		return null;
	}
	
	
	public String getFilePath(String type, String path, String filename, int size) {
		root = path;
		
		String folder = "";
		
		if (size == 0) {
			folder = "original/";
		}else if(size == 1) {
			folder = "w1920/";
		}else if(size == 2) {
			folder = "w1680/";
		}else if(size == 3) {
			folder = "w1280/";
		}else if(size == 4) {
			folder = "w1024/";
		}else if(size == 5) {
			folder = "w152/";
		}else if(size == 6) {
			folder = "w80/";
		}else if(size == 8) {
			folder = "w657/";
		}else if(size == 9) {
			folder = "w327/";
		}else if(size == 10) {
			folder = "w230/";
		}
		
		icons = root  + "icons/" + folder;
		
		logos = root + "logos/" + folder;
		
		job = root + "job/" + folder;
		
		artist = root + "artist/" + folder;
		
		product = root + "product/" + folder;
		
		banner = root + "banner/" + folder;
		
		String route;
		
		if (type.equals("icons")) {
			route = icons + filename;
			
			return route;
		}
		
		if (type.equals("logos")) {
			route = logos + filename;
			
			return route;
		}
		
		
		
		if (type.equals("artist")) {
			route = artist + filename;
			
			return route;
		}
		
		if (type.equals("job")) {
			route = job + filename;
			
			return route;
		}
		
		if (type.equals("product")) {
			route = product + filename;
			
			return route;
		}
		
		if (type.equals("banner")) {
			route = banner + filename;
			
			return route;
		}
		
		return null;
	}
	
	
	
	
	public String getBasePath(String type, String path) {
		root = path;
		
		icons = root  + "icons/";
		
		logos = root + "logos/";
		
		job = root + "job/";
		
		artist = root + "artist/";
		
		product = root + "product/";
		
		banner = root + "banner/";
		
		String route;
		
		if (type.equals("icons")) {
			route = icons;
			
			return route;
		}
		
		if (type.equals("logos")) {
			route = logos;
			
			return route;
		}
		
		
		if (type.equals("artist")) {
			route = artist;
			
			return route;
		}
	
		
		if (type.equals("job")) {
			route = job;
			
			return route;
		}
		
		if (type.equals("product")) {
			route = product;
			
			return route;
		}
		
		if (type.equals("banner")) {
			route = banner;
			
			return route;
		}
		
		return null;
	}
	
	public MediaType getMediaType(String type) {
		MediaType t = null;
		if (type.contains("jpeg")) {
			t = MediaType.IMAGE_JPEG;
		}else if(type.contains("png")) {
			t = MediaType.IMAGE_PNG;
		}
		
		return t;
	}
	
	public void deleteFile(String type, String filename, String path) {
		root = path;
		
		icons = root  + "icons/";
		
		logos = root + "logos/";
		
		job = root + "job/";
		
		artist = root + "artist/";
		
		product = root + "product/";
		
		banner = root + "banner/";
		
		String route;
		
		if (type.equals("icons")) {
			route = icons + "original/" + filename;
	
			File f= new File(route);
			
			if (f.exists())
			f.delete();

		}else if(type.equals("logos")) {
			route = logos + "original/" + filename;
			
			File f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = logos + "w1920/" + filename;

			f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = logos + "w1680/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = logos + "w1280/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = logos + "w1024/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = logos + "w657/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = logos + "w327/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = logos + "w230/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = logos + "w152/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = logos + "w80/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
		}else if(type.equals("job")) {
			route = job + "original/" + filename;
			
			File f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = job + "w1920/" + filename;

			f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = job + "w1680/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = job + "w1280/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = job + "w1024/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = job + "w657/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = job + "w327/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = job + "w230/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = job + "w152/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = job + "w80/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
		}else if(type.equals("artist")) {
			route = artist + "original/" + filename;
			
			File f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = artist + "w1920/" + filename;

			f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = artist + "w1680/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = artist + "w1280/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = artist + "w657/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = artist + "w1024/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = artist + "w327/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = artist + "w230/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();		
			
			route = artist + "w152/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = artist + "w80/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
		}else if(type.equals("product")) {
			route = product + "original/" + filename;
			
			File f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = product + "w1920/" + filename;

			f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = product + "w1680/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = product + "w1280/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = product + "w1024/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = product + "w657/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = product + "w327/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = product + "w230/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = product + "w152/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = product + "w80/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
		}else if(type.equals("banner")) {
			route = banner + "original/" + filename;
			
			File f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = banner + "w1920/" + filename;

			f= new File(route);
			
			if (f.exists())
			f.delete();
			
			
			route = banner + "w1680/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = banner + "w1280/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			
			route = banner + "w1024/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = banner + "w657/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = banner + "w327/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = banner + "w230/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = banner + "w152/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
			route = banner + "w80/" + filename;
			
			
			f= new File(route);
			if (f.exists())
			f.delete();
			
		}
		
		
		
	}

	public void copyFile(String type, String fileName, InputStream in, String path) throws IOException {
		root = path;
				
		icons = root  + "icons/original/";
		
		logos = root + "logos/original/";
		
		job = root + "job/original/";
		
		artist = root + "artist/original/";
		
		product = root + "product/original/";
		
		banner = root + "banner/original/";
		
	        try {
	        	if (type.equals("icons")) {
		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream(new File(icons + fileName));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		            while ((read = in.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		            in.close();
		            out.flush();
		            out.close();
	        	}
	        	
	        	if (type.equals("logos")) {
		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream(new File(logos + fileName));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		            while ((read = in.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		            in.close();
		            out.flush();
		            out.close();
	        	}
	        	
	        	
	        	if (type.equals("job")) {
		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream(new File(job + fileName));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		            while ((read = in.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		            in.close();
		            out.flush();
		            out.close();
	        	}
	        	
	         	if (type.equals("artist")) {
		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream(new File(artist + fileName));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		            while ((read = in.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		            in.close();
		            out.flush();
		            out.close();
	        	}
	         	
	         	if (type.equals("product")) {
		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream(new File(product + fileName));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		            while ((read = in.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		            in.close();
		            out.flush();
		            out.close();
	        	}
	         	
	         	if (type.equals("banner")) {
		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream(new File(banner + fileName));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		            while ((read = in.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		            in.close();
		            out.flush();
		            out.close();
	        	}
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	
	
	public int generateResized(String fileName, String type, String path, String formatName){
		root = path;
		
		icons = root  + "icons";
		
		logos = root + "logos";
		
		job = root + "job";
		
		artist = root + "artist";
		
		product = root + "product";
		
		banner = root + "banner";
		
		
		int maxWidth = 0;
		
		String ext;
		
		if (formatName.contains("jpeg")) {
			ext = "jpg";
		}else {
			ext = "png";
		}
		
		
		
		String p = "";
		
		if (type.equals("job")) {
			p = job;
		}else if(type.equals("logos")) {
			p = logos;
		}else if(type.equals("artist")) {
			p = artist;
		}else if(type.equals("product")) {
			p = product;
		}else if(type.equals("banner")) {
			p = banner;
		}
		
		
		try {
			File img = new File(p + "/original/" + fileName);
		    BufferedImage imageIn;
			
		    imageIn = ImageIO.read(img);
		    

		    
		    if (imageIn.getWidth() >= 1920) {
		    	
		    	BufferedImage out = getBestFit(imageIn, 1920, 1080);
		    	
		    	File outputfile = new File(p + "/w1920/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	

		    	
		    	
		    	out = getBestFit(imageIn, 1680, 1050);
		    	
		    	outputfile = new File(p + "/w1680/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	
		    	out = getBestFit(imageIn, 1280, 1024);
		    	
		    	outputfile = new File(p + "/w1280/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	
		    	out = getBestFit(imageIn, 1024, 768);
		    	
		    	outputfile = new File(p + "/w1024/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 657, 427);
		    	
		    	outputfile = new File(p + "/w657/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 327, 200);
		    	
		    	outputfile = new File(p + "/w327/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 230, 220);
		    	
		    	outputfile = new File(p + "/w230/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		      	
		    	out = getBestFit(imageIn, 152, 130);
		    	
		    	outputfile = new File(p + "/w152/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 83, 71);
		    	
		    	outputfile = new File(p + "/w80/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	
		    	
		    }else if(imageIn.getWidth() > 1279 && imageIn.getWidth() < 1920 ) {
		    	BufferedImage out = getBestFit(imageIn, 1280, 1024);
		    	
		    	File outputfile = new File(p + "/w1280/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	
		    	out = getBestFit(imageIn, 1024, 768);
		    	
		    	outputfile = new File(p + "/w1024/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 657, 427);
		    	
		    	outputfile = new File(p + "/w657/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 327, 200);
		    	
		    	outputfile = new File(p + "/w327/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 230, 220);
		    	
		    	outputfile = new File(p + "/w230/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 152, 130);
		    	
		    	outputfile = new File(p + "/w152/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 83, 71);
		    	
		    	outputfile = new File(p + "/w80/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    }else if(imageIn.getWidth() > 1024 && imageIn.getWidth() < 1279 ) {
		    	
		    	BufferedImage out = getBestFit(imageIn, 1024, 768);
		    	
		    	File outputfile = new File(p + "/w1024/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 657, 427);
		    	
		    	outputfile = new File(p + "/w657/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 327, 200);
		    	
		    	outputfile = new File(p + "/w327/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 230, 220);
		    	
		    	outputfile = new File(p + "/w230/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 152, 130);
		    	
		    	outputfile = new File(p + "/w152/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 83, 71);
		    	
		    	outputfile = new File(p + "/w80/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    }else if(imageIn.getWidth() > 600 && imageIn.getWidth() < 1024 ) {
		    	
		    	BufferedImage out = getBestFit(imageIn, 657, 427);
		    	
		    	File outputfile = new File(p + "/w657/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 327, 200);
		    	
		    	outputfile = new File(p + "/w327/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 230, 220);
		    	
		    	outputfile = new File(p + "/w230/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 152, 130);
		    	
		    	outputfile = new File(p + "/w152/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 83, 71);
		    	
		    	outputfile = new File(p + "/w80/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    }else {
		    	BufferedImage out = getBestFit(imageIn, 327, 200);
		    	
		    	File outputfile = new File(p + "/w327/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	out = getBestFit(imageIn, 230, 220);
		    	
		    	outputfile = new File(p + "/w230/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		        out = getBestFit(imageIn, 152, 130);
		    	
		    	outputfile = new File(p + "/w152/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    	
		    	
		    	out = getBestFit(imageIn, 83, 71);
		    	
		    	outputfile = new File(p + "/w80/" + fileName);
		    	ImageIO.write(out, ext, outputfile);
		    }
		    
		    maxWidth = imageIn.getWidth();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
		
		
		return maxWidth;
	}
	
	
	private BufferedImage getBestFit(BufferedImage bi, int maxWidth, int maxHeight)
	  {
	if (bi == null)
		return null ;

	  	Mode mode = Mode.AUTOMATIC ;
	  	int maxSize = Math.min(maxWidth, maxHeight) ;
	  	double dh = (double)bi.getHeight() ;
	  	if (dh > Double.MIN_VALUE)
	  	{
	  		double imageAspectRatio = (double)bi.getWidth() / dh ;
	      	if (maxHeight * imageAspectRatio <=  maxWidth)
	      	{
	      		maxSize = maxHeight ;
	      		mode = Mode.FIT_TO_HEIGHT ;
	      	}
	      	else
	      	{
	      		maxSize = maxWidth ;
	      		mode = Mode.FIT_TO_WIDTH ;
	      	}	
	  	}
	  	return Scalr.resize(bi, Method.ULTRA_QUALITY, mode, maxSize, Scalr.OP_ANTIALIAS) ; 
	  }
	
	
	public int getSecondMaximun(Integer width) {
		 	if (width >= 1920) {
		 		return 1;
		    	
		    }else {
		    	return 0;
		    }
	}

}
