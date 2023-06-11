package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.model.WashCycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WashCycleRepository extends JpaRepository<WashCycle, Long> {
}
