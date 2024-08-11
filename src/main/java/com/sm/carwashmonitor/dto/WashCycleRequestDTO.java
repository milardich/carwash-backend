package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WashCycleRequestDTO {
    private Float waterConsumption;
    private Float detergentConsumption;
    private Float waxConsumption;
    private Integer coinAmount;
}
