/**
 * 
 */
package com.urbanslap.eventservice.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.eventservice.entity.ServiceProviderEntity;
import com.urbanslap.eventservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.eventservice.serviceproviderfacade.ServiceProviderFacade;

/**
 * @author deepu
 *
 */
@RestController
@RequestMapping("/api/event-service")
public class ServiceProviderRelationResource {

	@Autowired
	ServiceProviderFacade serviceProviderFacade;
	
	@GetMapping("/getAllServicesProvidedByProviders")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceProviderEntity>>> getAllAvailableServiceProviderRelations(){
		NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> messageWrapper = serviceProviderFacade.getAllServiceProviderRelations();
		if(Objects.nonNull(messageWrapper.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceProviderEntity>>>(messageWrapper, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceProviderEntity>>>(messageWrapper, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
}
