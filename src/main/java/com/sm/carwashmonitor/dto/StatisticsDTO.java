package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class StatisticsDTO {
    Float totalRevenue;
    String bestStationName;
    Float bestStationRevenue;
    Integer totalWashCount;
    Float totalWaterConsumption;
    Float totalWaxConsumption;
    Float totalDetergentConsumption;

    /*
    * 1 coin = 1 euro
    * */
    Float totalWaterCost;
    Float totalWaxCost;
    Float totalDetergentCost;

    /* TODO:
    *   String revenuePercentIncrease;
    *   String washCountPercentIncrease;
    *  */

    List<StationStatisticsDTO> allStationsStatistics;
    StationStatisticsDTO totalStationsStatistics; //bottom of the table
}
