package com.urbanslap.userservice.usereventresource.facades;

import java.util.List;

import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.dto.orderService.OrderEventEntityDto;
import com.urbanslap.userservice.entity.UserOrderEntity;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface UserEventFacade {

	NetworkExchangeMessageWrapper<UserOrderEntity> createOrder(String eventName, String userId);

	NetworkExchangeMessageWrapper<List<ServiceEntityDto>> getAllAvailableServices();

	NetworkExchangeMessageWrapper<UserOrderEntity> updateOrder(String orderId, String userId, String status);

	NetworkExchangeMessageWrapper<UserOrderEntity> getOrderByOrderIdAndUserId(String orderId, String userId);

	NetworkExchangeMessageWrapper<List<UserOrderEntity>> getAllOrdersByUserId(String userId);
}