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
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.orderservice.entity.OrderEventEntity;
import com.urbanslap.orderservice.facade.OrderEventFacade;
import com.urbanslap.orderservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
@RestController
public class OrderEventResource {

	@Autowired
	OrderEventFacade orderEventFacade;

	@GetMapping("/api/orders/findForUser/user/{userId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<OrderEventEntity>>> getOrdersForUserId(
			@PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<List<OrderEventEntity>> entity = orderEventFacade.getAllOrderForUserId(userId);
		if (Objects.nonNull(entity.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<List<OrderEventEntity>>>(entity, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<OrderEventEntity>>>(entity,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
