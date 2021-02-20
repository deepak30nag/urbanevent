package com.urbanslap.userservice.locationservicedao;

import com.urbanslap.userservice.entity.UserLocationEntity;

public interface UserLocationServiceDao {
	UserLocationEntity getUserLocationById(String locId);

	UserLocationEntity createUserLocation(UserLocationEntity entity);
}
