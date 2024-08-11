package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.UnitInfoDTO;

public interface UnitInfoRepository {
    UnitInfoDTO getUnitInfo(Long unitId, String dateTimeFrom, String dateTimeTo);
}
