package com.serpienteemplumada.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Cartcontent;

@Repository
public interface CartcontentRepository extends CrudRepository<Cartcontent, Long>{
	
	@Transactional
	public Optional<Cartcontent> findById(Long id);
	
	@Transactional
	@Query("select distinct cc from Cartcontent cc \r\n"
			+ "join cc.cart c \r\n"
			+ "where cc.idCart = :id ")
	public List<Cartcontent> selectCartcontent(Long id);
	
	@Transactional
	@Query("select distinct cc from Cartcontent cc \r\n"
			+ "join cc.cart c \r\n"
			+ "where cc.idCart = :cart and cc.idProducts = :product")
	public Cartcontent selectCartcontentProduct(Long cart, Long product);

}
