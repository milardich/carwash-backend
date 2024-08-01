package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.ResourceUsageChartDataDTO;

import java.util.List;

public interface ResourceRepository {
    List<ResourceUsageChartDataDTO> getResourceUsageChartData(Long stationId, String dateTimeRange);
}
