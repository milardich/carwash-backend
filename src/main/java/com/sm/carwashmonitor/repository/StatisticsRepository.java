package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.StatisticsDTO;

public interface StatisticsRepository {
    StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timeZone);
}
