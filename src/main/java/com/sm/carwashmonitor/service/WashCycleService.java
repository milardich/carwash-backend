package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.WashCycleDTO;
import com.sm.carwashmonitor.dto.WashCycleRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface WashCycleService {
    WashCycleDTO createNewWashCycle(Long stationId, Long unitId, WashCycleRequestDTO washCycleRequestDto);
    WashCycleDTO getWashCycle(Long stationId, Long unitId, Long washCycleId);
    List<WashCycleDTO> getFilteredWashCycles(Long stationId, Long unitId, LocalDateTime dateFrom, LocalDateTime dateTo);
    Integer createDummyWashCycle(Long stationId, Long unitId, WashCycleRequestDTO washCycleRequestDTO, String washCycleDate, Long washCycleId);
}
