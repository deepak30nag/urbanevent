/**
 * 
 */
package com.urbanslap.eventservice.servicedao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.urbanslap.eventservice.entity.ServiceEntity;

/**
 * @author deepu
 *
 */
@Service
public class ServiceDaoImpl implements ServiceDao {
	static Map<String, ServiceEntity> servicesDao;
	static {
		servicesDao = new HashMap<>();
		servicesDao.put("001", new ServiceEntity("001", "Beauty and Wellness"));
		servicesDao.put("002", new ServiceEntity("002", "Repair Services"));
		servicesDao.put("003", new ServiceEntity("003", "Home Maintenance"));
		servicesDao.put("004", new ServiceEntity("004", "Home Care and Design"));
		servicesDao.put("005", new ServiceEntity("005", "Weddings"));
	}

	@Override
	public List<ServiceEntity> getAllAvailableService() {
		return Objects.nonNull(servicesDao) && servicesDao.size() > 0
				? servicesDao.values().stream().collect(Collectors.toList())
				: null;
	}
	
	@Override
	public ServiceEntity getById(String id) {
		if(Objects.nonNull(servicesDao) && servicesDao.size()>0) {
			if(servicesDao.containsKey(id)) {
				return servicesDao.get(id);
			}
		}
		return null;
	}
}
