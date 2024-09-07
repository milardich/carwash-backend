package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.dto.WashCycleDTO;

import java.util.List;

public interface StatisticsService {
    StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timezone);
    List<StationStatisticsDTO> getAllStationsStatistics(String dateTimeFrom, String dateTimeTo, String timezone);
}
