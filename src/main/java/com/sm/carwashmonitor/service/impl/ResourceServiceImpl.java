package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.ResourcesUsageResponseDto;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.WashCycle;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.WashCycleRepository;
import com.sm.carwashmonitor.service.ResourceService;
import com.sm.carwashmonitor.service.WashCycleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final WashCycleRepository washCycleRepository;
    private final StationRepository stationRepository;

    @Override
    public ResourcesUsageResponseDto getStationResourcesUsage(Long stationId, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {

        Optional<Station> station = stationRepository.findById(stationId);
        if(station.isEmpty()) {
            throw new EntityNotFoundException("Station not found.");
        }
        List<Unit> units = station.get().getUnits();
        List<WashCycle> washCycles = new ArrayList<>();
        units.forEach(unit -> {
            washCycles.addAll(washCycleRepository.getFilteredWashCyclesByUnitId(unit.getUnitId(), dateTimeFrom, dateTimeTo));
        });

        return fillResourcesUsageDto(washCycles);
    }

    private ResourcesUsageResponseDto fillResourcesUsageDto(List<WashCycle> washCycles) {
        ResourcesUsageResponseDto resourcesUsageResponseDto = new ResourcesUsageResponseDto();
        resourcesUsageResponseDto.setWaterUsage(new HashMap<>());
        resourcesUsageResponseDto.setWaxUsage(new HashMap<>());
        resourcesUsageResponseDto.setDetergentUsage(new HashMap<>());
        washCycles.forEach(washCycle -> {
            resourcesUsageResponseDto.getWaterUsage().put(washCycle.getWashCycleDate(), washCycle.getWaterConsumption());
            resourcesUsageResponseDto.getDetergentUsage().put(washCycle.getWashCycleDate(), washCycle.getDetergentConsumption());
            resourcesUsageResponseDto.getWaxUsage().put(washCycle.getWashCycleDate(), washCycle.getWaxConsumption());
        });
        return  resourcesUsageResponseDto;
    }
}
