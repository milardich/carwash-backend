package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResourceUsageDto {
    private LocalDateTime dateTime;
    private Float consumption;
}
