package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class StationResponseDto {
    private Long stationId;
    private String stationName;
    private String city;
    private String streetName;
    private String streetNumber;
    private String country;
    private List<UnitDto> units;
}
