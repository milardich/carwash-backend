package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.StationRequestDTO;
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
    private StationRequestDTO stationRequestDto;
    private StationDTO stationDTO;
    private Station station;
    private final String testString = "test";
    private final Long testLong = 55L;

    @BeforeEach
    public void init() {
        this.stationRequestDto = new StationRequestDTO();
        this.stationDTO = new StationDTO();
        this.station = new Station();
        fillStationRequestDTO(this.stationRequestDto);
        fillStationResponseDto(this.stationDTO);
        fillStation(this.station);
    }

    @Test
    void testCreateStationReturnStationDTO() throws Exception {
        Mockito.doNothing().when(stationValidation).validate(Mockito.any());
        Mockito.when(stationMapper.toEntity(Mockito.any())).thenReturn(this.station);
        Mockito.when(stationRepository.save(Mockito.any())).thenReturn(this.station);
        Mockito.doReturn(stationDTO).when(stationMapper).toDto(Mockito.any());

        StationDTO actualResponse = stationService.createStation(stationRequestDto);

        Assertions.assertEquals(stationDTO.getCity(), actualResponse.getCity());
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
        Mockito.when(stationMapper.toDto(Mockito.any())).thenReturn(this.stationDTO);

        StationDTO actualResponse = stationService.getStation(this.testLong);

        Assertions.assertEquals(this.testLong, actualResponse.getStationId());
    }

    @Test
    void testGetStationThrowException() throws Exception {
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        NoSuchElementException thrownException = Assertions.assertThrows(
            NoSuchElementException.class,
            () -> stationService.getStation(Mockito.any())
        );

        Assertions.assertEquals("No value present", thrownException.getMessage());
    }

    @Test
    void testGetAllStationsReturnListOfStationResponseDTOs() throws Exception {
        List<Station> stations = new ArrayList<>();
        Station station1 = this.station;
        Station station2 = this.station;
        stations.add(station1);
        stations.add(station2);

        List<StationDTO> stationDTOs = new ArrayList<>();
        StationDTO stationDTO1 = this.stationDTO;
        StationDTO stationDTO2 = this.stationDTO;
        stationDTOs.add(stationDTO1);
        stationDTOs.add(stationDTO2);

        Mockito.when(stationRepository.findAll()).thenReturn(stations);
        Mockito.when(stationMapper.toDto(stations.get(0))).thenReturn(stationDTOs.get(0));
        Mockito.when(stationMapper.toDto(stations.get(1))).thenReturn(stationDTOs.get(1));

        List<StationDTO> actualResponse = stationService.getAllStations();

        Assertions.assertEquals(stationDTOs.get(1), actualResponse.get(1));
    }

    private void fillStationRequestDTO(StationRequestDTO stationRequestDto) {
        stationRequestDto.setStationName(this.testString);
        stationRequestDto.setCountry(this.testString);
        stationRequestDto.setCity(this.testString);
        stationRequestDto.setStreetName(this.testString);
        stationRequestDto.setStreetNumber(this.testString);
    }

    private void fillStationResponseDto(StationDTO stationDTO) {
        stationDTO.setStationId(this.testLong);
        stationDTO.setStationName(this.testString);
        stationDTO.setCountry(this.testString);
        stationDTO.setCity(this.testString);
        stationDTO.setStreetName(this.testString);
        stationDTO.setStreetNumber(this.testString);
        stationDTO.setUnits(null);
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
