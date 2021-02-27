/**
 * 
 */
package com.urbanslap.userservice.usereventservices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.dto.eventservice.ServiceProviderEntityDto;
import com.urbanslap.userservice.dto.orderService.OrderEventEntityDto;
import com.urbanslap.userservice.entity.UserEntity;
import com.urbanslap.userservice.entity.UserLocationEntity;
import com.urbanslap.userservice.entity.UserOrderEntity;
import com.urbanslap.userservice.entity.UserRoles;
import com.urbanslap.userservice.enums.OrderStatus;
import com.urbanslap.userservice.locationservicedao.UserLocationServiceDao;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.restHelper.ProxyHelperClient;
import com.urbanslap.userservice.rolesservicedao.UserRolesServiceDao;
import com.urbanslap.userservice.servicedao.UserDaoService;

/**
 * @author deepakbisht
 *
 */
@Service
public class UserEventServiceImpl implements UserEventService {

	@Autowired
	UserRolesServiceDao userRolesServiceDao;
	
	@Autowired
	UserDaoService userDaoService;
	
	@Autowired
	UserLocationServiceDao userLocationDao;
	
	@Autowired
	ProxyHelperClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	private static final String EVENT_SERVICE = "event-service";
	private static final String ORDER_SERVICE = "order-service";

