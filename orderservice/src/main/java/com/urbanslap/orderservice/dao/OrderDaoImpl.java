/**
 * 
 */
package com.urbanslap.orderservice.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbanslap.orderservice.entity.OrderEventEntity;
import com.urbanslap.orderservice.enums.OrderStatus;

/**
 * @author deepu
 *
 */
@Service
public class OrderDaoImpl implements OrderDao {
	
	
	static Map<String, OrderEventEntity> orderTransactions;
	static {
		orderTransactions = new HashMap<>();
		// seed with two data fields.
		orderTransactions.put("000",
				new OrderEventEntity("000", "001", "002", "001", OrderStatus.PLACED, new Date(), null));
		orderTransactions.put("001",
				new OrderEventEntity("001", "001", "002", "001", OrderStatus.PLACED, new Date(), null));
	}
	

	private boolean isOrderTransactionsEmpty() {
		return Objects.isNull(orderTransactions) || orderTransactions.size() <= 0;
	}

	public List<OrderEventEntity> getOrderForUserId(String userId) {
		if (Objects.nonNull(userId) && userId.length() > 0 && !isOrderTransactionsEmpty()) {
			return orderTransactions.values().stream().filter(eventEntity -> eventEntity.getOrderBy().equals(userId))
					.collect(Collectors.toList());
		}
		return null;
	}

	public OrderEventEntity findByOrderId(String orderId) {
		if (orderTransactions.containsKey(orderId)) {
			return orderTransactions.get(orderId);
		}
		return null;
	}

	public OrderEventEntity createNewOrderEntry(OrderEventEntity data) {
		if (Objects.isNull(data)) {
			return null;
		}
		if (orderTransactions.size() <= 9) {
			data.setOrderid("00" + (orderTransactions.size() + 1));
		} else if (orderTransactions.size() <= 99) {
			data.setOrderid("0" + (orderTransactions.size() + 1));
		} else {
			data.setOrderid(Integer.valueOf(orderTransactions.size() + 1).toString());
		}
		data.setStatus(OrderStatus.PLACED);
		data.setPlacedAt(new Date());
		data.setLastupdatedAt(new Date());
		orderTransactions.put(data.getOrderid(), data);
		//sending message to queue to Topic named "order_req_received" 
		//this would be listened further by admin.
//		sendKafkaNotification(data.getStatus(), data.getOrderid());
		return data;
	}

	public OrderEventEntity updateOrderEntry(OrderEventEntity data,String orderId) {
		if(orderTransactions.containsKey(orderId)) {
			OrderEventEntity orderData = orderTransactions.get(orderId);
			if(notNullAndNotEmpty(data.getCurrentlyWith())) {
				orderData.setCurrentlyWith(data.getCurrentlyWith());
			}
			if(Objects.nonNull(data.getStatus()) && notNullAndNotEmpty(data.getStatus().toString())) {
				if(!data.getStatus().equals(orderData.getStatus())) {
//					sendKafkaNotification(data.getStatus(),data.getOrderid());
				}
				orderData.setStatus(data.getStatus());
			}
			orderData.setLastupdatedAt(new Date());
			orderTransactions.put(orderData.getOrderid(), orderData);
			return data;
		}
		return null;
	}
	
	/*
	 * private void sendKafkaNotification(OrderStatus status, String orderId) {
	 * String TOPIC_NAME=""; switch(status) { case PLACED:
	 * TOPIC_NAME="order_req_received"; kafkaTemplate.send(TOPIC_NAME,orderId);
	 * break; case APPROVED: TOPIC_NAME="order_req_approved";
	 * kafkaTemplate.send(TOPIC_NAME,orderId); break; case ACCEPTED:
	 * TOPIC_NAME="order_req_accepted"; kafkaTemplate.send(TOPIC_NAME,orderId);
	 * break; default:break; }
	 * 
	 * }
	 */

	private boolean notNullAndNotEmpty(String value) {
		return Objects.nonNull(value) && value.length() > 0;
	}

}
