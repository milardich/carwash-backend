package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;

import java.util.List;

public interface ChartDataRepository {
    List<ResourceChartDataDTO> getResourceUsageChartData(Long stationId, String pgTimeInterval, String timezone);
}
