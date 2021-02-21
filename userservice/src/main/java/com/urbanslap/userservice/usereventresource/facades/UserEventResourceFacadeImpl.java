/**
 * 
 */
package com.urbanslap.userservice.usereventresource.facades;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
@Component
public class UserEventResourceFacadeImpl implements UserEventResourceFacade {
	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Autowired
	RestTemplate restTemplate;

	private static final String EVENT_SERVICE = "event-service";

	public NetworkExchangeMessageWrapper<List<ServiceEntityDto>> serviceEntity() {
		NetworkExchangeMessageWrapper<List<ServiceEntityDto>> msgWrapper = new NetworkExchangeMessageWrapper<List<ServiceEntityDto>>();
		final String baseUrl = initializeBaseURLs(EVENT_SERVICE);
		final String resourceUrl = "/api/" + EVENT_SERVICE + "/allAvailableServices";
		ResponseEntity<NetworkExchangeMessageWrapper> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(baseUrl + resourceUrl, NetworkExchangeMessageWrapper.class);
		} catch (Exception e) {
			msgWrapper.setStatus(0);
			msgWrapper.setMessage("Failed to get response from event-service");
			msgWrapper.setPayload(null);
		}
		if (Objects.nonNull(responseEntity.getBody())) {
			msgWrapper = responseEntity.getBody();
		}
		return msgWrapper;
	}

	private String initializeBaseURLs(String serviceName) {
		ServiceInstance instanceForEventService = loadBalancerClient.choose(serviceName);
		return instanceForEventService.getUri().toString();
	}
}
