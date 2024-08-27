package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.dto.WashCycleDTO;

public interface StatisticsService {
    StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timezone);
}
