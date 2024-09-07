package com.sm.carwashmonitor.controller;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import com.sm.carwashmonitor.dto.StatisticsSummaryDTO;
import com.sm.carwashmonitor.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/highlights")
    public StatisticsHighlightsDTO getStatisticsHighlights(
            @RequestParam("dateTimeFrom") String dateTimeFrom,
            @RequestParam("dateTimeTo") String dateTimeTo,
            @RequestParam("timezone") String timezone
    ) {
        return statisticsService.getStatisticsHighlights(dateTimeFrom, dateTimeTo, timezone);
    }

    @GetMapping("/summary")
    public StatisticsSummaryDTO getStatisticsSummary(
            @RequestParam("dateTimeFrom") String dateTimeFrom,
            @RequestParam("dateTimeTo") String dateTimeTo,
            @RequestParam("timezone") String timezone
    ) {
        return statisticsService.getStatisticsSummary(dateTimeFrom, dateTimeTo, timezone);
    }
}
