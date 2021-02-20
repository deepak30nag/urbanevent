/**
 * 
 */
package com.urbanslap.userservice.locationservicedao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.urbanslap.userservice.entity.UserLocationEntity;

/**
 * @author deepu
 *
 */
@Service
public class UserLocationServiceDaoImpl implements UserLocationServiceDao {
	static Map<String, UserLocationEntity> userLocationDao;
	static {
		userLocationDao = new HashMap<String, UserLocationEntity>();
		userLocationDao.put("001", new UserLocationEntity(1, "001", "NORTH_DELHI"));
		userLocationDao.put("002", new UserLocationEntity(2, "002", "EAST_DELHI"));
		userLocationDao.put("003", new UserLocationEntity(3, "003", "WEST_DELHI"));
		userLocationDao.put("004", new UserLocationEntity(4, "004", "SOUTH_DELHI"));
		userLocationDao.put("005", new UserLocationEntity(5, "005", "NOIDA"));
		userLocationDao.put("006", new UserLocationEntity(6, "006", "GREATER_NOIDA"));
		userLocationDao.put("007", new UserLocationEntity(7, "007", "GURUGRAM"));
		userLocationDao.put("008", new UserLocationEntity(8, "008", "GHAZIABAD"));
		userLocationDao.put("009", new UserLocationEntity(9, "009", "FARIDABAD"));
	}

	@Override
	public UserLocationEntity getUserLocationById(String locId) {
		if (userLocationDao.containsKey(locId)) {
			return userLocationDao.get(locId);
		}
		return null;
	}

	@Override
	public UserLocationEntity createUserLocation(UserLocationEntity entity) {
		Optional<UserLocationEntity> optionalEntity = userLocationDao.values().stream()
				.filter(entry -> entry.getLocation().equals(entity.getLocation())).findAny();
		if (optionalEntity.isPresent()) {
			return optionalEntity.get();
		} else {
			entity.setId(userLocationDao.size() + 1);
			entity.setLoc_id(userLocationDao.size() <= 9 ? "00" + entity.getId() : "0" + entity.getId());
			userLocationDao.put(entity.getLoc_id(), entity);
			return entity;
		}
	}

}
