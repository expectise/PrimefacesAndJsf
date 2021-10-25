package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	@Transactional
	@Query("SELECT c FROM Category c ORDER BY c.idCategory DESC")
	public List<Category> selectAll();
	
	@Transactional
	public Optional<Category> findById(Long id);
}
