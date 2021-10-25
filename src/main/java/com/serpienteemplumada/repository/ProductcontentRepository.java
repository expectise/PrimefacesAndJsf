package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Productcontent;

@Repository
public interface ProductcontentRepository extends CrudRepository<Productcontent, Long> {
	@Transactional
	@Query("SELECT p FROM Productcontent p ORDER BY p.idProductcontent DESC")
	public List<Productcontent> selectAll();
	
	@Transactional
	Optional<Productcontent> findById(Long id);
	
	@Transactional
	@Query("select distinct p from Productcontent p \r\n"
			+ "join p.language l \r\n"
			+ "where p.idProducts = :id ")
	public List<Productcontent> selectProductcontent(Long id);
	
	
	@Transactional
	@Query("select distinct p from Productcontent p \r\n"
			+ "where p.idProducts = :idProducts and idLanguage = :idLanguage")
	public Productcontent selectProductlang(Long idProducts, Long idLanguage);

}
