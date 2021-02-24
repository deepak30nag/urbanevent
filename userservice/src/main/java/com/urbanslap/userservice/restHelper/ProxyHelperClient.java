/**
 * 
 */
package com.urbanslap.userservice.restHelper;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author deepakbisht
 *
 */
@Component
public final class ProxyHelperClient {
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	public RestTemplate restTemplate;

	public final String getBaseUrl(String serviceName)
	{
		final ServiceInstance instance = loadBalancerClient.choose(serviceName);
		if(Objects.nonNull(instance)) {
			return instance.getUri().toString();
		}
		return "";
	}
		
}
