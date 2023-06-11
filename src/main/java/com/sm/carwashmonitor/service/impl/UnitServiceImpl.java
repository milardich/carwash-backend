package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.dto.UnitDto;
import com.sm.carwashmonitor.dto.UnitStatusDto;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.mapper.UnitMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.enumeration.UnitStatus;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.UnitRepository;
import com.sm.carwashmonitor.service.UnitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final UnitMapper unitMapper;

    @Override
    public StationResponseDto createUnit(Long stationId) {
        Unit unit = new Unit();
        Optional<Station> station = stationRepository.findById(stationId);
        if(station.isEmpty()) {
            throw new EntityNotFoundException("Station not found");
        }
        unit.setStation(station.get());
        setDefaultUnitValues(unit);
        unitRepository.save(unit);
        return stationMapper.toDto(station.get());
    }

    @Override
    public UnitDto updateUnitStatus(Long stationId, Long unitId, UnitStatusDto unitStatusDto) {
        Optional<Station> station = stationRepository.findById(stationId);
        if(station.isEmpty()) {
            throw new EntityNotFoundException("Station not found");
        }
        Optional<Unit> unit = unitRepository.findById(unitId);
        if(unit.isEmpty()) {
            throw new EntityNotFoundException("Unit not found");
        }
        if(!station.get().getUnits().contains(unit.get())) {
            throw new EntityNotFoundException("Station " + stationId + "does not contain unit " + unitId);
        }
        if(!unitStatusExists(unitStatusDto.getStatus())) {
            throw new EntityNotFoundException("Status of type '" + unitStatusDto.getStatus() + "' does not exist");
        }
        unit.get().setStatus(unitStatusDto.getStatus());
        unitRepository.save(unit.get());
        return unitMapper.toDto(unit.get());
    }

    private void setDefaultUnitValues(Unit unit) {
        unit.setStatus(UnitStatus.INACTIVE.name());
        unit.setCoinTrayAmount(0);
        unit.setTotalWaterConsumption(0.00F);
        unit.setTotalDetergentConsumption(0.00F);
        unit.setTotalWaxConsumption(0.00F);
    }

    private boolean unitStatusExists(String unitStatus){
        for (UnitStatus status : UnitStatus.values()) {
            if (status.name().equals(unitStatus)) {
                return true;
            }
        }
        return false;
    }
}
