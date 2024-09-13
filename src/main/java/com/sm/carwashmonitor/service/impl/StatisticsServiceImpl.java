package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import com.sm.carwashmonitor.dto.StatisticsSummaryDTO;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import com.sm.carwashmonitor.service.StatisticsService;
import com.sm.carwashmonitor.util.CarwashResourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public StatisticsHighlightsDTO getStatisticsHighlights(String dateTimeFrom, String dateTimeTo, String timezone) {
        StatisticsHighlightsDTO statisticsHighlights = statisticsRepository
                .getStatisticsHighlights(dateTimeFrom, dateTimeTo, timezone);
        CarwashResourceUtil.setCosts(statisticsHighlights);
        return statisticsHighlights;
    }

    @Override
    public StatisticsSummaryDTO getStatisticsSummary(String dateTimeFrom, String dateTimeTo, String timezone) {
        StatisticsSummaryDTO statisticsSummaryDTO = new StatisticsSummaryDTO();
        List<StationStatisticsDTO> allStationsStatistics = statisticsRepository
                .getStatisticsSummary(dateTimeFrom, dateTimeTo, timezone);
        statisticsSummaryDTO.setAllStationStatistics(allStationsStatistics);
        CarwashResourceUtil.setCosts(statisticsSummaryDTO);
        return statisticsSummaryDTO;
    }
}
