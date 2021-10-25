package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Tagscontent;

@Repository
public interface TagscontentRepository extends CrudRepository<Tagscontent, Long> {

	@Transactional
	@Query("SELECT t FROM Tagscontent t ORDER BY idTags DESC")
	public List<Tagscontent> selectAll();
	
	@Transactional
	public Optional<Tagscontent> findById(Long id);
	
	
	@Transactional
	@Query("select DISTINCT t from Tagscontent t \r\n"
			+ "join t.language l \r\n"
			+ "where t.idTags = :id ")
	public List<Tagscontent> selectTagscontent(Long id);
}
