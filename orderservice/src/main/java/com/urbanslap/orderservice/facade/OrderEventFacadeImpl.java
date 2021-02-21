/**
 * 
 */
package com.urbanslap.orderservice.facade;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbanslap.orderservice.dao.OrderDao;
import com.urbanslap.orderservice.entity.OrderEventEntity;
import com.urbanslap.orderservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
@Component
public class OrderEventFacadeImpl implements OrderEventFacade {

	@Autowired
	OrderDao orderDao;

	@Override
	public NetworkExchangeMessageWrapper<List<OrderEventEntity>> getAllOrderForUserId(String userId) {
		NetworkExchangeMessageWrapper<List<OrderEventEntity>> messageWrapper = new NetworkExchangeMessageWrapper<List<OrderEventEntity>>();
		if (Objects.isNull(userId) || userId.length() == 0) {
			messageWrapper.setMessage("Please provide value for userid");
			messageWrapper.setStatus(1);
			messageWrapper.setPayload(null);
			return messageWrapper;
		}
		List<OrderEventEntity> orderEntity = orderDao.getOrderForUserId(userId);
		if (Objects.nonNull(orderEntity) && !orderEntity.isEmpty()) {
			messageWrapper.setMessage("Orders for userId: " + userId);
			messageWrapper.setStatus(0);
			messageWrapper.setPayload(orderEntity);
			return messageWrapper;
		}
		messageWrapper.setMessage("No Orders for user");
		messageWrapper.setStatus(1);
		messageWrapper.setPayload(null);
		return messageWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<OrderEventEntity> findByOrderId(String orderId) {
		NetworkExchangeMessageWrapper<OrderEventEntity> messageWrapper = new NetworkExchangeMessageWrapper<OrderEventEntity>();
		if (Objects.isNull(orderId) || orderId.length() == 0) {
			messageWrapper.setMessage("Please provide value for orderId");
			messageWrapper.setStatus(1);
			messageWrapper.setPayload(null);
			return messageWrapper;
		}
		final OrderEventEntity orderEntity = orderDao.findByOrderId(orderId);
		if (Objects.nonNull(orderEntity)) {
			messageWrapper.setMessage("Orders for userId: " + orderId);
			messageWrapper.setStatus(0);
			messageWrapper.setPayload(orderEntity);
			return messageWrapper;
		}
		messageWrapper.setMessage("No Orders for user");
		messageWrapper.setStatus(1);
		messageWrapper.setPayload(null);
		return messageWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<OrderEventEntity> createNewOrderEntry(OrderEventEntity data) {
		NetworkExchangeMessageWrapper<OrderEventEntity> messageWrapper = new NetworkExchangeMessageWrapper<OrderEventEntity>();
		if (Objects.isNull(data)) {
			messageWrapper.setMessage("Please provide values for order event correctly");
			messageWrapper.setStatus(3);
			messageWrapper.setPayload(null);
			return messageWrapper;
		}
		final OrderEventEntity orderEntity = orderDao.createNewOrderEntry(data);
		if (Objects.nonNull(orderEntity)) {
			messageWrapper.setMessage("Order created: ");
			messageWrapper.setStatus(2);
			messageWrapper.setPayload(orderEntity);
			return messageWrapper;
		}
		messageWrapper.setMessage("Cannot Create");
		messageWrapper.setStatus(4);
		messageWrapper.setPayload(null);
		return messageWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<OrderEventEntity> updateOrderEntry(OrderEventEntity data, String orderId) {
		NetworkExchangeMessageWrapper<OrderEventEntity> messageWrapper = new NetworkExchangeMessageWrapper<OrderEventEntity>();
		if (Objects.isNull(data)) {
			messageWrapper.setMessage("Please provide values for order event correctly");
			messageWrapper.setStatus(3);
			messageWrapper.setPayload(null);
			return messageWrapper;
		}
		final OrderEventEntity orderEntity = orderDao.updateOrderEntry(data, orderId);
		if (Objects.nonNull(orderEntity)) {
			messageWrapper.setMessage("Order created: ");
			messageWrapper.setStatus(2);
			messageWrapper.setPayload(orderEntity);
			return messageWrapper;
		}
		messageWrapper.setMessage("Cannot Create");
		messageWrapper.setStatus(4);
		messageWrapper.setPayload(null);
		return messageWrapper;
	}
}
