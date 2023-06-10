package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;

public interface StationService {
    StationResponseDto createStation(StationRequestDto stationRequestDto);
}
