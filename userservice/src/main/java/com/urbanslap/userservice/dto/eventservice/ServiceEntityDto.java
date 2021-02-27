/**
 * 
 */
package com.urbanslap.userservice.dto.eventservice;

/**
 * @author deepu
 *
 */
public class ServiceEntityDto {
	String serviceId;
	String serviceName;

	/**
	 * @param serviceId
	 * @param serviceName
	 */
	public ServiceEntityDto(String serviceId, String serviceName) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
	}

	/**
	 * 
	 */
	public ServiceEntityDto() {
		super();
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
