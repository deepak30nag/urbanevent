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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.userservice.entity.UserEntityData;
import com.urbanslap.userservice.facade.UserFacade;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
@RestController
@RequestMapping("/api/user-service")
public class UserResource {
	@Autowired
	UserFacade userFacade;

	@GetMapping("/findById/user/{userId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>> getUserByUserId(
			@PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<UserEntityData> networkData = userFacade.getUserByUserId(userId);
		if (Objects.nonNull(networkData.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/findByIdAndRoleId/user/{userId}/role/{roleId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>> getUserByUserIdandRoleId(
			@PathVariable(value = "userId") String userId, @PathVariable(value = "roleId") String roleId) {
		NetworkExchangeMessageWrapper<UserEntityData> networkData = userFacade.getUserByUserIdandRoleId(userId, roleId);
		if (Objects.nonNull(networkData.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/findByIdAndLocationId/user/{userId}/location/{locationId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>> getUserByUserIdandLocationId(
			@PathVariable(value = "userId") String userId, @PathVariable(value = "locationId") String locationId) {
		NetworkExchangeMessageWrapper<UserEntityData> networkData = userFacade.getUserByUserIdandLocationId(userId,
				locationId);
		if (Objects.nonNull(networkData.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/createUser/user/")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>> createUser(
			@RequestBody UserEntityData userEntityData) {
		NetworkExchangeMessageWrapper<UserEntityData> networkData = userFacade.createUser(userEntityData);
		if (Objects.nonNull(networkData.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/updateUser/user/{userId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>> createUser(
			@RequestBody UserEntityData userEntityData, @PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<UserEntityData> networkData = userFacade.updateUserByUserId(userId, userEntityData);
		if (Objects.nonNull(networkData.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/deleteById/user/{userId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>> deleteUserByUserId(
			@PathVariable(value = "userId") String userId) {
		NetworkExchangeMessageWrapper<UserEntityData> networkData = userFacade.deleteUserByUserId(userId);
		if (Objects.nonNull(networkData.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserEntityData>>(networkData,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
