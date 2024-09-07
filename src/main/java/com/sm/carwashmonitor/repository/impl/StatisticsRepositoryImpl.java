package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import com.sm.carwashmonitor.mapper.StationsStatisticsRowMapper;
import com.sm.carwashmonitor.mapper.StatisticsRowMapper;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import com.sm.carwashmonitor.util.CarwashResourceUtil;
import com.sm.carwashmonitor.util.FileReaderUtil;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public StatisticsHighlightsDTO getStatisticsHighlights(String dateTimeFrom, String dateTimeTo, String timezone) {
        String sqlQuery = FileReaderUtil.readStringFromFile("GetStatistics.sql");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dateTimeFrom", Timestamp.valueOf(LocalDateTime.parse(dateTimeFrom)));
        params.addValue("dateTimeTo", Timestamp.valueOf(LocalDateTime.parse(dateTimeTo)));
        params.addValue("timezone", timezone);

        return jdbcTemplate.queryForObject(sqlQuery, params, new StatisticsRowMapper());
    }

    @Override
    public List<StationStatisticsDTO> getStatisticsSummary(String dateTimeFrom, String dateTimeTo, String timezone) {
        String sqlQuery = FileReaderUtil.readStringFromFile("GetAllStationsStatistics.sql");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dateTimeFrom", Timestamp.valueOf(LocalDateTime.parse(dateTimeFrom)));
        params.addValue("dateTimeTo", Timestamp.valueOf(LocalDateTime.parse(dateTimeTo)));
        params.addValue("timezone", timezone);
        params.addValue("waterPrice", CarwashResourceUtil.getWaterPrice());
        params.addValue("waxPrice", CarwashResourceUtil.getWaxPrice());
        params.addValue("detergentPrice", CarwashResourceUtil.getDetergentPrice());

        return jdbcTemplate.query(sqlQuery, params, new StationsStatisticsRowMapper());
    }
}
