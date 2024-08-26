package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public StatisticsDTO getStatistics(
            @RequestParam("dateTimeFrom") String dateTimeFrom,
            @RequestParam("dateTimeTo") String dateTimeTo,
            @RequestParam("timezone") String timezone
    ) {
        return statisticsService.getStatistics(dateTimeFrom, dateTimeTo, timezone);
    }
}
