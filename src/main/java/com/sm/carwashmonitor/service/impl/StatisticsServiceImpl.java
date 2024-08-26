package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import com.sm.carwashmonitor.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timeZone) {
        statisticsRepository.getStatistics("2024-08-01T00:00:00", "2024-08-25T00:00:00", "Europe/Zagreb");
        return null;
    }
}
