package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.WashCycleDTO;
import com.sm.carwashmonitor.dto.WashCycleRequestDTO;
import com.sm.carwashmonitor.mapper.StatisticsRowMapper;
import com.sm.carwashmonitor.repository.DummyWashcycleRepository;
import com.sm.carwashmonitor.util.FileReaderUtil;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class DummyWashCycleRepositoryImpl implements DummyWashcycleRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer createDummyWashCycle(Long stationId, Long unitId, WashCycleRequestDTO washCycleRequestDTO, String washCycleDate, Long washCycleId) {
        String sqlQuery = FileReaderUtil.readStringFromFile("InsertDummyWashCycle.sql");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("stationId", stationId);
        params.addValue("unitId", unitId);
        params.addValue("washCycleDate", Timestamp.valueOf(LocalDateTime.parse(washCycleDate)));
        params.addValue("waterConsumption", washCycleRequestDTO.getWaterConsumption());
        params.addValue("waxConsumption", washCycleRequestDTO.getWaxConsumption());
        params.addValue("detergentConsumption", washCycleRequestDTO.getDetergentConsumption());
        params.addValue("coinAmount", washCycleRequestDTO.getCoinAmount());
        params.addValue("washCycleId", washCycleId);

        return jdbcTemplate.update(sqlQuery, params);
    }
}