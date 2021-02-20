package com.urbanslap.orderservice.dao;

import java.util.List;

import com.urbanslap.orderservice.entity.OrderEventEntity;

public interface OrderDao {
	List<OrderEventEntity> getOrderForUserId(String userId);

	OrderEventEntity findByOrderId(String orderId);

	OrderEventEntity createNewOrderEntry(OrderEventEntity data);

	OrderEventEntity updateOrderEntry(OrderEventEntity data);
}
