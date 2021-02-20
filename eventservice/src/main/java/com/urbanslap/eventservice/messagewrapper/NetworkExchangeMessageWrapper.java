/**
 * 
 */
package com.urbanslap.eventservice.messagewrapper;

import java.io.Serializable;

/**
 * @author deepu
 *
 */
public class NetworkExchangeMessageWrapper<T> implements Serializable {

	private static final long serialVersionUID = 5418652324551516563L;

	int status;
	String message;
	T payload;

	public NetworkExchangeMessageWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param message
	 * @param payload
	 */
	public NetworkExchangeMessageWrapper(int status, String message, T payload) {
		super();
		this.status = status;
		this.message = message;
		this.payload = payload;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
