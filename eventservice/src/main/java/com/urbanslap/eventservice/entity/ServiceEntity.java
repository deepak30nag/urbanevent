/**
 * 
 */
package com.urbanslap.eventservice.entity;

/**
 * @author deepu
 *
 */
public class ServiceEntity {
	String serviceId;
	String serviceName;

	/**
	 * @param serviceId
	 * @param serviceName
	 */
	public ServiceEntity(String serviceId, String serviceName) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
	}

	/**
	 * 
	 */
	public ServiceEntity() {
		super();
		// TODO Auto-generated constructor stub
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
