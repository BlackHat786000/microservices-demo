package com.learn.microservices.currency_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.microservices.currency_exchange.entity.CurrencyExchange;
import com.learn.microservices.currency_exchange.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
		
		if(currencyExchange == null) {
			throw new RuntimeException("Unable to find data from "+from+" to "+to);
		}
		
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		
		return currencyExchange;
	}

}
