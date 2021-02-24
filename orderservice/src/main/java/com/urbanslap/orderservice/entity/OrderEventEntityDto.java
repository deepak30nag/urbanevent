/**
 * 
 */
package com.urbanslap.orderservice.entity;

import java.util.Date;

/**
 * @author deepu
 *
 */
public class OrderEventEntityDto {
	String orderid;
	String eventId;
	String orderBy;
	String currentlyWith;
	String status;
	Date lastupdatedAt;
	Date placedAt;

	/**
	 * 
	 */
	public OrderEventEntityDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderid
	 * @param eventId
	 * @param orderBy
	 * @param currentlyWith
	 * @param status
	 * @param lastupdatedAt
	 * @param placedAt
	 */
	public OrderEventEntityDto(String orderid, String eventId, String orderBy, String currentlyWith, String status,
			Date lastupdatedAt, Date placedAt) {
		super();
		this.orderid = orderid;
		this.eventId = eventId;
		this.orderBy = orderBy;
		this.currentlyWith = currentlyWith;
		this.status = status;
		this.lastupdatedAt = lastupdatedAt;
		this.placedAt = placedAt;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastupdatedAt() {
		return lastupdatedAt;
	}

	public void setLastupdatedAt(Date lastupdatedAt) {
		this.lastupdatedAt = lastupdatedAt;
	}

	public Date getPlacedAt() {
		return placedAt;
	}

	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}
}
