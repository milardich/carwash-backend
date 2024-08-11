package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;
import com.sm.carwashmonitor.service.ChartDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/resources") // TODO: change to /api/chart/
public class ChartDataController {

    private final ChartDataService chartDataService;

    @GetMapping("/station/{stationId}/resource-consumption/chart-data")
    public List<ResourceChartDataDTO> getResourceUsageChartData(
            @PathVariable("stationId") Long stationId,
            @RequestParam("pgTimeInterval") String pgTimeInterval
    ) {
        return chartDataService.getResourceUsageChartData(stationId, pgTimeInterval);
    }

}
