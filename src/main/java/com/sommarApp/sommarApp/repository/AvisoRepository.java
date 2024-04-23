package com.sommarApp.sommarApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sommarApp.sommarApp.models.Aviso;

public interface AvisoRepository extends CrudRepository<Aviso, Long>{
	
	
	@Query(value = "SELECT * FROM public.aviso ORDER by data desc", nativeQuery = true)
	List<Aviso> buscarListaDeAvisosOrdenada();
	
	Aviso findById(long id);

}
