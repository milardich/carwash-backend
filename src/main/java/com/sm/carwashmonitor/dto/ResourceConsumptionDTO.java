package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResourceConsumptionDTO {
    private LocalDateTime dateTime;
    private Float consumption;
}
