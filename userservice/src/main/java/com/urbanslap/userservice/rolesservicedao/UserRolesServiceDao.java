package com.urbanslap.userservice.rolesservicedao;

import java.util.List;

import com.urbanslap.userservice.entity.UserRoles;

public interface UserRolesServiceDao {

	UserRoles findById(String id);

	UserRoles findByRoleName(String role);

	public List<UserRoles> getAllUserRoles();
}
