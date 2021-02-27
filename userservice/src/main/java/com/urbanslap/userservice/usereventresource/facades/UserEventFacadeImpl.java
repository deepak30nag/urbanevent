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
import com.urbanslap.userservice.entity.UserOrderEntity;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.restHelper.ProxyHelperClient;
import com.urbanslap.userservice.usereventservices.UserEventService;

/**
 * @author deepu
 *
 */
@Component
public class UserEventFacadeImpl implements UserEventFacade {
	
	@Autowired
	UserEventService userEventService;

	@Override
	public NetworkExchangeMessageWrapper<List<ServiceEntityDto>> getAllAvailableServices() {
		NetworkExchangeMessageWrapper<List<ServiceEntityDto>> msgWrapper = new NetworkExchangeMessageWrapper<List<ServiceEntityDto>>();
		msgWrapper = userEventService.getAllAvailableServices();
		return msgWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<UserOrderEntity> createOrder(String eventName, String userId) {
		NetworkExchangeMessageWrapper<UserOrderEntity> msgWrapper = userEventService.createOrder(eventName, userId);		
		return msgWrapper;
	}
	
	@Override
	public NetworkExchangeMessageWrapper<UserOrderEntity> updateOrder(String orderId, String userId, String status) {
		NetworkExchangeMessageWrapper<UserOrderEntity> msgWrapper = userEventService.updateOrder(orderId, userId, status);		
		return msgWrapper;
	}
	
	@Override
	public NetworkExchangeMessageWrapper<List<UserOrderEntity>> getAllOrdersByUserId(String userId) {
		NetworkExchangeMessageWrapper<List<UserOrderEntity>> msgWrapper = null;		
		return msgWrapper;
	}
	
	@Override
	public NetworkExchangeMessageWrapper<UserOrderEntity> getOrderByOrderIdAndUserId(String orderId, String userId) {
		NetworkExchangeMessageWrapper<UserOrderEntity> msgWrapper = userEventService.getOrderByOrderIdAndUserId(orderId, userId);		
		return msgWrapper;
	}
}
