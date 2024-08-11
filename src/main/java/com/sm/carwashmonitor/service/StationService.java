package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.StationRequestDTO;

import java.util.List;

public interface StationService {
    StationDTO createStation(StationRequestDTO stationRequestDto);
    StationDTO getStation(Long stationId);
    List<StationDTO> getAllStations();
}
