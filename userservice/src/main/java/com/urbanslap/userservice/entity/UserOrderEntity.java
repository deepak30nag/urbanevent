/**
 * 
 */
package com.urbanslap.userservice.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.urbanslap.userservice.enums.OrderStatus;

/**
 * @author deepakbisht
 *
 */
public class UserOrderEntity {
	String orderid;
	String eventName;
	String orderBy;
	String currentlyWith;
	String roleOfCurrentlyWith;
	public String getRoleOfCurrentlyWith() {
		return roleOfCurrentlyWith;
	}
	public void setRoleOfCurrentlyWith(String roleOfCurrentlyWith) {
		this.roleOfCurrentlyWith = roleOfCurrentlyWith;
	}
	OrderStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	Date lastUpdatedAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	Date placedAt;
	public UserOrderEntity() {
		super();
	}
	public UserOrderEntity(String orderid, String eventName, String orderBy, String currentlyWith, OrderStatus status,
			Date lastUpdatedAt, Date placedAt,String roleOfCurrentlyWith) {
		super();
		this.orderid = orderid;
		this.eventName = eventName;
		this.orderBy = orderBy;
		this.currentlyWith = currentlyWith;
		this.status = status;
		this.lastUpdatedAt = lastUpdatedAt;
		this.placedAt = placedAt;
		this.roleOfCurrentlyWith = roleOfCurrentlyWith;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getCurrentlyWith() {
		return currentlyWith;
	}
	public void setCurrentlyWith(String currentlyWith) {
		this.currentlyWith = currentlyWith;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}
	public void setLastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
	public Date getPlacedAt() {
		return placedAt;
	}
	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}
		
}
