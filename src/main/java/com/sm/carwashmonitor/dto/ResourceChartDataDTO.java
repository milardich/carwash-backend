package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ResourceChartDataDTO {
    String washCycleDate;
    Float totalWaterConsumption;
    Float totalWaxConsumption;
    Float totalDetergentConsumption;
}
