/**
 * 
 */
package com.urbanslap.userservice.entity;

import java.io.Serializable;

/**
 * @author deepu
 *
 */
public class UserEntityData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7176502727265744356L;
	String uid;
	String name;
	String phoneNumber;
	String email;
	String roleId;
	String locationId;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
}
