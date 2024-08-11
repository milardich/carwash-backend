package com.sm.carwashmonitor.repository;

import com.sm.carwashmonitor.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long>, UnitInfoRepository {

}
