package com.sm.carwashmonitor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.UnitDTO;
import com.sm.carwashmonitor.dto.UnitStatusDTO;
import com.sm.carwashmonitor.service.UnitService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest
@AutoConfigureMockMvc
public class UnitControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UnitService unitService;

    ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateUnitReturn200() throws Exception {
        StationDTO stationDTO = new StationDTO();

        Mockito.when(unitService.createUnit(Mockito.any())).thenReturn(stationDTO);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/station/55/unit")
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createUnitReturn404() throws Exception {
        Mockito.when(unitService.createUnit(Mockito.any())).thenThrow(new EntityNotFoundException("404 not found"));
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/station/55/unit")
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateUnitStatusReturn200() throws Exception {
        UnitDTO unitDto = new UnitDTO();
        UnitStatusDTO unitStatusDto = new UnitStatusDTO();
        unitStatusDto.setStatus("INACTIVE");

        Mockito.when(unitService.updateUnitStatus(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(unitDto);
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/station/55/unit/55/status")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objectMapper.writeValueAsString(unitStatusDto))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateUnitStatusReturn404() throws Exception {
        UnitStatusDTO unitStatusDto = new UnitStatusDTO();
        unitStatusDto.setStatus("asdf");

        Mockito.when(unitService.updateUnitStatus(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(new EntityNotFoundException("Status of type 'asdf' does not exist"));
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/station/55/unit/55/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(unitStatusDto))
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getUnitReturn200() throws Exception {
        UnitDTO unitDto = new UnitDTO();
        Mockito.when(unitService.getUnit(Mockito.any(), Mockito.any())).thenReturn(unitDto);
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/station/55/unit/55")
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUnitReturn404() throws Exception {
        Mockito.when(unitService.getUnit(Mockito.any(), Mockito.any())).thenThrow(new EntityNotFoundException("Unit not found"));
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/station/55/unit/55")
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
