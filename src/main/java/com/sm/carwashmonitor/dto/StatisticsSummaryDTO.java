package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatisticsSummaryDTO {
    List<StationStatisticsDTO> allStationStatistics;
    Float totalWaterCost;
    Float totalDetergentCost;
    Float totalWaxCost;
    Float totalRevenue;
}
