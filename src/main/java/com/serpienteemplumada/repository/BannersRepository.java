package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.serpienteemplumada.model.Banners;

public interface BannersRepository extends CrudRepository<Banners, Long> {
	
	@Transactional
	@Query("SELECT b FROM Banners b ORDER BY b.idBanners DESC")
	public List<Banners> selectAll();
	
	@Transactional
	Optional<Banners> findById(Long Id);

}
