package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class StationResponseDto {
    private String stationName;
    private String city;
    private String streetName;
    private Integer streetNumber;
    private String country;
    private List<UnitDto> units;
}
