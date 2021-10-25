package com.serpienteemplumada.repository;

import com.serpienteemplumada.model.Language;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long>{
	
	@Transactional
	@Query("SELECT l FROM Language l ORDER BY idLanguage DESC")
	public List<Language> selectAll();
	
	@Transactional
	public Optional<Language> findById(Long id);
	
	@Transactional
	@Query("SELECT l FROM Language l WHERE l.acrom = :acrom")
	public Language selectLanguage(String acrom);
	
}
