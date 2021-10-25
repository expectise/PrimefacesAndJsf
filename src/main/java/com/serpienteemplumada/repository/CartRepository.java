package com.serpienteemplumada.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{

	@Transactional
	public Optional<Cart> findById(Long id);

}
