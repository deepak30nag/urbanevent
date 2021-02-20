/**
 * 
 */
package com.urbanslap.userservice.entity;

/**
 * @author
 *
 */
public class UserEntity {
	int id;
	String uid;
	String name;
	String phoneNumber;
	String email;
	String roleId;
	String locationId;

	/**
	 * Default Constructor
	 */
	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param uid
	 * @param name
	 * @param phoneNumber
	 * @param email
	 * @param roleId
	 * @param locationId
	 */
	public UserEntity(int id, String uid, String name, String phoneNumber, String email, String roleId,
			String locationId) {
		super();
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.roleId = roleId;
		this.locationId = locationId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
