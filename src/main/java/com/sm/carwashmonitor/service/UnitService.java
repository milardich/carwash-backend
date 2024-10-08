package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.UnitDTO;
import com.sm.carwashmonitor.dto.UnitInfoDTO;
import com.sm.carwashmonitor.dto.UnitStatusDTO;

public interface UnitService {
    StationDTO createUnit(Long stationId);
    UnitDTO updateUnitStatus(Long stationId, Long unitId, UnitStatusDTO unitStatusDto);
    UnitDTO getUnit(Long stationId, Long unitId);
    UnitInfoDTO getUnitInfo(Long stationId, Long unitId, String dateTimeFrom, String dateTimeTo, String timezone);
}
