package com.sm.carwashmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TotalResourceUsageDTO {
    String washCycleDate;
    Float totalWaterConsumption;
    Float totalWaxConsumption;
    Float totalDetergentConsumption;
}
