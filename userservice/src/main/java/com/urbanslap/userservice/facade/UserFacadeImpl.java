package com.urbanslap.userservice.facade;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbanslap.userservice.converter.UserEntityConverter;
import com.urbanslap.userservice.entity.UserEntity;
import com.urbanslap.userservice.entity.UserEntityData;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.userservice.servicedao.UserDaoService;

/**
 * @author deepu
 *
 */
@Component
public class UserFacadeImpl implements UserFacade {

	@Autowired
	private UserDaoService userDaoService;

	public NetworkExchangeMessageWrapper<UserEntityData> createUser(UserEntityData userEntityDto) {
		final NetworkExchangeMessageWrapper<UserEntityData> userDto = new NetworkExchangeMessageWrapper<UserEntityData>();
		UserEntity user = new UserEntity(0, "000", userEntityDto.getName(), userEntityDto.getPhoneNumber(),
				userEntityDto.getEmail(), userEntityDto.getRoleId(), userEntityDto.getLocationId());
		UserEntity createdUser = userDaoService.createUser(user);
		if (Objects.nonNull(createdUser)) {
			userDto.setStatus(00);
			userDto.setMessage("Successfully Created");
			userDto.setPayload(UserEntityConverter.userDtoFromUserEntity(createdUser));
			return userDto;
		}
		userDto.setStatus(01);
		userDto.setMessage("Error While Creating");
		userDto.setPayload(null);
		return userDto;
	}

	public NetworkExchangeMessageWrapper<UserEntityData> getUserByUserId(String uid) {
		final NetworkExchangeMessageWrapper<UserEntityData> userDto = new NetworkExchangeMessageWrapper<UserEntityData>();
		UserEntity user = userDaoService.getUserByUserId(uid);
		if (Objects.nonNull(user)) {
			UserEntityData entityDto = UserEntityConverter.userDtoFromUserEntity(user);
			userDto.setStatus(02);
			userDto.setMessage("User Found");
			userDto.setPayload(entityDto);
			return userDto;
		}
		userDto.setStatus(03);
		userDto.setMessage("Error While Searching");
		userDto.setPayload(null);
		return userDto;
	}
	
	public NetworkExchangeMessageWrapper<UserEntityData> getUserByUserIdandRoleId(String uid, String roleId) {
		final NetworkExchangeMessageWrapper<UserEntityData> userDto = new NetworkExchangeMessageWrapper<UserEntityData>();
		UserEntity user = userDaoService.getUserByUserIdandRoleId(uid,roleId);
		if (Objects.nonNull(user)) {
			UserEntityData entityDto = UserEntityConverter.userDtoFromUserEntity(user);
			userDto.setStatus(2);
			userDto.setMessage("User Found");
			userDto.setPayload(entityDto);
			return userDto;
		}
		userDto.setStatus(3);
		userDto.setMessage("Error While Searching");
		userDto.setPayload(null);
		return userDto;
	}
	
	public NetworkExchangeMessageWrapper<UserEntityData> getUserByUserIdandLocationId(String uid, String locationId) {
		final NetworkExchangeMessageWrapper<UserEntityData> userDto = new NetworkExchangeMessageWrapper<UserEntityData>();
		UserEntity user = userDaoService.getUserByUserIdandLocationId(uid,locationId);
		if (Objects.nonNull(user)) {
			UserEntityData entityDto = UserEntityConverter.userDtoFromUserEntity(user);
			userDto.setStatus(2);
			userDto.setMessage("User Found");
			userDto.setPayload(entityDto);
			return userDto;
		}
		userDto.setStatus(3);
		userDto.setMessage("Error While Searching");
		userDto.setPayload(null);
		return userDto;
	}
	
	public NetworkExchangeMessageWrapper<UserEntityData> updateUserByUserId(String uid, UserEntityData userEntityData) {
		final NetworkExchangeMessageWrapper<UserEntityData> userDto = new NetworkExchangeMessageWrapper<UserEntityData>();
		UserEntity user = UserEntityConverter.userEntityFromUserDto(userEntityData);
		user = userDaoService.updateUserByUserId(uid, user);
		if (Objects.nonNull(user)) {
			UserEntityData entityDto = UserEntityConverter.userDtoFromUserEntity(user);
			userDto.setStatus(4);
			userDto.setMessage("User Updated");
			userDto.setPayload(entityDto);
			return userDto;
		}
		userDto.setStatus(5);
		userDto.setMessage("Error While Updating");
		userDto.setPayload(null);
		return userDto;
	}
	
	public NetworkExchangeMessageWrapper<UserEntityData> deleteUserByUserId(String uid) {
		final NetworkExchangeMessageWrapper<UserEntityData> userDto = new NetworkExchangeMessageWrapper<UserEntityData>();
		UserEntity user = userDaoService.deleteUserByUserId(uid);
		if (Objects.nonNull(user)) {
			UserEntityData entityDto = UserEntityConverter.userDtoFromUserEntity(user);
			userDto.setStatus(6);
			userDto.setMessage("User Deleted");
			userDto.setPayload(entityDto);
			return userDto;
		}
		userDto.setStatus(7);
		userDto.setMessage("Error While Deletion");
		userDto.setPayload(null);
		return userDto;
	}
	
}
