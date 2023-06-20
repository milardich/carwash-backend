package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResourcesUsageResponseDto {
    private Float totalWaterConsumption;
    private Float totalDetergentConsumption;
    private Float totalWaxConsumption;
    private List<ResourceUsageDto> waterUsages;
    private List<ResourceUsageDto> detergentUsages;
    private List<ResourceUsageDto> waxUsages;
}
