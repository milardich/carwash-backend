package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.UnitInfoDTO;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.mapper.UnitMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.enumeration.UnitStatus;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.UnitRepository;
import com.sm.carwashmonitor.service.impl.UnitServiceImpl;
import com.sm.carwashmonitor.validation.StationValidation;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTests {

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private StationRepository stationRepository;

    @Spy
    private StationMapper stationMapper;

    @Spy
    private StationValidation stationValidation;

    @Spy
    private UnitMapper unitMapper;

    @InjectMocks
    private UnitServiceImpl unitService;

    // test objects
    Station station;
    Optional<Station> optionalStation;
    StationDTO stationDTO;
    Unit unit;
    String testString = "test";
    Long testLong = 55L;
    Float testFloat = 0.00F;

    @BeforeEach
    public void init() {
        this.station = new Station();
        this.station.setStationId(this.testLong);
        this.station.setStationName(this.testString);
        this.station.setCity(this.testString);
        this.station.setCountry(this.testString);
        this.station.setStreetName(this.testString);
        this.station.setUnits(new ArrayList<>());

        this.optionalStation = Optional.of(this.station);

        this.unit = new Unit();
        this.unit.setStatus(UnitStatus.INACTIVE.name());
        this.unit.setStation(this.station);

        this.stationDTO = new StationDTO();
        this.stationDTO.setStationId(this.testLong);
        this.stationDTO.setStationName(this.testString);
        this.stationDTO.setCity(this.testString);
        this.stationDTO.setCountry(this.testString);
        this.stationDTO.setStreetName(this.testString);
        this.stationDTO.setUnits(new ArrayList<>());
    }

    @Test
    void testCreateUnitReturnStationResponseDTO() throws Exception {
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(this.optionalStation);
        Mockito.when(unitRepository.save(Mockito.any())).thenReturn(this.unit);
        Mockito.when(stationMapper.toDto(Mockito.any())).thenReturn(this.stationDTO);

        StationDTO actualResponse = unitService.createUnit(55L);

        Assertions.assertEquals(this.stationDTO.getStationId(), actualResponse.getStationId());
    }

    @Test
    void testCreateUnitThrowException() throws Exception {
        Optional<Station> emptyOptionalStation = Optional.empty();
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(emptyOptionalStation);

        EntityNotFoundException thrownException = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> unitService.createUnit(55L)
        );

        Assertions.assertEquals("Station not found", thrownException.getMessage());
    }

    @Test
    void testGetUnitInfo() throws Exception {
        UnitInfoDTO unitInfoDTO = new UnitInfoDTO();
        unitInfoDTO.setTotalDetergentConsumption(1.0F);
        unitInfoDTO.setTotalWaterConsumption(1.0F);
        unitInfoDTO.setTotalWaxConsumption(1.0F);
        unitInfoDTO.setTotalCoinAmount(1);
        unitInfoDTO.setWashCycleCount(1);

        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.of(this.station));
        Mockito.when(unitRepository.findById(Mockito.any())).thenReturn(Optional.of(this.unit));
        Mockito.doNothing().when(stationValidation).validateStationContainsUnit(this.station, this.unit);
        Mockito.when(unitRepository.getUnitInfo(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(unitInfoDTO);

        UnitInfoDTO actual = unitService.getUnitInfo(1L, 1L, "2024-01-01T00:00:00", "2024-08-10T23:59:59", "Europe/Zagreb");

        Assertions.assertEquals(unitInfoDTO.getWashCycleCount(), actual.getWashCycleCount());
    }
}
