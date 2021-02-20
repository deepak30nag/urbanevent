/**
 * 
 */
package com.urbanslap.userservice.servicedao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.urbanslap.userservice.entity.UserEntity;

/**
 * @author deepu Basic CRUD operation for in memory user entity
 */
@Service
public class UserDaoServiceImpl implements UserDaoService {

	/*
	 * second last attribute
	 * 001 : admin
	 * 002 : provider
	 * 003 : consumer 
	*/
	static Map<String, UserEntity> userDao;
	static {
		userDao = new HashMap<String, UserEntity>();
		userDao.put("001", new UserEntity(1, "001", "Fury", "0123456789", "sheThor@avengers.com", "001", "001"));
		userDao.put("002",
				new UserEntity(2, "002", "Steve Rogers", "0123456789", "captainAmerica@avengers.com", "002", "001"));
		userDao.put("003", new UserEntity(3, "003", "Thor Odinson", "0123456789", "thor@avengers.com", "002", "004"));
		userDao.put("004", new UserEntity(4, "004", "Tony Stark", "0123456789", "ironMan@avengers.com", "003", "001"));
		userDao.put("005", new UserEntity(5, "005", "Pepper Potts", "0123456789", "rescue@avengers.com", "003", "004"));
		userDao.put("006", new UserEntity(6, "006", "Wade Wilson", "0123456789", "deadpool@xforce.com", "002", "001"));
		userDao.put("007",
				new UserEntity(7, "007", "Kamala Khan", "0123456789", "ms.marvel@nextgenavengers.com", "002", "001"));
		userDao.put("008",
				new UserEntity(8, "008", "Natasha Romanoff", "0123456789", "blackWidow@avengers.com", "003", "004"));
		userDao.put("009", new UserEntity(9, "009", "Jane Foster", "0123456789", "sheThor@avenger.com", "002", "001"));
		userDao.put("010", new UserEntity(10, "010", "Bruce Banner", "0123456789", "hulk@xforce.com", "003", "001"));
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		if (userDao.size() == 0) {
			userDao = new HashMap<String, UserEntity>();
			user.setId(1);
			user.setUid("001");
			userDao.put("001", user);
		} else {
			user.setId(userDao.size() + 1);
			if (user.getId() <= 9) {
				user.setUid(new StringBuilder("00").append(user.getId()).toString());
			} else if (user.getId() > 9) {
				user.setUid(new StringBuilder("0").append(user.getId()).toString());
			} else {
				user.setUid(Integer.valueOf(user.getId()).toString());
			}
		}
		return user;
	}

	@Override
	public UserEntity getUserByUserId(String uid) {
		if (userDao.containsKey(uid)) {
			return userDao.get(uid);
		}
		return null;
	}

	@Override
	public UserEntity getUserByUserIdandRoleId(String uid, String roleId) {
		if (userDao.containsKey(uid)) {
			UserEntity user = userDao.get(uid);
			if (user.getRoleId().equals(roleId)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public UserEntity getUserByUserIdandLocationId(String uid, String locationId) {
		if (userDao.containsKey(uid)) {
			UserEntity user = userDao.get(uid);
			if (user.getLocationId().equals(locationId)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public UserEntity updateUserByUserId(String uid, UserEntity user) {
		if (userDao.containsKey(uid)) {
			UserEntity userToUpdate = userDao.get(uid);
			user.setId(userToUpdate.getId());
			user.setUid(userToUpdate.getUid());
			userDao.put(uid, user);
			return user;
		} else {
			return null;
		}
	}

	@Override
	public UserEntity deleteUserByUserId(String uid) {
		if (userDao.containsKey(uid)) {
			UserEntity userToDelete = userDao.get(uid);
			userDao.remove(uid);
			return userToDelete;
		}
		return null;
	}

}
