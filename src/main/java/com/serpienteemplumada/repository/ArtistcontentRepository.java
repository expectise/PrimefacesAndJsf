package com.serpienteemplumada.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Artistcontent;

@Repository
public interface ArtistcontentRepository extends CrudRepository<Artistcontent, Long> {
	@Transactional
	@Query("select distinct a from Artistcontent a \r\n"
			+ "join a.language l \r\n"
			+ "where a.idArtists = :id ")
	public List<Artistcontent> selectArtistcontent(Long id);
	
	
	@Transactional
	@Query("select distinct ac from Artistcontent ac \r\n"
			+ "join ac.artists a \r\n"
			+ "where ac.idArtists = :idArtists and ac.idLanguage = :lang")
	public Artistcontent selectArtistcontentAndLanguage(Long idArtists, Long lang);
}
