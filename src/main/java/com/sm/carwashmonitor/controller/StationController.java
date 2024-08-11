package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StationRequestDTO;
import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station")
public class StationController {

    private final StationService stationService;

    @PostMapping
    public StationDTO createStation(@RequestBody StationRequestDTO stationRequestDto) {
        return stationService.createStation(stationRequestDto);
    }

    @GetMapping("/{stationId}")
    public StationDTO getStationById(@PathVariable(value = "stationId") Long stationId) {
        return stationService.getStation(stationId);
    }

    @GetMapping
    public List<StationDTO> getAllStations() {
        return stationService.getAllStations();
    }
}
