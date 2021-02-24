package com.urbanslap.eventservice.serviceproviderfacade;

import java.util.List;

import com.urbanslap.eventservice.entity.ServiceProviderEntity;
import com.urbanslap.eventservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface ServiceProviderFacade {

	NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> getAllServiceProviderRelations();

	NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> getServiceProviderRelationsByLocation(String locationId);

	NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> getServiceProviderRelationsByLocationAndServiceId(
			String locationId, String serviceId);

}
