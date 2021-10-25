package com.serpienteemplumada.controller;

import java.io.RandomAccessFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.serpienteemplumada.model.Artists;
import com.serpienteemplumada.model.Banners;
import com.serpienteemplumada.model.Language;
import com.serpienteemplumada.model.Pictureproduct;
import com.serpienteemplumada.repository.ArtistsRepository;
import com.serpienteemplumada.repository.BannersRepository;
import com.serpienteemplumada.repository.LanguageRepository;
import com.serpienteemplumada.repository.PictureproductRepository;
import com.serpienteemplumada.utils.CopyFile;
import com.serpienteemplumada.utils.PathsProps;


@Controller
@RequestMapping("/images/")
public class ImagesController {
	
	@Autowired
	private PathsProps pathsProps;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private ArtistsRepository artistsRepository;
	
	@Autowired
	private PictureproductRepository pictureproductRepository;
	
	@Autowired
	private BannersRepository bannersRepository;
	
	
	@Autowired
	private CopyFile copyFile;
	
	@GetMapping("/icons/{id}/{title}")
    public ResponseEntity<byte[]> getIcon(@PathVariable(value="id") Long id, @PathVariable(value="title") String title) {
        Language lang = languageRepository.findById(id).orElse(null);
    	
        if (lang != null) {
        	
        	String file = copyFile.getFilePath("icons", pathsProps.getProperties().getImgRoot(), lang.getIcon());
        	
	    	RandomAccessFile f = null;
	        try {
	            f = new RandomAccessFile(file, "r");
	            byte[] b = new byte[(int)f.length()];
	            f.readFully(b);
	            f.close();
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(copyFile.getMediaType(lang.getType()));
	            return  new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return null;
	        }
        }
        
        return null;
    }
    
    
    
    @GetMapping("/artists/{size}/{id}/{name}")
    public ResponseEntity<byte[]> getArtist(@PathVariable(value="id") Long id, @PathVariable(value="size") int size, @PathVariable(value="name") String name) {
        
    	Artists art = artistsRepository.findById(id).orElse(null);
    	
        if (art != null) {
        	
        	String file = "";
        	
        	if (size != 7) {
        		file = copyFile.getFilePath("artist", pathsProps.getProperties().getImgRoot(), art.getArtistPic(), size);
        	}else {
        		file = copyFile.getFilePath("artist", pathsProps.getProperties().getImgRoot(), art.getArtistPic(), copyFile.getSecondMaximun(art.getArtistMaxQuality()));
        	}
        	
        	
	    	RandomAccessFile f = null;
	        try {
	            f = new RandomAccessFile(file, "r");
	            byte[] b = new byte[(int)f.length()];
	            f.readFully(b);
	            f.close();
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(copyFile.getMediaType(art.getArtistPicType()));
	            return  new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return null;
	        }
        }
        
        return null;
    }
    
    
    @GetMapping("/logo/{size}/{id}/{name}")
    public ResponseEntity<byte[]> getLogo(@PathVariable(value="id") Long id, @PathVariable(value="size") int size, @PathVariable(value="name") String name) {
        
    	Artists art = artistsRepository.findById(id).orElse(null);
    	
        if (art != null) {
        	
        	String file = "";
        	
        	if (size != 7) {
        		file = copyFile.getFilePath("logos", pathsProps.getProperties().getImgRoot(), art.getLogo(), size);
        	}else {
        		file = copyFile.getFilePath("logos", pathsProps.getProperties().getImgRoot(), art.getLogo(), copyFile.getSecondMaximun(art.getLogoMaxQuality()));
        	}
        	
        	
	    	RandomAccessFile f = null;
	        try {
	            f = new RandomAccessFile(file, "r");
	            byte[] b = new byte[(int)f.length()];
	            f.readFully(b);
	            f.close();
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(copyFile.getMediaType(art.getLogoType()));
	            return  new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return null;
	        }
        }
        
        return null;
    }
    
    
    @GetMapping("/job/{size}/{id}/{name}")
    public ResponseEntity<byte[]> getJob(@PathVariable(value="id") Long id, @PathVariable(value="size") int size, @PathVariable(value="name") String name) {
        
    	Artists art = artistsRepository.findById(id).orElse(null);
    	
        if (art != null) {
        	
        	String file = "";
        	
        	if (size != 7) {
        		file = copyFile.getFilePath("job", pathsProps.getProperties().getImgRoot(), art.getWorkPic(), size);
        	}else {
        		file = copyFile.getFilePath("job", pathsProps.getProperties().getImgRoot(), art.getWorkPic(), copyFile.getSecondMaximun(art.getWorkMaxQuality()));
        	}
        	
        	
	    	RandomAccessFile f = null;
	        try {
	            f = new RandomAccessFile(file, "r");
	            byte[] b = new byte[(int)f.length()];
	            f.readFully(b);
	            f.close();
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(copyFile.getMediaType(art.getWorkPicType()));
	            return  new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return null;
	        }
        }
        
        return null;
    }
    
    
    @GetMapping("/product/{size}/{id}/{name}")
    public ResponseEntity<byte[]> getProduct(@PathVariable(value="id") Long id, @PathVariable(value="size") int size, @PathVariable(value="name") String name) {
        
    	Pictureproduct pic = pictureproductRepository.findById(id).orElse(null);
    	
        if (pic != null) {
        	
        	String file = "";
        	
        	if (size != 7) {
        		file = copyFile.getFilePath("product", pathsProps.getProperties().getImgRoot(), pic.getFile(), size);
        	}else {
        		file = copyFile.getFilePath("product", pathsProps.getProperties().getImgRoot(), pic.getFile(), copyFile.getSecondMaximun(pic.getFileMaxQuality()));
        	}
        	
        	
	    	RandomAccessFile f = null;
	        try {
	            f = new RandomAccessFile(file, "r");
	            byte[] b = new byte[(int)f.length()];
	            f.readFully(b);
	            f.close();
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(copyFile.getMediaType(pic.getFileType()));
	            return  new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return null;
	        }
        }
        
        return null;
    }
    
