package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.mapper.UnitMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.enumeration.UnitStatus;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.UnitRepository;
import com.sm.carwashmonitor.service.impl.UnitServiceImpl;
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
import java.util.NoSuchElementException;
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
    private UnitMapper unitMapper;

    @InjectMocks
    private UnitServiceImpl unitService;

    // test objects
    Station station;
    Optional<Station> optionalStation;
    StationResponseDto stationResponseDto;
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
        this.unit.setCoinTrayAmount(0);
        this.unit.setTotalWaterConsumption(this.testFloat);
        this.unit.setTotalDetergentConsumption(this.testFloat);
        this.unit.setTotalWaxConsumption(this.testFloat);
        this.unit.setStation(this.station);

        this.stationResponseDto = new StationResponseDto();
        this.stationResponseDto.setStationId(this.testLong);
        this.stationResponseDto.setStationName(this.testString);
        this.stationResponseDto.setCity(this.testString);
        this.stationResponseDto.setCountry(this.testString);
        this.stationResponseDto.setStreetName(this.testString);
        this.stationResponseDto.setUnits(new ArrayList<>());
    }

    @Test
    void testCreateUnitReturnStationResponseDTO() throws Exception {
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(this.optionalStation);
        Mockito.when(unitRepository.save(Mockito.any())).thenReturn(this.unit);
        Mockito.when(stationMapper.toDto(Mockito.any())).thenReturn(this.stationResponseDto);

        StationResponseDto actualResponse = unitService.createUnit(55L);

        Assertions.assertEquals(this.stationResponseDto.getStationId(), actualResponse.getStationId());
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
}
