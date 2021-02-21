/**
 * 
 */
package com.urbanslap.userservice.rolesfacade;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbanslap.userservice.entity.UserRoles;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.rolesservicedao.UserRolesServiceDao;

/**
 * @author deepu
 *
 */
@Component
public class UserRolesFacadeImpl implements UserRolesFacade {
	@Autowired
	UserRolesServiceDao userRolesServiceDao;

	@Override
	public NetworkExchangeMessageWrapper<UserRoles> findById(String id) {
		NetworkExchangeMessageWrapper<UserRoles> roleMessageWrapper = new NetworkExchangeMessageWrapper<UserRoles>();
		final UserRoles role = userRolesServiceDao.findById(id);
		if (Objects.nonNull(role)) {
			roleMessageWrapper.setStatus(0);
			roleMessageWrapper.setMessage("Found Role by id: " + id);
			roleMessageWrapper.setPayload(role);
			return roleMessageWrapper;
		}
		roleMessageWrapper.setStatus(1);
		roleMessageWrapper.setMessage("No Roles with id: " + id);
		roleMessageWrapper.setPayload(null);
		return roleMessageWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<UserRoles> findByRoleName(String role_name) {
		NetworkExchangeMessageWrapper<UserRoles> roleMessageWrapper = new NetworkExchangeMessageWrapper<UserRoles>();
		final UserRoles role = userRolesServiceDao.findByRoleName(role_name);
		if (Objects.nonNull(role)) {
			roleMessageWrapper.setStatus(2);
			roleMessageWrapper.setMessage("Found Role By name: " + role_name);
			roleMessageWrapper.setPayload(role);
			return roleMessageWrapper;
		}
		roleMessageWrapper.setStatus(3);
		roleMessageWrapper.setMessage("No Roles found by name: " + role_name);
		roleMessageWrapper.setPayload(null);
		return roleMessageWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<List<UserRoles>> getAllUserRoles() {
		NetworkExchangeMessageWrapper<List<UserRoles>> roleMessageWrapper = new NetworkExchangeMessageWrapper<List<UserRoles>>();
		List<UserRoles> rolesList = userRolesServiceDao.getAllUserRoles();
		if (Objects.nonNull(rolesList) && rolesList.size() > 0) {
			roleMessageWrapper.setStatus(4);
			roleMessageWrapper.setMessage("List of User Roles");
			roleMessageWrapper.setPayload(rolesList);
			return roleMessageWrapper;
		}
		roleMessageWrapper.setStatus(5);
		roleMessageWrapper.setMessage("No Roles found");
		roleMessageWrapper.setPayload(null);
		return roleMessageWrapper;
	}

}
