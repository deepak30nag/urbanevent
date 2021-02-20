/**
 * 
 */
package com.urbanslap.eventservice.servicefacades;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbanslap.eventservice.entity.ServiceEntity;
import com.urbanslap.eventservice.messagewrapper.NetworkExchangeMessageWrapper;
import com.urbanslap.eventservice.servicedao.ServiceDao;

/**
 * @author deepu
 *
 */
@Component
public class ServiceFacadesImpl implements ServiceFacades {
	
	@Autowired
	ServiceDao serviceDao;
	
	@Override
	public NetworkExchangeMessageWrapper<List<ServiceEntity>> getAllAvailableServices(){
		NetworkExchangeMessageWrapper<List<ServiceEntity>> messageWrapper = new NetworkExchangeMessageWrapper<List<ServiceEntity>>();
		final List<ServiceEntity> listofAvailableServices = serviceDao.getAllAvailableService();
		if(Objects.nonNull(listofAvailableServices) && listofAvailableServices.size() > 0) {
			messageWrapper.setStatus(0);
			messageWrapper.setMessage("Returning All Available Services");
			messageWrapper.setPayload(listofAvailableServices);
			return messageWrapper;
		}
		messageWrapper.setStatus(1);
		messageWrapper.setMessage("Error in finding the list of all available services");
		messageWrapper.setPayload(listofAvailableServices);
		return messageWrapper;
		
	}
}
