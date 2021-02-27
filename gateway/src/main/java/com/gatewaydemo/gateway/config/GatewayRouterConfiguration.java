/**
 * 
 */
package com.gatewaydemo.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author deepakbisht
 *
 */
@Configuration
public class GatewayRouterConfiguration {

	@Bean
	public RouteLocator returnRouterConfig(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p-> p.path("/resource/**").uri("lb://api-resource/"))
				.route(p->p.path("/user-service/**").uri("lb://user-service/"))
				.route(p->p.path("/order-service/**").uri("lb://order-service/"))
				.route(p->p.path("/event-service/**").uri("lb://event-service/"))
				.build();
	}
}
