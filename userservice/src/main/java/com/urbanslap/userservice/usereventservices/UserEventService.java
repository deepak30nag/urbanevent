package com.urbanslap.userservice.usereventservices;

import java.util.List;

import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.dto.orderService.OrderEventEntityDto;
import com.urbanslap.userservice.entity.UserOrderEntity;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface UserEventService {

	NetworkExchangeMessageWrapper<List<ServiceEntityDto>> getAllAvailableServices();

	NetworkExchangeMessageWrapper<UserOrderEntity> createOrder(String eventName, String userId);

	NetworkExchangeMessageWrapper<UserOrderEntity> updateOrder(String orderId, String userId, String status);

	NetworkExchangeMessageWrapper<UserOrderEntity> getOrderByOrderIdAndUserId(String orderId, String userId);

}