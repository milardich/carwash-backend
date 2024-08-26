package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StatisticsDTO;

public interface StatisticsService {
    StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timeZone);
}
