package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;

import java.util.List;

public interface StationService {
    StationResponseDto createStation(StationRequestDto stationRequestDto);
    StationResponseDto getStation(Long stationId);
    List<StationResponseDto> getAllStations();
}
