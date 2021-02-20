/**
 * 
 */
package com.urbanslap.eventservice.entity;

/**
 * @author deepu
 *
 */
public class ServiceProviderEntity {
	String id;
	String serviceId;
	String locationId;
	String providerId;

	/**
	 * 
	 */
	public ServiceProviderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param serviceId
	 * @param locationId
	 * @param providerId
	 */
	public ServiceProviderEntity(String id, String serviceId, String locationId, String providerId) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.locationId = locationId;
		this.providerId = providerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

}
