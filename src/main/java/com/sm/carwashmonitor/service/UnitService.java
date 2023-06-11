package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.dto.UnitDto;
import com.sm.carwashmonitor.dto.UnitStatusDto;
import com.sm.carwashmonitor.model.enumeration.UnitStatus;

public interface UnitService {
    StationResponseDto createUnit(Long stationId);
    UnitDto updateUnitStatus(Long stationId, Long unitId, UnitStatusDto unitStatusDto);
}
