package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.UnitDTO;
import com.sm.carwashmonitor.model.Unit;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UnitMapper {

    @Mapping(target = "unitId", source = "unitId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "coinTrayAmount", source = "coinTrayAmount")
    Unit toEntity(UnitDTO unitDto);

    @Mapping(target = "unitId", source = "unitId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "coinTrayAmount", source = "coinTrayAmount")
    UnitDTO toDto(Unit unit);
}
