package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import com.sm.carwashmonitor.dto.StatisticsSummaryDTO;

import java.util.List;

public interface StatisticsService {
    StatisticsHighlightsDTO getStatisticsHighlights(String dateTimeFrom, String dateTimeTo, String timezone);
    StatisticsSummaryDTO getStatisticsSummary(String dateTimeFrom, String dateTimeTo, String timezone);
}
