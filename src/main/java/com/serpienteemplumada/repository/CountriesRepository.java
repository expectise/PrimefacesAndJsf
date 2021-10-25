package com.serpienteemplumada.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Countries;

@Repository
public interface CountriesRepository extends CrudRepository<Countries, Long>{

	@Transactional
	@Query("SELECT c FROM Countries c ORDER BY idCountries DESC")
	public List<Countries> selectAll();
	
	@Transactional
	@Query("SELECT c FROM Countries c WHERE c.active = 1 ORDER BY idCountries DESC")
	public List<Countries> selectAllActive();
	
}
