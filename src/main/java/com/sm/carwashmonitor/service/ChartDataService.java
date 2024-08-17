package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;
import java.util.List;

public interface ChartDataService {
    List<ResourceChartDataDTO> getResourceUsageChartData(Long stationId, String pgTimeInterval, String timezone);
}
