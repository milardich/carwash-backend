package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;

import java.util.List;

public interface ResourceRepository {
    List<ResourceChartDataDTO> getResourceUsageChartData(Long stationId, String pgTimeInterval);
}
