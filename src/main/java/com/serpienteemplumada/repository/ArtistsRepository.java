package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Artists;

@Repository
public interface ArtistsRepository extends CrudRepository<Artists, Long> {
	
	@Transactional
	@Query("SELECT a FROM Artists a ORDER BY a.idArtists DESC")
	public List<Artists> selectAll();
	
	@Transactional
	public Optional<Artists> findById(Long id);
	
	@Transactional
	@Query("SELECT a FROM Artists a where a.active=true ORDER BY a.idArtists DESC")
	public List<Artists> selectAllActive();
}
