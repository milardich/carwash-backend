package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.dto.WashCycleDTO;
import com.sm.carwashmonitor.dto.WashCycleRequestDTO;

public interface DummyWashcycleRepository {
    Integer createDummyWashCycle(
            Long stationId,
            Long unitId,
            WashCycleRequestDTO washCycleRequestDTO,
            String washCycleDate,
            Long washCycleId
    );
}
