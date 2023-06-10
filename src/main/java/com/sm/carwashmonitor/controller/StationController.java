package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station")
public class StationController {

    @PostMapping
    public StationResponseDto createStation(@RequestBody StationRequestDto stationRequestDto) {
        return null;
    }
}
