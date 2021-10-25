package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Categorycontent;

@Repository
public interface CategorycontentRepository extends CrudRepository<Categorycontent, Long>{
	@Transactional
	@Query("select distinct c from Categorycontent c \r\n"
			+ "join c.language l \r\n"
			+ "where c.idCategory = :id ")
	public List<Categorycontent> selectCategorycontent(Long id);
	
	@Transactional
	Optional<Categorycontent> findByIdCategoryAndIdLanguage(Long cat, Long lang);
	
	@Transactional
	@Query("select distinct cc from Categorycontent cc \r\n"
			+ "join cc.category c \r\n"
			+ "where cc.idLanguage = :id ")
	public List<Categorycontent> selectCategorycontentByLang(Long id);
}
