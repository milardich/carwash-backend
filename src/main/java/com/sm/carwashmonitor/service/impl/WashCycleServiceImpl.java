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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        washCycleResponseDto.setStationId(stationId);
        return washCycleResponseDto;
    }

    @Override
    public WashCycleResponseDto getWashCycle(Long stationId, Long unitId, Long washCycleId) {
        // TODO: validate
        Optional<Station> station = stationRepository.findById(stationId);
        if(station.isEmpty()) {
            throw new EntityNotFoundException("Station not found");
        }
        Optional<Unit> unit = unitRepository.findById(unitId);
        if(unit.isEmpty()) {
            throw new EntityNotFoundException("Unit not found");
        }
        if(!station.get().getUnits().contains(unit.get())) {
            throw new EntityNotFoundException("Station does not contain unit with id " + unitId);
        }
        Optional<WashCycle> washCycle = washCycleRepository.findById(washCycleId);
        if(washCycle.isEmpty()) {
            throw new EntityNotFoundException("Wash cycle not found");
        }
        if(!unit.get().getWashCycles().contains(washCycle.get())) {
            throw new EntityNotFoundException("Unit does not contain wash cycle with id " + washCycleId);
        }
        WashCycleResponseDto washCycleResponseDto = washCycleMapper.toDto(washCycle.get());
        washCycleResponseDto.setWashCycleDate(washCycle.get().getWashCycleDate().toString());
        washCycleResponseDto.setStationId(stationId);
        return washCycleResponseDto;
    }

    @Override
    public List<WashCycleResponseDto> getFilteredWashCycles(Long stationId, Long unitId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        // TODO: validate
        Optional<Station> station = stationRepository.findById(stationId);
        if(station.isEmpty()) {
            throw new EntityNotFoundException("Station not found");
        }
        Optional<Unit> unit = unitRepository.findById(unitId);
        if(unit.isEmpty()) {
            throw new EntityNotFoundException("Unit not found");
        }
        if(!station.get().getUnits().contains(unit.get())) {
            throw new EntityNotFoundException("Station does not contain unit with id " + unitId);
        }
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
