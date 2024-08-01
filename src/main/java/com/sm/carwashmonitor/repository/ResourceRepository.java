package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.TotalResourceUsageDTO;

import java.util.List;

public interface ResourceRepository {
    List<TotalResourceUsageDTO> getResourcesUsageByDateTimeRange(Long stationId, String dateTimeRange);
}
