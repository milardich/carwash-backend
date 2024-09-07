package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsDTO;

import java.util.List;

public interface StatisticsRepository {
    StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timeZone);
    List<StationStatisticsDTO> getAllStationsStatistics(String dateTimeFrom, String dateTimeTo, String timeZone);
}
