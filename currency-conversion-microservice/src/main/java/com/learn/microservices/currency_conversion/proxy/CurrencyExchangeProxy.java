package com.learn.microservices.currency_conversion.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn.microservices.currency_conversion.entity.CurrencyConversion;

// name value should be service application name that you want to call
//@FeignClient(name="currency-exchange", url="http://localhost:8000")
/* 
 	Feign will talk to eureka naming server and pickup all the available instances of currency-exchange service and
 	do automatic load balancing between them
*/
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getExchangeValue(@PathVariable String from, @PathVariable String to);

}
