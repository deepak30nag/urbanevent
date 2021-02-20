/**
 * 
 */
package com.urbanslap.userservice.locationfacade;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbanslap.userservice.entity.UserLocationEntity;
import com.urbanslap.userservice.locationservicedao.UserLocationServiceDao;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
@Component
public class UserLocationFacadeImpl implements UserLocationFacade {

	@Autowired
	UserLocationServiceDao userLocationService;

	@Override
	public NetworkExchangeMessageWrapper<UserLocationEntity> getUserLocationById(String locId) {
		NetworkExchangeMessageWrapper<UserLocationEntity> messageWrapper = new NetworkExchangeMessageWrapper<UserLocationEntity>();
		UserLocationEntity entity = userLocationService.getUserLocationById(locId);
		if (Objects.nonNull(entity)) {
			messageWrapper.setStatus(0);
			messageWrapper.setMessage("Location found");
			messageWrapper.setPayload(entity);
			return messageWrapper;
		}
		messageWrapper.setStatus(1);
		messageWrapper.setMessage("Location Not found");
		messageWrapper.setPayload(null);
		return messageWrapper;
	}

	@Override
	public NetworkExchangeMessageWrapper<UserLocationEntity> createUserLocation(UserLocationEntity entity) {
		NetworkExchangeMessageWrapper<UserLocationEntity> messageWrapper = new NetworkExchangeMessageWrapper<UserLocationEntity>();
		entity = userLocationService.createUserLocation(entity);
		if (Objects.nonNull(entity)) {
			messageWrapper.setStatus(2);
			messageWrapper.setMessage("Location Created");
			messageWrapper.setPayload(entity);
		}
		return messageWrapper;
	}
}
