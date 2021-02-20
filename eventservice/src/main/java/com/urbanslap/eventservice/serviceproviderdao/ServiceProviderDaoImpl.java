/**
 * 
 */
package com.urbanslap.eventservice.serviceproviderdao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.urbanslap.eventservice.entity.ServiceProviderEntity;

/**
 * @author deepu
 *
 */
@Service
public class ServiceProviderDaoImpl implements ServiceProviderDao {
	static Map<String, ServiceProviderEntity> serviceProviders;
	static {
		serviceProviders = new HashMap<>();
		serviceProviders.put("001", new ServiceProviderEntity("001", "001", "001", "002"));
		serviceProviders.put("002", new ServiceProviderEntity("002", "001", "001", "003"));
		serviceProviders.put("003", new ServiceProviderEntity("003", "001", "001", "006"));
		serviceProviders.put("004", new ServiceProviderEntity("004", "002", "001", "002"));
		serviceProviders.put("005", new ServiceProviderEntity("005", "002", "001", "003"));
		serviceProviders.put("006", new ServiceProviderEntity("006", "003", "001", "006"));
		serviceProviders.put("007", new ServiceProviderEntity("007", "003", "001", "002"));
		serviceProviders.put("008", new ServiceProviderEntity("008", "003", "001", "003"));
	}

	@Override
	public List<ServiceProviderEntity> getAllServiceProdviderRelations() {
		return Objects.nonNull(serviceProviders) && serviceProviders.size() > 0
				? serviceProviders.values().stream().collect(Collectors.toList())
				: null;
	}

}
