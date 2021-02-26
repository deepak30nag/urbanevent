/**
 * 
 */
package com.urbanslap.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.dto.orderService.OrderEventEntityDto;
import com.urbanslap.userservice.entity.UserOrderEntity;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.usereventresource.facades.UserEventFacade;

/**
 * @author deepu
 *
 */
@RestController
@RequestMapping("/user-service")
public class UserEventResource {

	@Autowired
	UserEventFacade userEventFacade;

	@GetMapping("/{userId}/allAvailableEvents")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceEntityDto>>> getAllAvailabeEvents(
			@PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<List<ServiceEntityDto>> payload = userEventFacade.getAllAvailableServices();
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceEntityDto>>>(payload, HttpStatus.OK);
	}

	@PostMapping("/{userId}/requestForService")
	public ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntityDto>> placeRequestForService(
			@RequestParam("eventName") String eventName, @PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<OrderEventEntityDto> payload = userEventFacade.createOrder(eventName, userId);
		return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntityDto>>(payload, HttpStatus.OK);
	}

	@PostMapping("/{userId}/updateOrderRequest")
	public ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntityDto>> updateOrderRequestForService(
			@RequestParam("orderId") String orderId,@RequestParam(value="status",required=false)String status, @PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<OrderEventEntityDto> payload = userEventFacade.updateOrder(orderId, userId,status);
		return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntityDto>>(payload, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/allOrders")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<UserOrderEntity>>> getAllOrdersByUserId(
			@PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<List<UserOrderEntity>> payload = userEventFacade.getAllOrdersByUserId(userId);
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<UserOrderEntity>>>(payload, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/getOrderByOrderId/order/{orderId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserOrderEntity>> getOrderByOrderIdAndUserId(
			@PathVariable(value = "userId") String userId, @PathVariable(value="orderId") String orderId) {
		NetworkExchangeMessageWrapper<UserOrderEntity> payload = userEventFacade.getOrderByOrderIdAndUserId(orderId,userId);
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserOrderEntity>>(payload, HttpStatus.OK);
	}
	
}
