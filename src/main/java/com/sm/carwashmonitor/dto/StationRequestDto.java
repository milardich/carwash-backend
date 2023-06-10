package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class StationRequestDto {
    private String stationName;
    private String city;
    private String streetName;
    private Integer streetNumber;
    private String country;
}
