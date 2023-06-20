package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResourceConsumptionDto {
    private LocalDateTime dateTime;
    private Float consumption;
}
