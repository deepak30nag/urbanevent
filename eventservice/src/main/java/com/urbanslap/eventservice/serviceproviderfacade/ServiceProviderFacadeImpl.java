/**
 * 
 */
package com.urbanslap.eventservice.serviceproviderfacade;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbanslap.eventservice.entity.ServiceProviderEntity;
import com.urbanslap.eventservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.eventservice.serviceproviderdao.ServiceProviderDao;

/**
 * @author deepu
 *
 */
@Component
public class ServiceProviderFacadeImpl implements ServiceProviderFacade {
	
	
	@Autowired
	ServiceProviderDao serviceProviderDao;
	
	@Override
	public NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> getAllServiceProviderRelations(){
		NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> serviceProviderRelations = new NetworkExchangeMessageWrapper<List<ServiceProviderEntity>>();
		serviceProviderRelations.setPayload(serviceProviderDao.getAllServiceProdviderRelations());
		if(Objects.nonNull(serviceProviderRelations.getPayload())) {
			serviceProviderRelations.setStatus(0);
			serviceProviderRelations.setMessage("Success");
		} else {
			serviceProviderRelations.setStatus(1);
			serviceProviderRelations.setMessage("Failure");			
		}
		return serviceProviderRelations;
		
	}
	
	@Override
	public NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> getServiceProviderRelationsByLocation(String locationId){
		NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> serviceProviderRelations = new NetworkExchangeMessageWrapper<List<ServiceProviderEntity>>();
		serviceProviderRelations.setPayload(serviceProviderDao.getAllServiceProviderRelationsByLocationId(locationId));
		if(Objects.nonNull(serviceProviderRelations.getPayload()) && serviceProviderRelations.getPayload().size() > 0) {
			serviceProviderRelations.setStatus(0);
			serviceProviderRelations.setMessage("Success");
		} else {
			serviceProviderRelations.setStatus(1);
			serviceProviderRelations.setMessage("Failure");			
		}
		return serviceProviderRelations;		
	}
	
	@Override
	public NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> getServiceProviderRelationsByLocationAndServiceId(String locationId, String serviceId){
		NetworkExchangeMessageWrapper<List<ServiceProviderEntity>> serviceProviderRelations = new NetworkExchangeMessageWrapper<List<ServiceProviderEntity>>();
		serviceProviderRelations.setPayload(serviceProviderDao.getAllServiceProviderRelationsByLocationIdAndServiceId(locationId,serviceId));
		if(Objects.nonNull(serviceProviderRelations.getPayload()) && serviceProviderRelations.getPayload().size() > 0) {
			serviceProviderRelations.setStatus(0);
			serviceProviderRelations.setMessage("Success");
		} else {
			serviceProviderRelations.setStatus(1);
			serviceProviderRelations.setMessage("Failure");			
		}
		return serviceProviderRelations;		
	}
	
}
