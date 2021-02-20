package com.urbanslap.eventservice.servicedao;

import java.util.List;

import com.urbanslap.eventservice.entity.ServiceEntity;

public interface ServiceDao {
	List<ServiceEntity> getAllAvailableService();
}
