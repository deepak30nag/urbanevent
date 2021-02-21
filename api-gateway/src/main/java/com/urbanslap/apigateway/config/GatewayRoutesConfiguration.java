/**
 * 
 */
package com.urbanslap.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author deepu
 *
 */
@Component
public class GatewayRoutesConfiguration {
	
	@Bean
	public RouteLocator getGatewayLocator(RouteLocatorBuilder builder)
	{
		return builder.routes().build();
	}
}
