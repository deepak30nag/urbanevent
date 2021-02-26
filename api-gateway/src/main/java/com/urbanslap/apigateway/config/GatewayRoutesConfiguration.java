/**
 * 
 */
package com.urbanslap.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author deepu
 *
 */
@Configuration
public class GatewayRoutesConfiguration {

	@Bean
	public RouteLocator getGatewayLocator(RouteLocatorBuilder builder) {
		return builder.routes()
		.route(p -> p.path("/user-service/**").uri("lb://user-service")).build();
//				.route(p -> p.path("/order-service/**").uri("lb://order-service"))
//				.route(p -> p.path("/event-service/**").uri("lb://event-service")).build();
	}

}
