package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station/{stationId}/unit")
public class UnitController {

    private final UnitService unitService;

    @PostMapping
    public StationResponseDto createUnit(@PathVariable(name = "stationId") Long stationId) {
        return unitService.createUnit(stationId);
    }
}
