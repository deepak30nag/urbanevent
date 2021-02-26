/**
 * 
 */
package com.urbanslap.userservice.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanslap.userservice.entity.UserRoles;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.rolesfacade.UserRolesFacade;

/**
 * @author deepu
 *
 */
@RestController
@RequestMapping("/user-service")
public class UserRolesResource {

	@Autowired
	UserRolesFacade userRolesFacade;

	@GetMapping("/getAllUserRoles")
	public ResponseEntity<NetworkExchangeMessageWrapper<List<UserRoles>>> getAllUserRoles() {
		NetworkExchangeMessageWrapper<List<UserRoles>> messageWrapper = userRolesFacade.getAllUserRoles();
		if (Objects.nonNull(messageWrapper.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<List<UserRoles>>>(messageWrapper, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<List<UserRoles>>>(messageWrapper,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/findById/role/{roleId}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserRoles>> getUserRoleById(
			@PathVariable("roleId") String roleId) {
		NetworkExchangeMessageWrapper<UserRoles> messageWrapper = userRolesFacade.findById(roleId);
		if (Objects.nonNull(messageWrapper.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserRoles>>(messageWrapper, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserRoles>>(messageWrapper,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/findByName/role/{roleName}")
	public ResponseEntity<NetworkExchangeMessageWrapper<UserRoles>> getUserRoleByRoleName(
			@PathVariable("roleName") String roleName) {
		NetworkExchangeMessageWrapper<UserRoles> messageWrapper = userRolesFacade.findByRoleName(roleName);
		if (Objects.nonNull(messageWrapper.getPayload())) {
			return new ResponseEntity<NetworkExchangeMessageWrapper<UserRoles>>(messageWrapper, HttpStatus.OK);
		}
		return new ResponseEntity<NetworkExchangeMessageWrapper<UserRoles>>(messageWrapper,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
