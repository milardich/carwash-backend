package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.TotalResourceUsageDTO;
import com.sm.carwashmonitor.dto.ResourcesUsageResponseDto;
import com.sm.carwashmonitor.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/station/{stationId}/resource-consumption")
    public ResourcesUsageResponseDto getStationResourceConsumption(
            @PathVariable(name = "stationId") Long stationId,
            @RequestParam(name = "dateTimeFrom", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeFrom,
            @RequestParam(name = "dateTimeTo", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeTo
    ) {
        return resourceService.getStationResourcesUsage(stationId, dateTimeFrom, dateTimeTo);
    }

    @GetMapping("/station/{stationId}/resource-consumption/total")
    public List<TotalResourceUsageDTO> getGroupedResourcesUsage(
            @PathVariable("stationId") Long stationId,
            @RequestParam("dateTimeRange") String dateTimeRange
    ) {
        return resourceService.getGroupedResourcesUsage(stationId, dateTimeRange);
    }
}
