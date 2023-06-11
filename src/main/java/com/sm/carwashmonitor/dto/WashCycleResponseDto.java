package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WashCycleResponseDto {
    private Long unitId;
    private String washCycleDate;
    private Integer coinAmount;
    private Float waterConsumption;
    private Float detergentConsumption;
    private Float waxConsumption;
}
