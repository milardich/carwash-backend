package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.enumeration.UnitStatus;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.UnitRepository;
import com.sm.carwashmonitor.service.UnitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

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

    private void setDefaultUnitValues(Unit unit) {
        unit.setStatus(UnitStatus.INACTIVE.name());
        unit.setCoinTrayAmount(0);
        unit.setTotalWaterConsumption(0.00F);
        unit.setTotalDetergentConsumption(0.00F);
        unit.setTotalWaxConsumption(0.00F);
    }
}
