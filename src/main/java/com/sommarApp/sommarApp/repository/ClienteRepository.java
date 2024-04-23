package com.sommarApp.sommarApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sommarApp.sommarApp.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	Cliente findById(long id);
	Cliente findByNome(String nome);
	
	// Query para a busca
	@Query(value = "select u from Cliente u where u.nome like %?1%")
	List<Cliente>findByNomes(String nome);
	
//	List<Cliente> findByAtivoTrue();
	
}
