package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.StationRequestDTO;
import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.model.Station;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StationMapper {

    @Mapping(target = "stationName", source = "stationName")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "streetName", source = "streetName")
    @Mapping(target = "streetNumber", source = "streetNumber")
    @Mapping(target = "country", source = "country")
    Station toEntity(StationRequestDTO dto);

    @Mapping(target = "stationId", source = "stationId")
    @Mapping(target = "stationName", source = "stationName")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "streetName", source = "streetName")
    @Mapping(target = "streetNumber", source = "streetNumber")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "units", source = "units")
    StationDTO toDto(Station station);
}