    @GetMapping("/banner/{size}/{id}/{part}/{name}")
    public ResponseEntity<byte[]> getBanner(@PathVariable(value="id") Long id, @PathVariable(value="size") int size, @PathVariable(value="part") int part, @PathVariable(value="name") String name) {
        
    	Banners pic = bannersRepository.findById(id).orElse(null);
    	
        if (pic != null) {
        	
        	String filedb = "";
        	Integer maxWidth = 0;
        	String filetype = "";
        	
        	if (part == 1) {
        		filedb = pic.getFile();
        		maxWidth = pic.getFileMaxQuality();
        		filetype = pic.getFileType();
        	}else if(part == 2) {
        		filedb = pic.getFile2();
        		maxWidth = pic.getFileMaxQuality2();
        		filetype = pic.getFileType2();
        	}else if(part == 3) {
        		filedb = pic.getFile3();
        		maxWidth = pic.getFileMaxQuality3();
        		filetype = pic.getFileType3();
        	}else if(part == 4) {
        		filedb = pic.getFile4();
        		maxWidth = pic.getFileMaxQuality4();
        		filetype = pic.getFileType4();
        	}
        	
        	String file = "";
        	
        	if (size != 7) {
        		file = copyFile.getFilePath("banner", pathsProps.getProperties().getImgRoot(), filedb, size);
        	}else {
        		file = copyFile.getFilePath("banner", pathsProps.getProperties().getImgRoot(), filedb, copyFile.getSecondMaximun(maxWidth));
        	}
        	
        	
	    	RandomAccessFile f = null;
	        try {
	            f = new RandomAccessFile(file, "r");
	            byte[] b = new byte[(int)f.length()];
	            f.readFully(b);
	            f.close();
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(copyFile.getMediaType(filetype));
	            return  new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return null;
	        }
        }
        
        return null;
    }

}
