package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.dto.UnitDto;
import com.sm.carwashmonitor.dto.UnitStatusDto;
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

    @PatchMapping("/{unitId}/status")
    public UnitDto updateUnitStatus(@PathVariable(name = "stationId") Long stationId,
                                    @PathVariable(name = "unitId") Long unitId,
                                    @RequestBody UnitStatusDto unitStatusDto
    ) {
        return unitService.updateUnitStatus(stationId, unitId, unitStatusDto);
    }
}
