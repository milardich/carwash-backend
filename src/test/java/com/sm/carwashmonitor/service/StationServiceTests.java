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

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStationReturnStationDTO() throws Exception {
        StationRequestDto stationRequestDto = fillStationDTO();
        StationResponseDto expected = fillStationResponseDto();
        Station station = fillStation();

//        Mockito.doNothing().when(stationValidation).validate(Mockito.any(StationRequestDto.class));
        Mockito.when(stationMapper.toEntity(Mockito.any(StationRequestDto.class))).thenReturn(station);
//        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(station);
        Mockito.when(stationMapper.toDto(Mockito.any(Station.class))).thenReturn(expected);

        StationResponseDto actual = stationService.createStation(stationRequestDto);

        Assertions.assertEquals(expected.getCity(), actual.getCity());
    }

    private StationRequestDto fillStationDTO() {
        StationRequestDto stationRequestDto = new StationRequestDto();
        stationRequestDto.setStationName("TestName");
        stationRequestDto.setCountry("testCountry");
        stationRequestDto.setCity("asd");
        stationRequestDto.setStreetName("asdads");
        stationRequestDto.setStreetNumber("12A");
        return stationRequestDto;
    }

    private StationResponseDto fillStationResponseDto() {
        StationResponseDto stationResponseDto = new StationResponseDto();
        stationResponseDto.setStationName("TestName");
        stationResponseDto.setCountry("testCountry");
        stationResponseDto.setCity("asd");
        stationResponseDto.setStreetName("asdads");
        stationResponseDto.setStreetNumber("12A");
        return stationResponseDto;
    }

    private Station fillStation() {
        Station station = new Station();
        station.setStationName("TestName");
        station.setCountry("testCountry");
        station.setCity("asd");
        station.setStreetName("asdads");
        station.setStreetNumber("12A");
        return station;
    }
}
