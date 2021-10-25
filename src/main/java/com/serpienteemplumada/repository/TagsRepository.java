package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Tags;

@Repository
public interface TagsRepository extends CrudRepository<Tags, Long>{
	
	@Transactional
	@Query("SELECT t FROM Tags t ORDER BY idTags DESC")
	public List<Tags> selectAll();
	
	@Transactional
	public Optional<Tags> findById(Long id);

}
