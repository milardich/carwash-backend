package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResourcesUsageResponseDto {
    private Float totalWaterConsumption;
    private Float totalDetergentConsumption;
    private Float totalWaxConsumption;
    private List<ResourceConsumptionDto> waterConsumptions;
    private List<ResourceConsumptionDto> detergentConsumptions;
    private List<ResourceConsumptionDto> waxConsumptions;

    public ResourcesUsageResponseDto() {
        this.totalWaterConsumption = 0.0F;
        this.totalDetergentConsumption = 0.0F;
        this.totalWaxConsumption = 0.0F;
        this.waterConsumptions = new ArrayList<>();
        this.detergentConsumptions = new ArrayList<>();
        this.waxConsumptions = new ArrayList<>();
    }
}
