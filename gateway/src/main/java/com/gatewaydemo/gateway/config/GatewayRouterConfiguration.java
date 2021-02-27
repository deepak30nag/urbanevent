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
		return builder.routes().route(p-> p.path("/resource/**").uri("lb://api-resource/")).build();
	}
}
