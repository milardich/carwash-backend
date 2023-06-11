package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station")
public class StationController {

    private final StationService stationService;

    @PostMapping
    public StationResponseDto createStation(@RequestBody StationRequestDto stationRequestDto) {
        return stationService.createStation(stationRequestDto);
    }
}
