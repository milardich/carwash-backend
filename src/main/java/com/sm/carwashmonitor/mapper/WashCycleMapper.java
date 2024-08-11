package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.WashCycleRequestDTO;
import com.sm.carwashmonitor.dto.WashCycleDTO;
import com.sm.carwashmonitor.model.WashCycle;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WashCycleMapper {

    @Mapping(target = "waterConsumption", source = "waterConsumption")
    @Mapping(target = "detergentConsumption", source = "detergentConsumption")
    @Mapping(target = "waxConsumption", source = "waxConsumption")
    @Mapping(target = "coinAmount", source = "coinAmount")
    WashCycle toEntity(WashCycleRequestDTO washCycleRequestDto);

    @Mapping(target = "washCycleId", source = "washCycleId")
    @Mapping(target = "unitId", expression = "java(washCycle.getUnit().getUnitId())")
    @Mapping(target = "waterConsumption", source = "waterConsumption")
    @Mapping(target = "washCycleDate", source = "washCycleDate")
    @Mapping(target = "detergentConsumption", source = "detergentConsumption")
    @Mapping(target = "waxConsumption", source = "waxConsumption")
    @Mapping(target = "coinAmount", source = "coinAmount")
    WashCycleDTO toDto(WashCycle washCycle);
}
