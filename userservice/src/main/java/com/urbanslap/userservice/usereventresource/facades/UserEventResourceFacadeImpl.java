/**
 * 
 */
package com.urbanslap.userservice.usereventresource.facades;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.dto.orderService.OrderEventEntityDto;
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
	private static final String ORDER_SERVICE = "order-service";

	@Override
	public NetworkExchangeMessageWrapper<List<ServiceEntityDto>> serviceEntity() {
		NetworkExchangeMessageWrapper<List<ServiceEntityDto>> msgWrapper = new NetworkExchangeMessageWrapper<List<ServiceEntityDto>>();
		final String baseUrl = initializeBaseURLs(EVENT_SERVICE);
		final String resourceUrl = "/api/" + EVENT_SERVICE + "/allAvailableServices";
		ResponseEntity<NetworkExchangeMessageWrapper> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(baseUrl + resourceUrl, NetworkExchangeMessageWrapper.class);
		} catch (Exception e) {
			msgWrapper.setStatus(1);
			msgWrapper.setMessage("Failed to get response from event-service");
			msgWrapper.setPayload(null);
		}
		if (Objects.nonNull(responseEntity.getBody())) {
			msgWrapper = responseEntity.getBody();
		}
		return msgWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<OrderEventEntityDto> createOrder(OrderEventEntityDto dto) {
		NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper = new NetworkExchangeMessageWrapper<OrderEventEntityDto>();
		final String baseUrl = initializeBaseURLs(ORDER_SERVICE);
		final String resourceUrl = baseUrl + "/api/" + ORDER_SERVICE + "/orders/createOrder";
		ResponseEntity<NetworkExchangeMessageWrapper> responseEntity = null;
		try {
			URI url = new URI(resourceUrl);
			final RequestEntity<OrderEventEntityDto> request = new RequestEntity<OrderEventEntityDto>(dto,
					HttpMethod.POST, url);
			responseEntity = restTemplate.postForEntity(url, request, NetworkExchangeMessageWrapper.class);
		} catch (URISyntaxException e1) {
			msgWrapper.setStatus(1);
			msgWrapper.setMessage("Failed to create url to request order-service");
			msgWrapper.setPayload(null);
		} catch (Exception e) {
			msgWrapper.setStatus(1);
			msgWrapper.setMessage("Failed to create request to order-service");
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
