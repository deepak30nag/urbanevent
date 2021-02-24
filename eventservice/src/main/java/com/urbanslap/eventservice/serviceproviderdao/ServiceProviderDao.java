package com.urbanslap.eventservice.serviceproviderdao;

import java.util.List;

import com.urbanslap.eventservice.entity.ServiceProviderEntity;

public interface ServiceProviderDao {

	List<ServiceProviderEntity> getAllServiceProdviderRelations();

	List<ServiceProviderEntity> getAllServiceProviderRelationsByLocationId(String locationId);

	List<ServiceProviderEntity> getAllServiceProviderRelationsByLocationIdAndServiceId(String locationId,
			String serviceId);

}
