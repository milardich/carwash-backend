package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.UnitDTO;
import com.sm.carwashmonitor.dto.UnitStatusDTO;
import com.sm.carwashmonitor.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station/{stationId}/unit")
public class UnitController {

    private final UnitService unitService;

    @PostMapping
    public StationDTO createUnit(@PathVariable(name = "stationId") Long stationId) {
        return unitService.createUnit(stationId);
    }

    @PatchMapping("/{unitId}/status")
    public UnitDTO updateUnitStatus(@PathVariable(name = "stationId") Long stationId,
                                    @PathVariable(name = "unitId") Long unitId,
                                    @RequestBody UnitStatusDTO unitStatusDto
    ) {
        return unitService.updateUnitStatus(stationId, unitId, unitStatusDto);
    }

    @GetMapping("/{unitId}")
    public UnitDTO getUnit(@PathVariable(name = "stationId") Long stationId,
                           @PathVariable(name = "unitId") Long unitId
    ) {
        return unitService.getUnit(stationId, unitId);
    }
}
