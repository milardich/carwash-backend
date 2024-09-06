package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.dto.WashCycleDTO;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import com.sm.carwashmonitor.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timezone) {
        StatisticsDTO stats = statisticsRepository.getStatistics(dateTimeFrom, dateTimeTo, timezone);
        setCosts(stats);
        calculateTotalRevenue(stats);
        return stats;
    }

    private void setCosts(StatisticsDTO statisticsDTO) {
        // per liter price (eur)
        final Float waterPrice = 0.00183F;
        final Float waxPrice = 5.480F;
        final Float detergentPrice = 3.71F;

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

    private void calculateTotalRevenue(StatisticsDTO statisticsDTO) {
        // eur
        final Float priceOfCoin = 2.0F;
        statisticsDTO.setTotalRevenue(
                statisticsDTO.getTotalRevenue() * priceOfCoin
        );
    }
}
