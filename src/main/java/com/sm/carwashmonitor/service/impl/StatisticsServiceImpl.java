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
        StatisticsHighlightsDTO statisticsHighlights = statisticsRepository.getStatisticsHighlights(dateTimeFrom, dateTimeTo, timezone);
        setCosts(statisticsHighlights);
        return statisticsHighlights;
    }

    @Override
    public StatisticsSummaryDTO getStatisticsSummary(String dateTimeFrom, String dateTimeTo, String timezone) {
        StatisticsSummaryDTO statisticsSummaryDTO = new StatisticsSummaryDTO();
        List<StationStatisticsDTO> allStationsStatistics = statisticsRepository.getStatisticsSummary(dateTimeFrom, dateTimeTo, timezone);
        statisticsSummaryDTO.setAllStationStatistics(allStationsStatistics);
        setCosts(statisticsSummaryDTO);
        return statisticsSummaryDTO;
    }


    private void setCosts(StatisticsHighlightsDTO statisticsDTO) {
        // per liter price (eur)
        Float waterPrice = CarwashResourceUtil.getWaterPrice();
        Float waxPrice = CarwashResourceUtil.getWaxPrice();
        Float detergentPrice = CarwashResourceUtil.getDetergentPrice();

        statisticsDTO.setTotalWaxCost(
            statisticsDTO.getTotalWaxConsumption() * waxPrice
        );
        statisticsDTO.setTotalWaterCost(
            statisticsDTO.getTotalWaterConsumption() * waterPrice
        );
        statisticsDTO.setTotalDetergentCost(
            statisticsDTO.getTotalDetergentConsumption() * detergentPrice
        );
    }

    private void setCosts(StatisticsSummaryDTO statisticsSummary) {
        Float waterPrice = CarwashResourceUtil.getWaterPrice();
        Float waxPrice = CarwashResourceUtil.getWaxPrice();
        Float detergentPrice = CarwashResourceUtil.getDetergentPrice();

        statisticsSummary.setTotalWaxCost(0.0F);
        statisticsSummary.setTotalWaterCost(0.0F);
        statisticsSummary.setTotalDetergentCost(0.0F);
        statisticsSummary.setTotalRevenue(0.0F);

        statisticsSummary.getAllStationStatistics().forEach(s -> {
            statisticsSummary.setTotalWaterCost(
                    statisticsSummary.getTotalWaterCost() + s.getWaterCost()
            );
            statisticsSummary.setTotalDetergentCost(
                    statisticsSummary.getTotalDetergentCost() + s.getDetergentCost()
            );
            statisticsSummary.setTotalWaxCost(
                    statisticsSummary.getTotalWaxCost() + s.getWaxCost()
            );
            statisticsSummary.setTotalRevenue(
                    statisticsSummary.getTotalRevenue() + s.getRevenue()
            );
        });
    }
}
