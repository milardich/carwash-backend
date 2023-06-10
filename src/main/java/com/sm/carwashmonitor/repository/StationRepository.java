package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
