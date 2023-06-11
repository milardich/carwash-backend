package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.WashCycleRequestDto;
import com.sm.carwashmonitor.dto.WashCycleResponseDto;
import com.sm.carwashmonitor.mapper.WashCycleMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.WashCycle;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.UnitRepository;
import com.sm.carwashmonitor.repository.WashCycleRepository;
import com.sm.carwashmonitor.service.WashCycleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WashCycleServiceImpl implements WashCycleService {

    private final WashCycleRepository washCycleRepository;
    private final WashCycleMapper washCycleMapper;
    private final UnitRepository unitRepository;
    private final StationRepository stationRepository;

    @Override
    public WashCycleResponseDto createNewWashCycle(Long stationId, Long unitId, WashCycleRequestDto washCycleRequestDto) {
        Optional<Station> station = stationRepository.findById(stationId);
        if(station.isEmpty()) {
            throw new EntityNotFoundException("Station not found");
        }
        WashCycle washCycle = washCycleMapper.toEntity(washCycleRequestDto);
        Optional<Unit> unit = unitRepository.findById(unitId);
        if(unit.isEmpty()) {
            throw new EntityNotFoundException("Unit not found");
        }
        // TODO: validate if unit is in station
        washCycle.setUnit(unit.get());
        washCycle.setWashCycleDate(LocalDateTime.now());
        washCycleRepository.save(washCycle);
        WashCycleResponseDto washCycleResponseDto = washCycleMapper.toDto(washCycle);
        washCycleResponseDto.setUnitId(unit.get().getUnitId());
        return washCycleResponseDto;
    }
}
