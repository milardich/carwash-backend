package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.mapper.StationsStatisticsRowMapper;
import com.sm.carwashmonitor.mapper.StatisticsRowMapper;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import com.sm.carwashmonitor.util.CarwashResourceUtil;
import com.sm.carwashmonitor.util.FileReaderUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timezone) {
        String sqlQuery = FileReaderUtil.readStringFromFile("GetStatistics.sql");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dateTimeFrom", Timestamp.valueOf(LocalDateTime.parse(dateTimeFrom)));
        params.addValue("dateTimeTo", Timestamp.valueOf(LocalDateTime.parse(dateTimeTo)));
        params.addValue("timezone", timezone);

        return jdbcTemplate.queryForObject(sqlQuery, params, new StatisticsRowMapper());
    }

    @Override
    public List<StationStatisticsDTO> getAllStationsStatistics(String dateTimeFrom, String dateTimeTo, String timezone) {
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
