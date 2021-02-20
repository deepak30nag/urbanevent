/**
 * 
 */
package com.urbanslap.userservice.entity;

/**
 * @author deepu
 *
 */
public class UserLocationEntity {
	int id;
	String loc_id;
	String location;

	public UserLocationEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param loc_id
	 * @param location
	 */
	public UserLocationEntity(int id, String loc_id, String location) {
		super();
		this.id = id;
		this.loc_id = loc_id;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
