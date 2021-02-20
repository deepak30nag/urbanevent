package com.urbanslap.userservice.rolesservicedao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.urbanslap.userservice.entity.UserRoles;

/**
 * @author deepu
 *
 */
@Service
public class UserRolesServiceDaoImpl implements UserRolesServiceDao {
	private static Map<String, UserRoles> userRolesDao;
	static {
		userRolesDao = new HashMap<String, UserRoles>();
		userRolesDao.put("001", new UserRoles("001", "admin"));
		userRolesDao.put("002", new UserRoles("002", "provider"));
		userRolesDao.put("003", new UserRoles("003", "consumer"));
	}

	@Override
	public UserRoles findById(String id) {
		UserRoles role = null;
		if (userRolesDao.containsKey(id)) {
			role = userRolesDao.get(id);
		}
		return role;
	}

	@Override
	public UserRoles findByRoleName(String role_name) {
		return userRolesDao.values().stream().filter(role -> role.getName().equalsIgnoreCase(role_name)).findAny()
				.orElse(null);
	}

	@Override
	public List<UserRoles> getAllUserRoles() {
		return userRolesDao.values().stream().collect(Collectors.toList());
	}
}
