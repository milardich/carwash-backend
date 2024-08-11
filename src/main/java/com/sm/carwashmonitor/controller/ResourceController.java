package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;
import com.sm.carwashmonitor.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/station/{stationId}/resource-consumption/chart-data")
    public List<ResourceChartDataDTO> getResourceUsageChartData(
            @PathVariable("stationId") Long stationId,
            @RequestParam("pgTimeInterval") String pgTimeInterval
    ) {
        return resourceService.getResourceUsageChartData(stationId, pgTimeInterval);
    }

}
