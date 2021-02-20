/**
 * 
 */
package com.urbanslap.userservice.facade;

import com.urbanslap.userservice.entity.UserEntityData;
import com.urbanslap.userservice.messagewrapper.NetworkExchangeMessageWrapper;

/**
 * @author deepu
 *
 */
public interface UserFacade {
	NetworkExchangeMessageWrapper<UserEntityData> createUser(UserEntityData userEntityDto);
	NetworkExchangeMessageWrapper<UserEntityData> getUserByUserId(String uid);
	NetworkExchangeMessageWrapper<UserEntityData> getUserByUserIdandRoleId(String uid, String roleId);
	NetworkExchangeMessageWrapper<UserEntityData> getUserByUserIdandLocationId(String uid, String locationId);
	NetworkExchangeMessageWrapper<UserEntityData> updateUserByUserId(String uid, UserEntityData userEntityData);
	NetworkExchangeMessageWrapper<UserEntityData> deleteUserByUserId(String uid);
}
