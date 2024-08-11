package com.sm.carwashmonitor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class StationRequestDTO {
    private String stationName;
    private String city;
    private String streetName;
    private String streetNumber;
    private String country;
}
