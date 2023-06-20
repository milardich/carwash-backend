package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ResourcesUsageResponseDto {
    private Map<LocalDateTime, Float> waterUsage;
    private Map<LocalDateTime, Float> detergentUsage;
    private Map<LocalDateTime, Float> waxUsage;
}
