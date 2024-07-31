package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.GroupedResourceUsageDTO;
import com.sm.carwashmonitor.dto.ResourcesUsageResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ResourceService {
    ResourcesUsageResponseDto getStationResourcesUsage(
            Long stationId,
            LocalDateTime dateTimeFrom,
            LocalDateTime dateTimeTo
    );

    List<GroupedResourceUsageDTO> getGroupedResourcesUsage();
}
