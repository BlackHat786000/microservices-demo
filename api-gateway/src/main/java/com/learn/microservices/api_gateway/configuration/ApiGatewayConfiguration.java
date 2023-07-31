package com.learn.microservices.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyHeaderValue").addRequestParameter("MyParameter",
								"MyParameterValue"))
						.uri("http://httpbin.org:80"))
				// if a request starts with "/currency exchange", talk to eureka and find the location of this service and load balance between the instances returned.
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				// 
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				// http://localhost:8765/currency-conversion-feign/from/EUR/to/INR/quantity/100
				// Internally only (http://localhost:8765) is replaced by server:port of currency-conversion with help of eureka as it is registered on it.
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
	}

}
