/**
 * 
 */
package com.urbanslap.userservice.servicedao;

import com.urbanslap.userservice.entity.UserEntity;

/**
 * @author deepu
 *
 */
public interface UserDaoService {
	UserEntity createUser(UserEntity user);
	UserEntity getUserByUserId(String uid);
	UserEntity getUserByUserIdandRoleId(String uid, String roleId);
	UserEntity getUserByUserIdandLocationId(String uid, String locationId);
	UserEntity updateUserByUserId(String uid, UserEntity user);
	UserEntity deleteUserByUserId(String uid);
}
