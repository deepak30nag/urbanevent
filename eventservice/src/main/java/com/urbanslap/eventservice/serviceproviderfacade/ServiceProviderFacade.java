package com.urbanslap.eventservice.serviceproviderfacade;

import java.util.List;

import com.urbanslap.eventservice.entity.ServiceProviderEntity;
import com.urbanslap.eventservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface ServiceProviderFacade {

	NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> getAllServiceProviderRelations();

}
