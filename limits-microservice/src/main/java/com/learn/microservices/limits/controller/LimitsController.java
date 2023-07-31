package com.learn.microservices.limits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.microservices.limits.bean.Limits;
import com.learn.microservices.limits.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits getLimits() {
//		return new Limits(1, 1000);
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
	}

}
