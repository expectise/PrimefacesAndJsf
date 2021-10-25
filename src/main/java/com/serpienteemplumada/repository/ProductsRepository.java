package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Products;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {
	
	@Transactional
	@Query("SELECT p FROM Products p ORDER BY p.idProducts DESC")
	public List<Products> selectAll();
	
	@Transactional
	Optional<Products> findById(Long id);

}
