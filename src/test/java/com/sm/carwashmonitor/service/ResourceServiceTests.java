package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.ResourceConsumptionDto;
import com.sm.carwashmonitor.dto.ResourceUsageChartDataDTO;
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
        this.resourceUsageChartDataDTO.setWashCycleDate("2024-01-01 11:11:11");
        this.resourceUsageChartDataDTO.setTotalWaterConsumption(55.0F);
        this.resourceUsageChartDataDTO.setTotalWaxConsumption(55.0F);
        this.resourceUsageChartDataDTO.setTotalDetergentConsumption(55.0F);
        this.resourceUsageChartDataDTOs = new ArrayList<>();
        this.resourceUsageChartDataDTOs.add(this.resourceUsageChartDataDTO);

        this.resourceConsumptionDto = new ResourceConsumptionDto();
        this.resourceConsumptionDto.setConsumption(1.0F);
        this.resourceConsumptionDto.setDateTime(LocalDateTime.now());

        this.washCycle.setDetergentConsumption(1.00F);
        this.washCycle.setWaxConsumption(1.00F);
        this.washCycle.setWaterConsumption(1.00F);

        this.station.getUnits().add(this.unit);
        this.unit.getWashCycles().add(this.washCycle);
        this.units.add(this.unit);
        this.washCycles.add(this.washCycle);
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
