package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.serpienteemplumada.model.Asigncategory;

public interface AsigncategoryRepository extends CrudRepository<Asigncategory, Long> {
	@Transactional
	@Query("SELECT a FROM Asigncategory a ORDER BY a.idAsigncategory DESC")
	public List<Asigncategory> selectAll();
	
	@Transactional
	Optional<Asigncategory> findById(Long id);
	
	
	@Transactional
	@Query("select a from Asigncategory a \r\n"
			+ "where a.idProducts = :id ")
	public List<Asigncategory> selectAsignbyproduct(Long id);
	
	@Transactional
	@Query("select a from Asigncategory a \r\n"
			+ "where a.idProducts = :product and a.idCategory = :category")
	public Asigncategory selectIdbyCategoryAndProduct(Long category, Long product);
	
	@Transactional
	@Query("select distinct a from Asigncategory a \r\n"
			+ "join a.products p \r\n"
			+ "where a.idCategory = :id")
	public List<Asigncategory> selectCategoryproduct(Long id, Pageable pageable);

}
