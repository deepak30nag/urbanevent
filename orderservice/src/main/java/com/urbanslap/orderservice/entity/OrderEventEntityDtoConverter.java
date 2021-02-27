/**
 * 
 */
package com.urbanslap.orderservice.entity;

import java.util.Objects;

import com.urbanslap.orderservice.enums.OrderStatus;

/**
 * @author deepakbisht
 *
 */
public class OrderEventEntityDtoConverter {
	static public OrderEventEntity convertFromDtoToEntity(OrderEventEntityDto orderEvent) {
		return new OrderEventEntity(orderEvent.getOrderid(),orderEvent.getEventId(), orderEvent.getOrderBy(),orderEvent.getCurrentlyWith(), Objects.nonNull(orderEvent.getStatus()) ? OrderStatus.valueOf(orderEvent.getStatus().toUpperCase()) : null, null, null); 
	}
}
