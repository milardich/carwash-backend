package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationResponseDto;

public interface UnitService {
    StationResponseDto createUnit(Long stationId);
}
