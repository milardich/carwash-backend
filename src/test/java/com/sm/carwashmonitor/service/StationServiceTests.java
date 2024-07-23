package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.exception.GenericValidationException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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
    private final String testString = "test";
    private final Long testLong = 55L;

    @BeforeEach
    public void init() {
        this.stationRequestDto = new StationRequestDto();
        this.stationResponseDto = new StationResponseDto();
        this.station = new Station();
        fillStationRequestDTO(this.stationRequestDto);
        fillStationResponseDto(this.stationResponseDto);
        fillStation(this.station);
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

    @Test
    void testCreateInvalidStationThrowException() throws Exception {
        stationRequestDto.setStationName("");

        Mockito.doThrow(new GenericValidationException("Validation failed"))
                .when(stationValidation).validate(stationRequestDto);

        GenericValidationException thrownException = Assertions.assertThrows(
            GenericValidationException.class,
                () -> stationService.createStation(stationRequestDto)
        );

        Assertions.assertEquals("Validation failed", thrownException.getMessage());
    }

    @Test
    void testGetStationReturnStationResponseDTO() throws Exception {
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.of(this.station));
        Mockito.when(stationMapper.toDto(Mockito.any())).thenReturn(this.stationResponseDto);

        StationResponseDto actualResponse = stationService.getStation(this.testLong);

        Assertions.assertEquals(this.testLong, actualResponse.getStationId());
    }

    @Test
    void testGetStationThrowException() throws Exception {
        Mockito.when(stationRepository.findById(Mockito.any())).thenThrow(new NoSuchElementException("No such element"));

        NoSuchElementException thrownException = Assertions.assertThrows(
            NoSuchElementException.class,
                () -> stationRepository.findById(Mockito.any())
        );

        Assertions.assertEquals("No such element", thrownException.getMessage());
    }

    @Test
    void testGetAllStationsReturnListOfStationResponseDTOs() throws Exception {
        List<Station> stations = new ArrayList<>();
        Station station1 = this.station;
        Station station2 = this.station;
        stations.add(station1);
        stations.add(station2);

        List<StationResponseDto> stationResponseDtos = new ArrayList<>();
        StationResponseDto stationResponseDto1 = this.stationResponseDto;
        StationResponseDto stationResponseDto2 = this.stationResponseDto;
        stationResponseDtos.add(stationResponseDto1);
        stationResponseDtos.add(stationResponseDto2);

        Mockito.when(stationRepository.findAll()).thenReturn(stations);
        Mockito.when(stationMapper.toDto(stations.get(0))).thenReturn(stationResponseDtos.get(0));
        Mockito.when(stationMapper.toDto(stations.get(1))).thenReturn(stationResponseDtos.get(1));

        List<StationResponseDto> actualResponse = stationService.getAllStations();

        Assertions.assertEquals(stationResponseDtos.get(1), actualResponse.get(1));
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
