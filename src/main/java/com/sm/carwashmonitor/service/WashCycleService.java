package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.WashCycleRequestDto;
import com.sm.carwashmonitor.dto.WashCycleResponseDto;
import com.sm.carwashmonitor.model.WashCycle;

import java.time.LocalDateTime;
import java.util.List;

public interface WashCycleService {
    WashCycleResponseDto createNewWashCycle(Long stationId, Long unitId, WashCycleRequestDto washCycleRequestDto);
    WashCycleResponseDto getWashCycle(Long stationId, Long unitId, Long washCycleId);
    List<WashCycleResponseDto> getFilteredWashCycles(Long stationId, Long unitId, LocalDateTime dateFrom, LocalDateTime dateTo);
}
