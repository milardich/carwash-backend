package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.ResourcesUsageResponseDto;

import java.time.LocalDateTime;

public interface ResourceService {
    ResourcesUsageResponseDto getStationResourcesUsage(
            Long stationId,
            LocalDateTime dateTimeFrom,
            LocalDateTime dateTimeTo
    );
}
