/**
 * 
 */
package com.urbanslap.userservice.converter;

import java.util.Objects;

import com.urbanslap.userservice.entity.UserEntity;
import com.urbanslap.userservice.entity.UserEntityData;

/**
 * @author deepu
 *
 */
public class UserEntityConverter {
	public static UserEntityData userDtoFromUserEntity(UserEntity user) {
		UserEntityData dto = null;
		if (Objects.nonNull(user)) {
			dto = new UserEntityData();
			dto.setUid(user.getUid());
			dto.setName(user.getName());
			dto.setPhoneNumber(user.getPhoneNumber());
			dto.setEmail(user.getEmail());
			dto.setRoleId(user.getRoleId());
			dto.setLocationId(user.getLocationId());
		}
		return dto;
	}

	public static UserEntity userEntityFromUserDto(UserEntityData userData) {
		UserEntity userEntity = null;
		if (Objects.nonNull(userData)) {
			userEntity = new UserEntity(0, "000", userData.getName(), userData.getPhoneNumber(), userData.getEmail(),
					userData.getRoleId(), userData.getLocationId());
		}
		return userEntity;
	}
}
