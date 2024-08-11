package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitInfoDTO {
    Integer washCycleCount;
    Integer totalCoinAmount;
    Float totalWaterConsumption;
    Float totalWaxConsumption;
    Float totalDetergentConsumption;
}
