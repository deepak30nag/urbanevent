/**
 * 
 */
package com.urbanslap.orderservice.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.orderservice.entity.OrderEventEntity;
import com.urbanslap.orderservice.facade.OrderEventFacade;
import com.urbanslap.orderservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
@RestController
@RequestMapping("/api/order-service")
public class OrderEventResource {

	@Autowired
	OrderEventFacade orderEventFacade;

	@GetMapping("/orders/findForUser/user/{userId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<OrderEventEntity>>> getOrdersForUserId(
			@PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<List<OrderEventEntity>> entity = orderEventFacade.getAllOrderForUserId(userId);
		if (Objects.nonNull(entity.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<List<OrderEventEntity>>>(entity, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<OrderEventEntity>>>(entity,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/orders/findByOrderId/order/{orderId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>> getOrdersByOrderId(
			@PathVariable(value = "orderId") String orderId) {
		NetworkExchangeMessageWrapper<OrderEventEntity> entity = orderEventFacade.findByOrderId(orderId);
		if (Objects.nonNull(entity.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>>(entity, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>>(entity,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/orders/createOrder")
	public ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>> createOrder(
			@RequestBody OrderEventEntity orderEvent) {
		NetworkExchangeMessageWrapper<OrderEventEntity> entity = orderEventFacade.createNewOrderEntry(orderEvent);
		if (Objects.nonNull(entity.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>>(entity, HttpStatus.CREATED);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>>(entity,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/orders/updateOrder/{orderId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>> updateOrder(
			@RequestBody OrderEventEntity orderEvent, @PathVariable(value = "orderId") String orderId) {
		NetworkExchangeMessageWrapper<OrderEventEntity> entity = orderEventFacade.updateOrderEntry(orderEvent, orderId);
		if (Objects.nonNull(entity.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>>(entity, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<OrderEventEntity>>(entity,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
