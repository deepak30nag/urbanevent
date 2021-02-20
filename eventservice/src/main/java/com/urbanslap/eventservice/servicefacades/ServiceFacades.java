package com.urbanslap.eventservice.servicefacades;

import java.util.List;

import com.urbanslap.eventservice.entity.ServiceEntity;
import com.urbanslap.eventservice.messagewrapper.NetworkExchangeMessageWrapper;

public interface ServiceFacades {
	NetworkExchangeMessageWrapper<List<ServiceEntity>> getAllAvailableServices();
}
