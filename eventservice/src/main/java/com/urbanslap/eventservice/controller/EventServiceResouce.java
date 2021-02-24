package com.urbanslap.eventservice.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.eventservice.entity.ServiceEntity;
import com.urbanslap.eventservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.eventservice.servicefacades.ServiceFacades;

@RestController
@RequestMapping("/api/event-service")
public class EventServiceResouce {

	@Autowired
	ServiceFacades serviceFacades;

	@GetMapping("/allAvailableServices")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceEntity>>> getAllAvailableServices() {
		NetworkExchangeMessageWrapper<List<ServiceEntity>> messageWrapper = serviceFacades.getAllAvailableServices();
		if (Objects.nonNull(messageWrapper.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceEntity>>>(messageWrapper,
					HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceEntity>>>(messageWrapper,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/checkAvailability/{eventName}")
	public ResponseEntity<Boolean> checkIfEventIsAvailable(@PathVariable(name="eventName")String eventName){
		return new ResponseEntity<Boolean>(serviceFacades.checkIfAvailable(eventName),HttpStatus.OK);
		
	}
	
	@GetMapping("/getById/{eventId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<ServiceEntity>> getByEventId(
			@PathVariable("eventId") String eventId) {
		NetworkExchangeMessageWrapper<ServiceEntity> messageWrapper = serviceFacades.getByEventId(eventId);
		if (Objects.nonNull(messageWrapper.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<ServiceEntity>>(messageWrapper,HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<ServiceEntity>>(messageWrapper,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
