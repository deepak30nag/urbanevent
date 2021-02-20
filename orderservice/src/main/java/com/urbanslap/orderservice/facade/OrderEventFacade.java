package com.urbanslap.orderservice.facade;

import java.util.List;

import com.urbanslap.orderservice.entity.OrderEventEntity;
import com.urbanslap.orderservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface OrderEventFacade {

	NetworkExchangeMessageWrapper<OrderEventEntity> updateOrderEntry(OrderEventEntity data);

	NetworkExchangeMessageWrapper<OrderEventEntity> createNewOrderEntry(OrderEventEntity data);

	NetworkExchangeMessageWrapper<OrderEventEntity> findByOrderId(String orderId);

	NetworkExchangeMessageWrapper<List<OrderEventEntity>> getAllOrderForUserId(String userId);

}
