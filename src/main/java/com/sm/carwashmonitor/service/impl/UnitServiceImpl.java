package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.UnitDTO;
import com.sm.carwashmonitor.dto.UnitStatusDTO;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.mapper.UnitMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.enumeration.UnitStatus;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.UnitRepository;
import com.sm.carwashmonitor.service.UnitService;
import com.sm.carwashmonitor.validation.StationValidation;
import com.sm.carwashmonitor.validation.UnitValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final UnitMapper unitMapper;
    private final UnitValidation unitValidation;
    private final StationValidation stationValidation;

    @Override
    public StationDTO createUnit(Long stationId) {
        Unit unit = new Unit();
        Station station = stationRepository.findById(stationId).orElseThrow(()
                -> new EntityNotFoundException("Station not found"));
        unit.setStation(station);
        setDefaultUnitValues(unit);
        unitRepository.save(unit);
        return stationMapper.toDto(station);
    }

    @Override
    public UnitDTO updateUnitStatus(Long stationId, Long unitId, UnitStatusDTO unitStatusDto) {
        Station station = stationRepository.findById(stationId).orElseThrow(
                () -> new EntityNotFoundException("Station not found"));

        Unit unit = unitRepository.findById(unitId).orElseThrow(
                () -> new EntityNotFoundException("Unit not found"));

        stationValidation.validateStationContainsUnit(station, unit);
        unitValidation.validateUnitStatus(unitStatusDto.getStatus());
        unit.setStatus(unitStatusDto.getStatus());
        unitRepository.save(unit);

        return unitMapper.toDto(unit);
    }

    @Override
    public UnitDTO getUnit(Long stationId, Long unitId) {
        Unit unit = unitRepository.findById(unitId).orElseThrow(
                () -> new EntityNotFoundException("Unit not found"));

        return unitMapper.toDto(unit);
    }

    private void setDefaultUnitValues(Unit unit) {
        unit.setStatus(UnitStatus.INACTIVE.name());
        unit.setCoinTrayAmount(0);
    }
}
