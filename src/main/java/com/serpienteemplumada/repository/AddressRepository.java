package com.serpienteemplumada.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serpienteemplumada.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{
	
	public Optional<Address> findById(Long id);

}
