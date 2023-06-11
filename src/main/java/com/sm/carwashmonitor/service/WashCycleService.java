package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.WashCycleRequestDto;
import com.sm.carwashmonitor.dto.WashCycleResponseDto;

public interface WashCycleService {
    WashCycleResponseDto createNewWashCycle(Long stationId, Long unitId, WashCycleRequestDto washCycleRequestDto);
    WashCycleResponseDto getWashCycle(Long stationId, Long unitId, Long washCycleId);
}
