package com.urbanslap.userservice.usereventresource.facades;

import java.util.List;

import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.dto.orderService.OrderEventEntityDto;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface UserEventResourceFacade {
	NetworkExchangeMessageWrapper<List<ServiceEntityDto>> serviceEntity();

	NetworkExchangeMessageWrapper<OrderEventEntityDto> createOrder(OrderEventEntityDto dto);
}