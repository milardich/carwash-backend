package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class StatisticsDTO {
    Float totalRevenue;
    String bestStationName;
    Float bestStationRevenue;
    Integer totalWashCount;
    Integer totalWaterConsumption;
    Integer totalWaxConsumption;
    Integer totalDetergentConsumption;

    /*
    * This is calculated in service layer
    * */
    Float totalWaterCost;
    Float totalWaxCost;
    Float totalDetergentCost;
}
