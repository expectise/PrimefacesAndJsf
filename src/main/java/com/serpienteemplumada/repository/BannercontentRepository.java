package com.serpienteemplumada.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.serpienteemplumada.model.Bannercontent;

public interface BannercontentRepository extends CrudRepository<Bannercontent, Long> {
	
	@Transactional
	@Query("SELECT b FROM Bannercontent b ORDER BY b.idBannercontent DESC")
	public List<Bannercontent> selectAll();
	
	@Transactional
	@Query("select distinct b from Bannercontent b \r\n"
			+ "join b.language l \r\n"
			+ "where b.idBanners = :id ")
	public List<Bannercontent> selectBannercontent(Long id);

}
