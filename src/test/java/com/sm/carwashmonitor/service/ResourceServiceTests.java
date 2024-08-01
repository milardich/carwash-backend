package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.ResourceConsumptionDto;
import com.sm.carwashmonitor.dto.ResourceUsageChartDataDTO;
import com.sm.carwashmonitor.dto.ResourcesUsageResponseDto;
import com.sm.carwashmonitor.dto.WashCycleRequestDto;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.WashCycle;
import com.sm.carwashmonitor.repository.ResourceRepository;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.WashCycleRepository;
import com.sm.carwashmonitor.service.impl.ResourceServiceImpl;
import com.sm.carwashmonitor.validation.DateTimeValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceTests {

    @Mock
    private WashCycleRepository washCycleRepository;

    @Mock
    private StationRepository stationRepository;

    @Mock
    private DateTimeValidation dateTimeValidation;

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceServiceImpl resourceService;

    // test objects
    Station station;
    Unit unit;
    WashCycle washCycle;
    List<Unit> units;
    List<WashCycle> washCycles;
    ResourcesUsageResponseDto resourcesUsageResponseDto;
    ResourceConsumptionDto resourceConsumptionDto;
    ResourceUsageChartDataDTO resourceUsageChartDataDTO;
    List<ResourceUsageChartDataDTO> resourceUsageChartDataDTOs;

    @BeforeEach
    public void init() {
        this.station = new Station();
        this.unit = new Unit();
        this.washCycle = new WashCycle();

        this.station.setUnits(new ArrayList<>());
        this.unit.setWashCycles(new ArrayList<>());

        this.units = new ArrayList<>();
        this.washCycles = new ArrayList<>();

        this.resourceUsageChartDataDTO = new ResourceUsageChartDataDTO();
        this.resourceUsageChartDataDTOs = new ArrayList<>();
        this.resourceUsageChartDataDTOs.add(this.resourceUsageChartDataDTO);

        this.resourceConsumptionDto = new ResourceConsumptionDto();
        this.resourceConsumptionDto.setConsumption(1.0F);
        this.resourceConsumptionDto.setDateTime(LocalDateTime.now());

        this.resourcesUsageResponseDto = new ResourcesUsageResponseDto();
        this.resourcesUsageResponseDto.setDetergentConsumptions(new ArrayList<>());
        this.resourcesUsageResponseDto.setWaterConsumptions(new ArrayList<>());
        this.resourcesUsageResponseDto.setWaxConsumptions(new ArrayList<>());
        this.resourcesUsageResponseDto.setTotalDetergentConsumption(1.00F);
        this.resourcesUsageResponseDto.setTotalWaxConsumption(1.00F);
        this.resourcesUsageResponseDto.setTotalWaterConsumption(1.00F);

        this.washCycle.setDetergentConsumption(1.00F);
        this.washCycle.setWaxConsumption(1.00F);
        this.washCycle.setWaterConsumption(1.00F);

        this.station.getUnits().add(this.unit);
        this.unit.getWashCycles().add(this.washCycle);
        this.units.add(this.unit);
        this.washCycles.add(this.washCycle);
        this.resourcesUsageResponseDto.getDetergentConsumptions().add(this.resourceConsumptionDto);
    }

    @Test
    void testGetStationResourcesUsageReturnDTO() throws Exception {
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.of(this.station));
        Mockito.when(washCycleRepository.getFilteredWashCyclesByUnitId(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(washCycles);

        ResourcesUsageResponseDto actual = resourceService.getStationResourcesUsage(55L, LocalDateTime.now(), LocalDateTime.now());

        Float expectedUsage = this.resourcesUsageResponseDto.getDetergentConsumptions().get(0).getConsumption();
        Float actualUsage = actual.getDetergentConsumptions().get(0).getConsumption();

        Assertions.assertEquals(expectedUsage, actualUsage);
    }

    @Test
    void testGetResourceUsageChartDataReturnDTOList() throws Exception {
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.of(this.station));
        Mockito.doNothing().when(dateTimeValidation).validate(Mockito.any());
        Mockito.when(resourceRepository.getResourceUsageChartData(Mockito.any(), Mockito.any())).thenReturn(this.resourceUsageChartDataDTOs);

        List<ResourceUsageChartDataDTO> actualResponse = resourceService.getResourceUsageChartData(55L, "30 days");

        Float expectedConsumption = this.resourceUsageChartDataDTOs.get(0).getTotalWaxConsumption();
        Float actualConsumption = actualResponse.get(0).getTotalWaxConsumption();

        Assertions.assertEquals(expectedConsumption, actualConsumption);
    }
}
