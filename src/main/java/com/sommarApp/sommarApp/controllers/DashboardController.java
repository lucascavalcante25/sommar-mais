package com.sommarApp.sommarApp.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sommarApp.sommarApp.models.TransacaoEntrada;
import com.sommarApp.sommarApp.models.TransacaoSaida;
import com.sommarApp.sommarApp.repository.TransacaoEntradaRepository;
import com.sommarApp.sommarApp.repository.TransacaoSaidaRepository;

@Controller
public class DashboardController {

	@Autowired
	private TransacaoEntradaRepository transacaoEntradaRepository;
	@Autowired
	private TransacaoSaidaRepository transacaoSaidaRepository;

//	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@GetMapping("/dashboard")
	public ModelAndView dashboard(Model model) {
		ModelAndView mv = new ModelAndView("dashboard/dashboard");

		// Busca todas as transações de entrada e saída e converte para lista
		Iterable<TransacaoEntrada> iterableTransacoesEntrada = transacaoEntradaRepository.findAll();
		List<TransacaoEntrada> transacoesEntrada = new ArrayList<>();
		iterableTransacoesEntrada.forEach(transacoesEntrada::add);

		Iterable<TransacaoSaida> iterableTransacoesSaida = transacaoSaidaRepository.findAll();
		List<TransacaoSaida> transacoesSaida = new ArrayList<>();
		iterableTransacoesSaida.forEach(transacoesSaida::add);

		// Consolida os dados mês a mês
		Map<String, Double> consolidadoEntrada = consolidarPorMesEntrada(transacoesEntrada);
		Map<String, Double> consolidadoSaida = consolidarPorMesSaida(transacoesSaida);
		// Calcula o saldo acumulado para cada mês
		Map<String, Double> saldoAcumulado = calcularSaldoAcumulado(consolidadoEntrada, consolidadoSaida);

		// Adiciona os dados consolidados ao modelo
		mv.addObject("consolidadoEntrada", consolidadoEntrada);
		mv.addObject("consolidadoSaida", consolidadoSaida);
		mv.addObject("saldoAcumulado", saldoAcumulado);
		
		return mv;
	}

	private Map<String, Double> consolidarPorMesEntrada(List<TransacaoEntrada> transacoes) {
		Map<String, Double> consolidado = new HashMap<>();

		for (TransacaoEntrada transacao : transacoes) {
			LocalDate data = LocalDate.parse(transacao.getData());
			String mesAno = data.format(DateTimeFormatter.ofPattern("yyyy-MM")); // Formato "yyyy-MM" para agrupar por
																					// ano e mês
			BigDecimal valor = transacao.getValor();

			// Adiciona o valor da transação ao valor consolidado do mês correspondente
			consolidado.put(mesAno, consolidado.getOrDefault(mesAno, 0.0) + valor.doubleValue());
		}

		return consolidado;
	}

	private Map<String, Double> consolidarPorMesSaida(List<TransacaoSaida> transacoes) {
		Map<String, Double> consolidado = new HashMap<>();

		for (TransacaoSaida transacao : transacoes) {
			LocalDate data = LocalDate.parse(transacao.getData());
			String mesAno = data.format(DateTimeFormatter.ofPattern("yyyy-MM")); // Formato "yyyy-MM" para agrupar por
																					// ano e mês
			BigDecimal valor = transacao.getValor();

			// Adiciona o valor da transação ao valor consolidado do mês correspondente
			consolidado.put(mesAno, consolidado.getOrDefault(mesAno, 0.0) + valor.doubleValue());
		}

		return consolidado;
	}

	private Map<String, Double> calcularSaldoAcumulado(Map<String, Double> consolidadoEntrada, Map<String, Double> consolidadoSaida) {
	    Map<String, Double> saldoAcumulado = new HashMap<>();
	    double saldo = 0.0;

	    for (String mesAno : consolidadoEntrada.keySet()) {
	        double entrada = consolidadoEntrada.getOrDefault(mesAno, 0.0);
	        double saida = consolidadoSaida.getOrDefault(mesAno, 0.0);
	        saldo += entrada - saida;
	        saldoAcumulado.put(mesAno, saldo);
	    }

	    return saldoAcumulado;
	}

}
