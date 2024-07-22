package com.sm.carwashmonitor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.service.StationService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class StationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StationService stationService;

    @Test
    void createStationReturn200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        StationResponseDto stationResponseDto = new StationResponseDto();
        StationRequestDto stationRequestDto = new StationRequestDto();
        fillStationRequestDto(stationRequestDto);

        Mockito.when(stationService.createStation(stationRequestDto)).thenReturn(stationResponseDto);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/station")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(stationRequestDto))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getStationByIdReturn200() throws Exception {
        StationResponseDto stationResponseDto = new StationResponseDto();

        Mockito.when(stationService.getStation(1L)).thenReturn(stationResponseDto);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/station/1")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getStationByIdReturn404() throws Exception {
        Mockito.when(stationService.getStation(9999L))
                .thenThrow(new EntityNotFoundException("Station not found."));

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/station/9999")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getAllStationsReturn200() throws Exception {
        List<StationResponseDto> stations = new ArrayList<>();

        Mockito.when(stationService.getAllStations()).thenReturn(stations);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/station")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    void fillStationRequestDto(StationRequestDto stationRequestDto) {
        stationRequestDto.setStationName("test");
        stationRequestDto.setCity("test");
        stationRequestDto.setStreetName("asd");
        stationRequestDto.setStreetNumber("15");
        stationRequestDto.setCountry("test");
    }
}
