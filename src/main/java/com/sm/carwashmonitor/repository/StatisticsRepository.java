package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;

import java.util.List;

public interface StatisticsRepository {
    StatisticsHighlightsDTO getStatisticsHighlights(String dateTimeFrom, String dateTimeTo, String timeZone);
    List<StationStatisticsDTO> getStatisticsSummary(String dateTimeFrom, String dateTimeTo, String timeZone);
}
