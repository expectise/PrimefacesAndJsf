package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.serpienteemplumada.model.Pictureproduct;

public interface PictureproductRepository extends CrudRepository<Pictureproduct, Long> {
	@Transactional
	@Query("SELECT p FROM Pictureproduct p ORDER BY p.idPictureproduct DESC")
	public List<Pictureproduct> selectAll();
	
	@Transactional
	public Optional<Pictureproduct> findById(Long id);
	
	@Transactional
	public List<Pictureproduct> findByIdProducts(Long id);
	
	
	@Transactional
	@Query("SELECT p FROM Pictureproduct p WHERE p.idProducts = :id")
	public List<Pictureproduct> selectFirstPicProduct(Long id, Pageable pageable);
	
	

}
