/**
 * 
 */
package com.urbanslap.orderservice.entity.userrole.dto;

/**
 * @author deepu
 *
 */
public class UserRoles {
	String id;
	String name;

	/**
	 * @param id
	 * @param name
	 */
	public UserRoles(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * 
	 */
	public UserRoles() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
