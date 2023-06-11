package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitDto {
    private Long unitId;
    private String status;
    private Integer coinTrayAmount;
    private Float totalWaterConsumption;
    private Float totalDetergentConsumption;
    private Float totalWaxConsumption;
}
