package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.serpienteemplumada.model.Countrycontent;

public interface CountrycontentRepository extends CrudRepository<Countrycontent, Long> {

	@Transactional
	@Query("select distinct c from Countrycontent c \r\n"
			+ "join c.language l \r\n"
			+ "where c.idCountries = :id ")
	public List<Countrycontent> selectCountrycontent(Long id);
	
	@Transactional
	public Optional<Countrycontent> findById(Long id);
	
}
