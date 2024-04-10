package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.ResourceConsumptionDto;
import com.sm.carwashmonitor.dto.ResourcesUsageResponseDto;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.WashCycle;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.WashCycleRepository;
import com.sm.carwashmonitor.service.ResourceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

        washCycles.sort(Comparator.comparing(WashCycle::getWashCycleDate));

        ResourcesUsageResponseDto resourcesUsageResponseDto = new ResourcesUsageResponseDto();
        fillResourcesUsageResponseDto(resourcesUsageResponseDto, washCycles);
        return resourcesUsageResponseDto;
    }

    private void fillResourcesUsageResponseDto(ResourcesUsageResponseDto resourcesUsageResponseDto, List<WashCycle> washCycles) {
        washCycles.forEach(washCycle -> {
            fillResourceConsumptions(resourcesUsageResponseDto, washCycle);
            calculateTotalResourceConsumptions(resourcesUsageResponseDto, washCycle);
        });
    }

    private void fillResourceConsumptions(ResourcesUsageResponseDto resourcesUsageResponseDto, WashCycle washCycle) {
        ResourceConsumptionDto waterConsumptionDto = new ResourceConsumptionDto();
        waterConsumptionDto.setDateTime(washCycle.getWashCycleDate());
        waterConsumptionDto.setConsumption(washCycle.getWaterConsumption());
        resourcesUsageResponseDto.getWaterConsumptions().add(waterConsumptionDto);

        ResourceConsumptionDto detergentConsumptionDto = new ResourceConsumptionDto();
        detergentConsumptionDto.setDateTime(washCycle.getWashCycleDate());
        detergentConsumptionDto.setConsumption(washCycle.getDetergentConsumption());
        resourcesUsageResponseDto.getDetergentConsumptions().add(detergentConsumptionDto);

        ResourceConsumptionDto waxConsumptionDto = new ResourceConsumptionDto();
        waxConsumptionDto.setDateTime(washCycle.getWashCycleDate());
        waxConsumptionDto.setConsumption(washCycle.getWaxConsumption());
        resourcesUsageResponseDto.getWaxConsumptions().add(waxConsumptionDto);
    }

    private void calculateTotalResourceConsumptions(ResourcesUsageResponseDto resourcesUsageResponseDto, WashCycle washCycle) {
        Float totalWaterUsage = 0.0F;
        Float totalDetergentUsage = 0.0F;
        Float totalWaxUsage = 0.0F;

        totalWaterUsage = resourcesUsageResponseDto.getTotalWaterConsumption() + washCycle.getWaterConsumption();
        resourcesUsageResponseDto.setTotalWaterConsumption(totalWaterUsage);

        totalDetergentUsage = resourcesUsageResponseDto.getTotalDetergentConsumption() + washCycle.getDetergentConsumption();
        resourcesUsageResponseDto.setTotalDetergentConsumption(totalDetergentUsage);

        totalWaxUsage = resourcesUsageResponseDto.getTotalWaxConsumption() + washCycle.getWaxConsumption();
        resourcesUsageResponseDto.setTotalWaxConsumption(totalWaxUsage);
    }
}
