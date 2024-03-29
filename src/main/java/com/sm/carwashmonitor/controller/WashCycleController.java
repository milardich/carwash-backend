package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.WashCycleRequestDto;
import com.sm.carwashmonitor.dto.WashCycleResponseDto;
import com.sm.carwashmonitor.service.WashCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station/{stationId}/unit/{unitId}/wash-cycle")
public class WashCycleController {

    private final WashCycleService washCycleService;

    @PostMapping
    public WashCycleResponseDto createNewWashCycle(
            @PathVariable(name = "stationId") Long stationId,
            @PathVariable(name = "unitId") Long unitId,
            @RequestBody WashCycleRequestDto washCycleRequestDto
    ) {
        return washCycleService.createNewWashCycle(stationId, unitId, washCycleRequestDto);
    }

    @GetMapping("/{washCycleId}")
    public WashCycleResponseDto getWashCycle(
            @PathVariable(name = "stationId") Long stationId,
            @PathVariable(name = "unitId") Long unitId,
            @PathVariable(name = "washCycleId") Long washCycleId
    ) {
        return washCycleService.getWashCycle(stationId, unitId, washCycleId);
    }

    @GetMapping
    public List<WashCycleResponseDto> getFilteredWashCycles(
            @PathVariable(name = "stationId") Long stationId,
            @PathVariable(name = "unitId") Long unitId,
            @RequestParam(name = "dateTimeFrom", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeFrom,
            @RequestParam(name = "dateTimeTo", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeTo
    ) {
        return washCycleService.getFilteredWashCycles(stationId, unitId, dateTimeFrom, dateTimeTo);
    }
}
