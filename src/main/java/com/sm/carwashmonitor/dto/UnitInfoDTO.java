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

    /*
        unitStatus is filled additionally in service layer,
        because query returns the things above, without unitStatus
     */
    String unitStatus;
}
