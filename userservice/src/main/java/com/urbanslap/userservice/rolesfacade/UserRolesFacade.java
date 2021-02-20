package com.urbanslap.userservice.rolesfacade;

import java.util.List;

import com.urbanslap.userservice.entity.UserRoles;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface UserRolesFacade {
	NetworkExchangeMessageWrapper<UserRoles> findById(String id);

	NetworkExchangeMessageWrapper<UserRoles> findByRoleName(String role_name);

	NetworkExchangeMessageWrapper<List<UserRoles>> getAllUserRoles();
}
