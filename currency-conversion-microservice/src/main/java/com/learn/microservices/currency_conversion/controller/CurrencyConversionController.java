package com.learn.microservices.currency_conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.microservices.currency_conversion.entity.CurrencyConversion;
import com.learn.microservices.currency_conversion.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity
	) {
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("currency_from", from);
		uriVariables.put("currency_to", to);
		
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{currency_from}/to/{currency_to}",
				CurrencyConversion.class,
				uriVariables
		);
		
		CurrencyConversion currencyConversion = responseEntity.getBody();
		
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " -> rest template");
		
		return currencyConversion;
		
	}
	
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity
	) {
		
		CurrencyConversion currencyConversion = proxy.getExchangeValue(from, to);
		
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " -> feign client");
		
		return currencyConversion;
		
	}

}
