package com.learn.microservices.currency_exchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
//	@Retry(name="sample-api", fallbackMethod="hardcodedResponse")
	
/*	Initially circuit breaker is in closed state and executes the requests to dependent service but when there are very 
 * 	large volume of continuous failed requests, circuit breaker enters into open state and start short-circuiting the 
 * 	requests i.e return fallback response without even executing the request. This continues for some duration after 
 * 	which circuit breaker enters into half-open state and starts sending some percentage of requests to dependent service
 * 	to check whether it is up or not. If the dependent service is up, circuit breaker enters into closed state otherwise 
 * 	enters into open state. This process flow keeps on going.... */
	
//	@CircuitBreaker(name="default", fallbackMethod="hardcodedResponse")	// default configuration
	
	@RateLimiter(name="default")
	
	@Bulkhead(name="sample-api")
	@GetMapping("/sample-api")
	public String sampleApi() {
		logger.info("Call to sample api received");
		
//		ResponseEntity<String> responseEntity = 
//				new RestTemplate().getForEntity("http://localhost:8080/some-dummy-api", String.class);
		
		return "Sample API";
	}
	
	// we can have different fallback methods for different exceptions thrown by @Retry/@CircuitBreaker method
	public String hardcodedResponse(Exception ex) {
		return "Hardcoded Response";
	}

}
