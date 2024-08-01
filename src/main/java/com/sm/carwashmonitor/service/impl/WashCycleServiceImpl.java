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
import com.sm.carwashmonitor.validation.StationValidation;
import com.sm.carwashmonitor.validation.UnitValidation;
import com.sm.carwashmonitor.validation.WashCycleValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WashCycleServiceImpl implements WashCycleService {

    private final WashCycleRepository washCycleRepository;
    private final WashCycleMapper washCycleMapper;
    private final UnitRepository unitRepository;
    private final StationRepository stationRepository;
    private final StationValidation stationValidation;
    private final UnitValidation unitValidation;
    private final WashCycleValidation washCycleValidation;

    @Override
    public WashCycleResponseDto createNewWashCycle(Long stationId, Long unitId, WashCycleRequestDto washCycleRequestDto) {
        washCycleValidation.validateWashCycleRequest(washCycleRequestDto);

        Station station = stationRepository.findById(stationId).orElseThrow(
                () -> new EntityNotFoundException("Station not found"));

        Unit unit = unitRepository.findById(unitId).orElseThrow(
                () -> new EntityNotFoundException("Unit not found"));

        stationValidation.validateStationContainsUnit(station, unit);
        WashCycle washCycle = washCycleMapper.toEntity(washCycleRequestDto);
        washCycle.setUnit(unit);
        washCycle.setWashCycleDate(LocalDateTime.now());
        washCycleRepository.save(washCycle);
        WashCycleResponseDto washCycleResponseDto = washCycleMapper.toDto(washCycle);
        washCycleResponseDto.setStationId(stationId);
        return washCycleResponseDto;
    }

    @Override
    public WashCycleResponseDto getWashCycle(Long stationId, Long unitId, Long washCycleId) {
        Station station = stationRepository.findById(stationId).orElseThrow(
                () -> new EntityNotFoundException("Station not found"));

        Unit unit = unitRepository.findById(unitId).orElseThrow(
                () -> new EntityNotFoundException("Unit not found"));

        WashCycle washCycle = washCycleRepository.findById(washCycleId).orElseThrow(
                () -> new EntityNotFoundException("WashCycle not found"));

        stationValidation.validateStationContainsUnit(station, unit);
        unitValidation.validateUnitCointainsWashCycle(unit, washCycle);
        WashCycleResponseDto washCycleResponseDto = washCycleMapper.toDto(washCycle);
        washCycleResponseDto.setWashCycleDate(washCycle.getWashCycleDate().toString());
        washCycleResponseDto.setStationId(stationId);
        return washCycleResponseDto;
    }

    @Override
    public List<WashCycleResponseDto> getFilteredWashCycles(Long stationId, Long unitId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        Station station = stationRepository.findById(stationId).orElseThrow(
                () -> new EntityNotFoundException("Station not found"));

        Unit unit = unitRepository.findById(unitId).orElseThrow(
                () -> new EntityNotFoundException("Unit not found"));

        stationValidation.validateStationContainsUnit(station, unit);

        List<WashCycle> filteredWashCycles = washCycleRepository.getFilteredWashCyclesByUnitId(unitId, dateFrom, dateTo);
        List<WashCycleResponseDto> filteredWashCyclesResponse = new ArrayList<>();
        filteredWashCycles.forEach(washCycle -> {
            WashCycleResponseDto washCycleResponseDto = washCycleMapper.toDto(washCycle);
            washCycleResponseDto.setStationId(stationId);
            filteredWashCyclesResponse.add(washCycleResponseDto);
        });
        return filteredWashCyclesResponse;
    }
}
