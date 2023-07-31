package com.learn.microservices.currency_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.microservices.currency_exchange.entity.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	
	public CurrencyExchange findByFromAndTo(String from, String to);

}
