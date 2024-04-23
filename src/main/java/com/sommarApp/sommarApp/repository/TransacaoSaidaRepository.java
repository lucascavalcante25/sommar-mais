package com.sommarApp.sommarApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sommarApp.sommarApp.models.TransacaoSaida;

public interface TransacaoSaidaRepository extends CrudRepository<TransacaoSaida, Long>{
	
    List<TransacaoSaida> findByDataBetween(String startDate, String endDate);

}
