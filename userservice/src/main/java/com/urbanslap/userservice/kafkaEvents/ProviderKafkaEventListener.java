/**
 * 
 */
package com.urbanslap.userservice.kafkaEvents;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.urbanslap.userservice.dto.orderService.OrderEventEntityDto;
import com.urbanslap.userservice.enums.OrderStatus;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.restHelper.ProxyHelperClient;
import com.urbanslap.userservice.rolesservicedao.UserRolesServiceDao;

/**
 * @author deepakbisht
 *
 */
@Service
public class ProviderKafkaEventListener {

	@Autowired
	UserRolesServiceDao userRolesService;
	
	@Autowired
	ProxyHelperClient client;
	
//	@KafkaListener(topics = "order_req_approved", groupId = "order_req_received_group_id")
	public void allApprovedOrdersToOnWait(String orderId) {
		//update the order with status with admin
		final String resourceUrl = client.getBaseUrl("order-service") + "/orders/updateOrder/{orderId}";
		final OrderEventEntityDto entityDto = new OrderEventEntityDto();
		entityDto.setOrderid(orderId);
		final String userId = userRolesService.findByRoleName("provider").getId();
		entityDto.setCurrentlyWith(userId);
		entityDto.setStatus(OrderStatus.ON_WAIT);
		Map<String,String> urlParams = new HashMap<String,String>();
		urlParams.put("orderId", orderId);
		try {
			final RequestEntity<OrderEventEntityDto> request = new RequestEntity<OrderEventEntityDto>(entityDto,HttpMethod.PUT, new URI(resourceUrl));
			ResponseEntity<NetworkExchangeMessageWrapper> response = client.restTemplate.exchange(resourceUrl,HttpMethod.PUT, request, NetworkExchangeMessageWrapper.class,urlParams);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@KafkaListener(topics = "order_req_approved", groupId = "order_req_received_group_id")
	public void allAcceptedOrdersToComplete(String orderId) {
		//update the order with status with admin
		final String resourceUrl = client.getBaseUrl("order-service") + "/orders/updateOrder/{orderId}";
		final OrderEventEntityDto entityDto = new OrderEventEntityDto();
		entityDto.setOrderid(orderId);
		final String userId = userRolesService.findByRoleName("provider").getId();
		entityDto.setCurrentlyWith(userId);
		entityDto.setStatus(OrderStatus.COMPLETE);
		Map<String,String> urlParams = new HashMap<String,String>();
		urlParams.put("orderId", orderId);
		try {
			final RequestEntity<OrderEventEntityDto> request = new RequestEntity<OrderEventEntityDto>(entityDto,HttpMethod.PUT, new URI(resourceUrl));
			ResponseEntity<NetworkExchangeMessageWrapper> response = client.restTemplate.exchange(resourceUrl,HttpMethod.PUT, request, NetworkExchangeMessageWrapper.class,urlParams);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
