package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.model.WashCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WashCycleRepository extends JpaRepository<WashCycle, Long> {

    @Query("SELECT washCycle FROM WashCycle washCycle WHERE washCycle.unit.unitId = :unitId AND washCycle.washCycleDate >= :dateTimeFrom AND washCycle.washCycleDate <= :dateTimeTo ORDER BY washCycle.washCycleDate DESC")
    List<WashCycle> getFilteredWashCycles(Long unitId, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo);
}
