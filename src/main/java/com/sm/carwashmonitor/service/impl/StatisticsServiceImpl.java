package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsDTO;
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
    public StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timezone) {
        StatisticsDTO stats = statisticsRepository.getStatistics(dateTimeFrom, dateTimeTo, timezone);
        setCosts(stats);
        return stats;
    }

    @Override
    public List<StationStatisticsDTO> getAllStationsStatistics(String dateTimeFrom, String dateTimeTo, String timezone) {
        List<StationStatisticsDTO> allStationsStats = statisticsRepository.getAllStationsStatistics(dateTimeFrom, dateTimeTo, timezone);
        return allStationsStats;
    }


    private void setCosts(StatisticsDTO statisticsDTO) {
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
}
