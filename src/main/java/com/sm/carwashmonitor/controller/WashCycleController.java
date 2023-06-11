package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.WashCycleRequestDto;
import com.sm.carwashmonitor.dto.WashCycleResponseDto;
import com.sm.carwashmonitor.service.WashCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station/{stationId}/unit/{unitId}/wash-cycle")
public class WashCycleController {

    private final WashCycleService washCycleService;

    @PostMapping
    public WashCycleResponseDto createNewWashCycle(@PathVariable(name = "stationId") Long stationId,
                                                   @PathVariable(name = "unitId") Long unitId,
                                                   @RequestBody WashCycleRequestDto washCycleRequestDto
    ) {
        return washCycleService.createNewWashCycle(stationId, unitId, washCycleRequestDto);
    }

    @GetMapping("/{washCycleId}")
    public WashCycleResponseDto getWashCycle(@PathVariable(name = "stationId") Long stationId,
                                             @PathVariable(name = "unitId") Long unitId,
                                             @PathVariable(name = "washCycleId") Long washCycleId
    ) {
        return washCycleService.getWashCycle(stationId, unitId, washCycleId);
    }
}
