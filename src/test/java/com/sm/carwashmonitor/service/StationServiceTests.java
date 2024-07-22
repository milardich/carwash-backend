package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.service.impl.StationServiceImpl;
import com.sm.carwashmonitor.validation.StationValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class StationServiceTests {

    @Mock
    private StationRepository stationRepository;

    @Spy
    private StationMapper stationMapper;

    @Mock
    private StationValidation stationValidation;

    @InjectMocks
    private StationServiceImpl stationService;

    // test objects
    private StationRequestDto stationRequestDto;
    private StationResponseDto stationResponseDto;
    private Station station;
    private String testString;
    private Long testLong;

    @BeforeEach
    public void init() {
        this.stationRequestDto = new StationRequestDto();
        this.stationResponseDto = new StationResponseDto();
        this.station = new Station();
        fillStationRequestDTO(this.stationRequestDto);
        fillStationResponseDto(this.stationResponseDto);
        fillStation(this.station);
        this.testString = "test";
        this.testLong = 55L;
    }

    @Test
    void testCreateStationReturnStationDTO() throws Exception {
        Mockito.doNothing().when(stationValidation).validate(Mockito.any());
        Mockito.when(stationMapper.toEntity(Mockito.any())).thenReturn(this.station);
        Mockito.when(stationRepository.save(Mockito.any())).thenReturn(this.station);
        Mockito.doReturn(stationResponseDto).when(stationMapper).toDto(Mockito.any());

        StationResponseDto actualResponse = stationService.createStation(stationRequestDto);

        Assertions.assertEquals(stationResponseDto.getCity(), actualResponse.getCity());
        Mockito.verify(stationRepository).save(Mockito.any(Station.class));
    }

    private void fillStationRequestDTO(StationRequestDto stationRequestDto) {
        stationRequestDto.setStationName(this.testString);
        stationRequestDto.setCountry(this.testString);
        stationRequestDto.setCity(this.testString);
        stationRequestDto.setStreetName(this.testString);
        stationRequestDto.setStreetNumber(this.testString);
    }

    private void fillStationResponseDto(StationResponseDto stationResponseDto) {
        stationResponseDto.setStationId(this.testLong);
        stationResponseDto.setStationName(this.testString);
        stationResponseDto.setCountry(this.testString);
        stationResponseDto.setCity(this.testString);
        stationResponseDto.setStreetName(this.testString);
        stationResponseDto.setStreetNumber(this.testString);
        stationResponseDto.setUnits(null);
    }

    private void fillStation(Station station) {
        station.setStationId(this.testLong);
        station.setStationName(this.testString);
        station.setCountry(this.testString);
        station.setCity(this.testString);
        station.setStreetName(this.testString);
        station.setStreetNumber(this.testString);
        station.setUnits(null);
    }
}
