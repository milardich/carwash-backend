package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.model.WashCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WashCycleRepository extends JpaRepository<WashCycle, Long> {

    @Query("SELECT washCycle FROM WashCycle washCycle WHERE washCycle.unit.unitId = :unitId AND washCycle.washCycleDate >= :dateTimeFrom AND washCycle.washCycleDate <= :dateTimeTo ORDER BY washCycle.washCycleDate ASC")
    List<WashCycle> getFilteredWashCyclesByUnitId(Long unitId, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo);

    @Query("SELECT washCycle FROM WashCycle washCycle WHERE washCycle.unit.unitId = :unitId")
    List<WashCycle> getWashCyclesByUnitId(Long unitId);

    @Query(
        value = "SELECT wc.wash_cycle_date\\:\\:date AS washCycleDate, " +
                "SUM(wc.water_consumption) AS totalWaterConsumption, " +
                "SUM(wc.wax_consumption) AS totalWaxConsumption, " +
                "SUM(wc.detergent_consumption) AS totalDetergentConsumption " +
                "FROM wash_cycle wc " +
                "WHERE wc.wash_cycle_date > current_date - interval '1 month' " +
                "GROUP BY wc.wash_cycle_date\\:\\:date " +
                "ORDER BY wc.wash_cycle_date\\:\\:date",
        nativeQuery = true
    )
    List<Object[]> getResourcesUsage1Month();

    @Query(
        value = "SELECT wc.wash_cycle_date\\:\\:date AS washCycleDate, " +
                "SUM(wc.water_consumption) AS totalWaterConsumption, " +
                "SUM(wc.wax_consumption) AS totalWaxConsumption, " +
                "SUM(wc.detergent_consumption) AS totalDetergentConsumption " +
                "FROM wash_cycle wc " +
                "WHERE wc.wash_cycle_date > current_date - interval '7 days' " +
                "GROUP BY wc.wash_cycle_date\\:\\:date " +
                "ORDER BY wc.wash_cycle_date\\:\\:date",
        nativeQuery = true
    )
    List<Object[]> getResourcesUsageLast7Days();

    @Query(
        value = "SELECT wc.wash_cycle_date\\:\\:date AS washCycleDate, " +
                "SUM(wc.water_consumption) AS totalWaterConsumption, " +
                "SUM(wc.wax_consumption) AS totalWaxConsumption, " +
                "SUM(wc.detergent_consumption) AS totalDetergentConsumption " +
                "FROM wash_cycle wc " +
                "WHERE wc.wash_cycle_date > current_date - interval '1 year' " +
                "GROUP BY wc.wash_cycle_date\\:\\:date " +
                "ORDER BY wc.wash_cycle_date\\:\\:date",
        nativeQuery = true
    )
    List<Object[]> getResourcesUsageLastYear();
}
