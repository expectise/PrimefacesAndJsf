package com.serpienteemplumada.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Artistcontent;
import com.serpienteemplumada.model.Artistspage;

@Repository
public interface ArtistspageRepository extends CrudRepository<Artistspage, Long>{
	
	@Transactional
	@Query("SELECT a FROM Artistspage a ORDER BY a.idArtistspage DESC")
	public List<Artistspage> selectAll();
	
	
	@Query("select distinct a from Artistspage a \r\n"
			+ "where a.idLanguage = :id ")
	public Artistspage selectArtistpage(Long id);
	
}
