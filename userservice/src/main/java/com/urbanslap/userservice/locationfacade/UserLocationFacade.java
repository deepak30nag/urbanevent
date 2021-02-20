package com.urbanslap.userservice.locationfacade;

import com.urbanslap.userservice.entity.UserLocationEntity;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface UserLocationFacade {
	NetworkExchangeMessageWrapper<UserLocationEntity> getUserLocationById(String locId);

	NetworkExchangeMessageWrapper<UserLocationEntity> createUserLocation(UserLocationEntity entity);
}
