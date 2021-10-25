package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Pictureproductcontent;

@Repository
public interface PictureproductcontentRepository extends CrudRepository<Pictureproductcontent, Long> {

	@Transactional
	@Query("SELECT p FROM Productcontent p ORDER BY p.idProductcontent DESC")
	public List<Pictureproductcontent> selectAll();
	
	@Transactional
	Optional<Pictureproductcontent> findById(Long id);
	
	@Transactional
	@Query("select distinct p from Pictureproductcontent p \r\n"
			+ "join p.language l \r\n"
			+ "where p.idPictureproduct = :id ")
	public List<Pictureproductcontent> selectPictureproductcontent(Long id);
	
	
	@Transactional
	public List<Pictureproductcontent> findByIdPictureproduct(Long id);
	
	@Transactional
	public Optional<Pictureproductcontent> findByIdPictureproductAndIdLanguage(Long product, Long lang);
}
