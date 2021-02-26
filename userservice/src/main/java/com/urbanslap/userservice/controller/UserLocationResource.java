/**
 * 
 */
package com.urbanslap.userservice.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.userservice.entity.UserLocationEntity;
import com.urbanslap.userservice.locationfacade.UserLocationFacade;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
@RestController
@RequestMapping("/user-service")
public class UserLocationResource {

	@Autowired
	UserLocationFacade userLocationFacade;

	@GetMapping("/findById/location/{locationId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserLocationEntity>> getLocationById(
			@PathVariable(value = "locationId") String locationId) {
		NetworkExchangeMessageWrapper<UserLocationEntity> entity = userLocationFacade.getUserLocationById(locationId);
		if (Objects.nonNull(entity.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserLocationEntity>>(entity, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserLocationEntity>>(entity,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/findById/location")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserLocationEntity>> createLocation(
			@RequestBody UserLocationEntity userLocation) {
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserLocationEntity>>(
				userLocationFacade.createUserLocation(userLocation), HttpStatus.OK);
	}

}