	@Override
	public NetworkExchangeMessageWrapper<List<ServiceEntityDto>> getAllAvailableServices() {
		NetworkExchangeMessageWrapper<List<ServiceEntityDto>> msgWrapper = new NetworkExchangeMessageWrapper<List<ServiceEntityDto>>();
		final String baseUrl = client.getBaseUrl(EVENT_SERVICE);
		final String resourceUrl = "/" + EVENT_SERVICE + "/allAvailableServices";
		ResponseEntity<NetworkExchangeMessageWrapper> responseEntity = null;
		try {
			responseEntity = client.restTemplate.getForEntity(baseUrl + resourceUrl,
					NetworkExchangeMessageWrapper.class);
		} catch (Exception e) {
			msgWrapper.setStatus(1);
			msgWrapper.setMessage("Failed to get response from event-service");
			msgWrapper.setPayload(null);
		}
		if (Objects.nonNull(responseEntity) && Objects.nonNull(responseEntity.getBody())) {
			msgWrapper = responseEntity.getBody();
		}
		return msgWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<UserOrderEntity> createOrder(String eventName, String userId) {
		NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper = new NetworkExchangeMessageWrapper<OrderEventEntityDto>();
		NetworkExchangeMessageWrapper<UserOrderEntity> msgWrapperUserOrderEntity = new NetworkExchangeMessageWrapper<UserOrderEntity>();
//		List<ServiceEntityDto> availableServices = getAllAvailableServices().getPayload();
		List<ServiceEntityDto> availableServices = mapper.convertValue(getAllAvailableServices().getPayload(), new TypeReference<List<ServiceEntityDto>>() {});
		
		ServiceEntityDto entryOrdered = null;
		if (Objects.nonNull(availableServices) && availableServices.size() > 0) {
			Optional<ServiceEntityDto> optionalService = availableServices.stream()
					.filter(service -> service.getServiceName().equals(eventName)).findAny();
			if (optionalService.isPresent()) {
				entryOrdered = optionalService.get();
			}
		}
		if (Objects.isNull(entryOrdered)) {
			msgWrapperUserOrderEntity.setStatus(1);
			msgWrapperUserOrderEntity.setMessage("No such service is available.");
			return msgWrapperUserOrderEntity;
		}
		OrderEventEntityDto entityDto = new OrderEventEntityDto("", entryOrdered.getServiceId(), userId, "", null, null,
				null);
		final String baseUrl = client.getBaseUrl(ORDER_SERVICE);
		final String resourceUrl = baseUrl + "/" + ORDER_SERVICE + "/orders/createOrder";
		ResponseEntity<NetworkExchangeMessageWrapper> responseEntity = null;
		try {
			URI url = new URI(resourceUrl);
			final RequestEntity<OrderEventEntityDto> request = new RequestEntity<OrderEventEntityDto>(entityDto,
					HttpMethod.POST, url);
			responseEntity = client.restTemplate.postForEntity(url, request, NetworkExchangeMessageWrapper.class);
		} catch (URISyntaxException e1) {
			msgWrapperUserOrderEntity.setStatus(1);
			msgWrapperUserOrderEntity.setMessage(e1.getMessage());
			msgWrapperUserOrderEntity.setPayload(null);
		} catch (Exception e) {
			msgWrapperUserOrderEntity.setStatus(1);
			msgWrapperUserOrderEntity.setMessage("Failed to create request to order-service");
			msgWrapperUserOrderEntity.setPayload(null);
		}
		if (Objects.nonNull(responseEntity) && Objects.nonNull(responseEntity.getBody()) && Objects.nonNull(responseEntity.getBody().getPayload())) {
			entityDto = mapper.convertValue(responseEntity.getBody().getPayload(), OrderEventEntityDto.class);
		}
		msgWrapperUserOrderEntity = getUserOrderEntityForOrderEventEntity(entityDto, entityDto.getOrderid()); 
		return msgWrapperUserOrderEntity;
	}
	
	@Override
	public NetworkExchangeMessageWrapper<UserOrderEntity> getOrderByOrderIdAndUserId(String orderId, String userId) {
		NetworkExchangeMessageWrapper<UserOrderEntity> orderDetailsMsgWrapper= new NetworkExchangeMessageWrapper<UserOrderEntity>();
		NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper = new NetworkExchangeMessageWrapper<OrderEventEntityDto>();
		OrderEventEntityDto orderEntity = getOrderByOrderId(msgWrapper, orderId);
		orderDetailsMsgWrapper = getUserOrderEntityForOrderEventEntity(orderEntity, orderId);
		return orderDetailsMsgWrapper;
	}
	
	private NetworkExchangeMessageWrapper<UserOrderEntity> getUserOrderEntityForOrderEventEntity(OrderEventEntityDto orderEntity, String orderId){
		NetworkExchangeMessageWrapper<UserOrderEntity> msgWrapper = new NetworkExchangeMessageWrapper<UserOrderEntity>();
		UserOrderEntity orderDetails = null;
		if(Objects.isNull(orderEntity)) {
			msgWrapper.setMessage("No order found with order id:"+ orderId);
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
			return msgWrapper;
		}
		UserEntity orderBy = userDaoService.getUserByUserId(orderEntity.getOrderBy());
		UserLocationEntity location = userLocationDao.getUserLocationById(orderBy.getLocationId());
		UserEntity currentlyWith = userDaoService.getUserByUserId(orderEntity.getCurrentlyWith());
		UserRoles currentlyWithRole =userRolesServiceDao.findById(currentlyWith.getRoleId());
		final String resourceUrl = client.getBaseUrl(EVENT_SERVICE)+ "/" + EVENT_SERVICE + "/getById/{eventId}";
		Map<String,String> urlParams = new HashMap<>();
		urlParams.put("eventId", orderEntity.getEventId());
		ServiceEntityDto service = null;
		try {
			ResponseEntity<NetworkExchangeMessageWrapper> response = client.restTemplate.getForEntity(resourceUrl, NetworkExchangeMessageWrapper.class, urlParams);
			if(Objects.nonNull(response) && Objects.nonNull(response.getBody())) {
				Map<String,String> map = (Map<String, String>) response.getBody().getPayload();
				service = mapper.convertValue(map, ServiceEntityDto.class);
			}
		}catch(RestClientException e) {
			msgWrapper.setMessage("error");
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
			return msgWrapper;
		}
		if(Objects.isNull(service)) {
			msgWrapper.setMessage("Failed. Could not find any service with serviceid");
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
			return msgWrapper;
		}
		orderDetails = new UserOrderEntity(orderId, service.getServiceName(), orderBy.getName(), currentlyWith.getName(), orderEntity.getStatus(), orderEntity.getLastupdatedAt(), orderEntity.getPlacedAt(),currentlyWithRole.getName(),location.getLocation());
		msgWrapper.setPayload(orderDetails);
		msgWrapper.setMessage("Success");
		msgWrapper.setStatus(0);
		return msgWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<UserOrderEntity> updateOrder(String orderId, String userId,
			String status) {
		NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper = new NetworkExchangeMessageWrapper<OrderEventEntityDto>();
		NetworkExchangeMessageWrapper<UserOrderEntity> msgWrapperUserOrderEntity = new NetworkExchangeMessageWrapper<UserOrderEntity>();
		final UserRoles userRole = getUserRoleForUser(userId);
		OrderEventEntityDto dto = new OrderEventEntityDto();
		dto.setOrderid(orderId);
		if (userRole.getName().equalsIgnoreCase("admin")) {
			// approve or disapprove on the basis of location.
			adminUpdationOfOrder(dto,msgWrapper,userRole.getId());
		} else if (userRole.getName().equalsIgnoreCase("provider")) {
			// accept or reject the request.
			try{
				dto.setStatus(OrderStatus.valueOf(status.toUpperCase()));
			}catch(IllegalArgumentException e) {
				msgWrapperUserOrderEntity.setMessage("Such Status is not present. process exited with message: "+e.getMessage());
				msgWrapperUserOrderEntity.setStatus(1);
				msgWrapperUserOrderEntity.setPayload(null);
				return msgWrapperUserOrderEntity;
			}
			dto.setCurrentlyWith(userId);
			providerUpdationOfOrder(dto,msgWrapper,userRole.getId());
		} else {
			msgWrapperUserOrderEntity.setMessage("consumer cannot update service once placed");
			msgWrapperUserOrderEntity.setStatus(1);
			msgWrapperUserOrderEntity.setPayload(null);
			return msgWrapperUserOrderEntity;
		}
		dto = msgWrapper.getPayload();
		if(Objects.isNull(dto)) {
			msgWrapperUserOrderEntity.setMessage("consumer cannot update service once placed");
			msgWrapperUserOrderEntity.setStatus(1);
			msgWrapperUserOrderEntity.setPayload(null);
			return msgWrapperUserOrderEntity;
		}
		msgWrapperUserOrderEntity = getUserOrderEntityForOrderEventEntity(dto, dto.getOrderid());
		return msgWrapperUserOrderEntity;
	}

	private UserRoles getUserRoleForUser(String userId) {
		UserEntity userEntity = userDaoService.getUserByUserId(userId);
		if(Objects.nonNull(userEntity)) {
			return userRolesServiceDao.findById(userEntity.getRoleId());
		}
		return null;
	}

	private void providerUpdationOfOrder(OrderEventEntityDto dto,
			NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper,String userId) {
		String orderId = dto.getOrderid();
		OrderEventEntityDto orderUnderConsideration = getOrderByOrderId(msgWrapper, orderId);
		if(Objects.isNull(orderUnderConsideration)) {
			return;
		}
		if(!(orderUnderConsideration.getStatus().equals(OrderStatus.ON_WAIT) || (orderUnderConsideration.getStatus().equals(OrderStatus.APPROVED)))) {
			msgWrapper.setMessage("Failed. Order either already complete or accepted by other provider");
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(dto);
			return;
		}
		updateOrderWithStatus(dto, msgWrapper);
	}

	private OrderEventEntityDto getOrderByOrderId(NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper,
			String orderId) {
		String resourceUrl = client.getBaseUrl(ORDER_SERVICE) + "/" +ORDER_SERVICE+"/orders/findByOrderId/order/{orderId}";
		Map<String,String> urlParams = new HashMap<String,String>();
		urlParams.put("orderId", orderId);
		OrderEventEntityDto orderUnderConsideration = null;
		try {
			ResponseEntity<NetworkExchangeMessageWrapper> response = client.restTemplate.getForEntity(resourceUrl, NetworkExchangeMessageWrapper.class,urlParams);
			if(Objects.nonNull(response) && Objects.nonNull(response.getBody()) && Objects.nonNull(response.getBody().getPayload())) {
				Map<String,String> mapValues = (Map<String, String>) response.getBody().getPayload();
				orderUnderConsideration = mapper.convertValue(mapValues, OrderEventEntityDto.class);
			}else {
				msgWrapper.setMessage("Failed To Get Order");
				msgWrapper.setStatus(1);
				msgWrapper.setPayload(null);
				return null;
			}
		} catch (RestClientException e) {
			e.printStackTrace();
			msgWrapper.setMessage("Failed To Get Order. Process Ended with error: "+ e.getMessage());
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
			return null;
		}
		return orderUnderConsideration;
	}

	private void adminUpdationOfOrder(OrderEventEntityDto dto,
			NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper,String userId) {
		dto.setCurrentlyWith(userId);
		// bring in the information about the order
		String orderId = dto.getOrderid();
		OrderEventEntityDto orderToUpdate = getOrderByOrderId(msgWrapper,orderId);
		if(Objects.isNull(orderToUpdate)) {
			return;
		}
		// get user info to the location of the user
		final UserEntity orderedBy = userDaoService.getUserByUserId(orderToUpdate.getOrderBy());
		final String locationId = orderedBy.getLocationId();
		final String serviceId = orderToUpdate.getEventId();
		// poll event service to know if service is available
		String resourceUrl = client.getBaseUrl(EVENT_SERVICE) + "/" +EVENT_SERVICE+"/fetchBylocationAndService/location/{locationId}/service/{serviceId}";
		Map<String,String> urlParams = new HashMap<>();
		urlParams.put("serviceId", serviceId);
		urlParams.put("locationId", locationId);
		List<ServiceProviderEntityDto> serviceDtoList = null;
		try {
			ResponseEntity<NetworkExchangeMessageWrapper> response = client.restTemplate.getForEntity(resourceUrl, NetworkExchangeMessageWrapper.class,urlParams);
			if(Objects.nonNull(response) && Objects.nonNull(response.getBody()) && Objects.nonNull(response.getBody().getPayload())) {
				serviceDtoList = mapper.convertValue(getAllAvailableServices().getPayload(), new TypeReference<List<ServiceProviderEntityDto>>() {});				
				if(Objects.isNull(serviceDtoList) && serviceDtoList.size() == 0){
					msgWrapper.setMessage("Order Rejected. No Such Service available at users location");
					orderToUpdate.setStatus(OrderStatus.REJECTED_BY_ADMIN);
					msgWrapper.setStatus(0);
					updateOrderWithStatus(orderToUpdate,msgWrapper);
					return;	
				}
			}else {
				msgWrapper.setMessage("Failed To Update Order");
				msgWrapper.setStatus(1);
				msgWrapper.setPayload(null);
				return;
			}
		} catch (RestClientException e) {
			e.printStackTrace();
			msgWrapper.setMessage("Failed To Update Order. Process Ended with error: "+ e.getMessage());
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
			return;
		}
		// update order to approved.
		orderToUpdate.setStatus(OrderStatus.APPROVED);
		updateOrderWithStatus(orderToUpdate,msgWrapper);
		

	}

	private void updateOrderWithStatus(OrderEventEntityDto orderToUpdate,NetworkExchangeMessageWrapper<OrderEventEntityDto> msgWrapper) {
		// update order with status
		String orderId = orderToUpdate.getOrderid();
		String resourceUrl = client.getBaseUrl(ORDER_SERVICE) + "/" +ORDER_SERVICE+"/orders/updateOrder/"+orderId;
		OrderEventEntityDto orderupdated = null;
		try {
			RequestEntity<OrderEventEntityDto> request = new RequestEntity<OrderEventEntityDto>(orderToUpdate, HttpMethod.PUT, new URI(resourceUrl));
			ResponseEntity<NetworkExchangeMessageWrapper> response = client.restTemplate.exchange(resourceUrl, HttpMethod.PUT, request, NetworkExchangeMessageWrapper.class);
			if(Objects.nonNull(response) && Objects.nonNull(response.getBody()) && Objects.nonNull(response.getBody().getPayload())) {
				orderupdated = mapper.convertValue(response.getBody().getPayload(), OrderEventEntityDto.class);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			msgWrapper.setMessage("Failed To Update Order with status: "+orderToUpdate.getStatus()+" Process Ended with error: "+ e.getMessage());
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
		} catch (RestClientException e) {
			e.printStackTrace();
			msgWrapper.setMessage("Failed To Update Order with status: "+orderToUpdate.getStatus()+" Process Ended with error: "+ e.getMessage());
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
		}
		if(Objects.isNull(orderupdated)) {
			msgWrapper.setMessage("Failed To Update Order with status: "+orderToUpdate.getStatus());
			msgWrapper.setStatus(1);
			msgWrapper.setPayload(null);
			return;
		}
		msgWrapper.setPayload(orderupdated);
		msgWrapper.setMessage("Update Order with status: "+orderupdated.getStatus());
		msgWrapper.setStatus(0);		
	}
}
