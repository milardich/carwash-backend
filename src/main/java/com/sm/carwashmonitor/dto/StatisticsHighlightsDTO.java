package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class StatisticsHighlightsDTO {
    Float totalRevenue;
    String bestStationName;
    Float bestStationRevenue;
    Integer totalWashCount;
    Float totalWaterConsumption;
    Float totalWaxConsumption;
    Float totalDetergentConsumption;
    Float totalWaterCost;
    Float totalWaxCost;
    Float totalDetergentCost;
}
