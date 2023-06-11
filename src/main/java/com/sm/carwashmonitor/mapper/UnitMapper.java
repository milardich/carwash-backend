package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.UnitDto;
import com.sm.carwashmonitor.model.Unit;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UnitMapper {

    @Mapping(target = "unitId", source = "unitId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "coinTrayAmount", source = "coinTrayAmount")
    @Mapping(target = "totalWaterConsumption", source = "totalWaterConsumption")
    @Mapping(target = "totalDetergentConsumption", source = "totalDetergentConsumption")
    @Mapping(target = "totalWaxConsumption", source = "totalWaxConsumption")
    Unit toEntity(UnitDto unitDto);
}
