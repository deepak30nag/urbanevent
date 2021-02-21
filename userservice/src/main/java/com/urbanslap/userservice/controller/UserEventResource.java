/**
 * 
 */
package com.urbanslap.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.userservice.dto.eventservice.ServiceEntityDto;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.usereventresource.facades.UserEventResourceFacade;

/**
 * @author deepu
 *
 */
@RestController
@RequestMapping("/api/user-service")
public class UserEventResource {

	@Autowired
	UserEventResourceFacade userEventResourceFacade;

	@GetMapping("/{userId}/allAvailableEvents")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceEntityDto>>> getAllAvailabeEvents(
			@PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<List<ServiceEntityDto>> payload = userEventResourceFacade.serviceEntity();
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<ServiceEntityDto>>>(payload, HttpStatus.OK);
	}
}
