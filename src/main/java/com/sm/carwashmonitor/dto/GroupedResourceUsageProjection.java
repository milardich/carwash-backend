package com.sm.carwashmonitor.dto;

import java.time.LocalDate;

public interface GroupedResourceUsageProjection {
    LocalDate getWashCycleDate();
    Float getTotalWaterConsumption();
    Float getTotalWaxConsumption();
    Float getTotalDetergentConsumption();
}
