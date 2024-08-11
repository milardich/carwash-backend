package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.WashCycle;
import com.sm.carwashmonitor.repository.ResourceRepository;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.WashCycleRepository;
import com.sm.carwashmonitor.service.impl.ChartDataServiceImpl;
import com.sm.carwashmonitor.validation.DateTimeValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ChartDataServiceTests {

    @Mock
    private WashCycleRepository washCycleRepository;

    @Mock
    private StationRepository stationRepository;

    @Mock
    private DateTimeValidation dateTimeValidation;

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ChartDataServiceImpl resourceService;

    // test objects
    Station station;
    Unit unit;
    WashCycle washCycle;
    List<Unit> units;
    List<WashCycle> washCycles;
    ResourceChartDataDTO resourceChartDataDTO;
    List<ResourceChartDataDTO> resourceChartDataDTOs;

    @BeforeEach
    public void init() {
        this.station = new Station();
        this.unit = new Unit();
        this.washCycle = new WashCycle();

        this.station.setUnits(new ArrayList<>());
        this.unit.setWashCycles(new ArrayList<>());

        this.units = new ArrayList<>();
        this.washCycles = new ArrayList<>();

        this.resourceChartDataDTO = new ResourceChartDataDTO();
        this.resourceChartDataDTO.setWashCycleDate("2024-01-01 11:11:11");
        this.resourceChartDataDTO.setTotalWaterConsumption(55.0F);
        this.resourceChartDataDTO.setTotalWaxConsumption(55.0F);
        this.resourceChartDataDTO.setTotalDetergentConsumption(55.0F);
        this.resourceChartDataDTOs = new ArrayList<>();
        this.resourceChartDataDTOs.add(this.resourceChartDataDTO);

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
        Mockito.when(resourceRepository.getResourceUsageChartData(Mockito.any(), Mockito.any())).thenReturn(this.resourceChartDataDTOs);

        List<ResourceChartDataDTO> actualResponse = resourceService.getResourceUsageChartData(55L, "30 days");

        Float expectedConsumption = this.resourceChartDataDTOs.get(0).getTotalWaxConsumption();
        Float actualConsumption = actualResponse.get(0).getTotalWaxConsumption();

        Assertions.assertEquals(expectedConsumption, actualConsumption);
    }
}
