package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationStatisticsDTO {
    String stationName;
    Float waterCost;
    Float detergentCost;
    Float waxCost;
    Float revenue;
}
