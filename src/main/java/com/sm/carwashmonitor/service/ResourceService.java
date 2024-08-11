package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.ResourceUsageChartDataDTO;
import java.util.List;

public interface ResourceService {
    List<ResourceUsageChartDataDTO> getResourceUsageChartData(Long stationId, String pgTimeInterval);
}
