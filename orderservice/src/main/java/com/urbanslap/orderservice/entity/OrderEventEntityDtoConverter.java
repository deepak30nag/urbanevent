/**
 * 
 */
package com.urbanslap.orderservice.entity;

import com.urbanslap.orderservice.enums.OrderStatus;

/**
 * @author deepakbisht
 *
 */
public class OrderEventEntityDtoConverter {
	static public OrderEventEntity convertFromDtoToEntity(OrderEventEntityDto orderEvent) {
		return new OrderEventEntity(orderEvent.getOrderid(),orderEvent.getEventId(), orderEvent.getOrderBy(),orderEvent.getCurrentlyWith(), OrderStatus.valueOf(orderEvent.getStatus().toUpperCase()), null, null); 
	}
}
