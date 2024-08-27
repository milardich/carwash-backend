package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.mapper.StatisticsRowMapper;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepository {

//    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timezone) {
        File sqlQueryFile = getResourceFile("GetStatistics.sql");
        String sqlQuery = readStringFromFile(sqlQueryFile);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dateTimeFrom", Timestamp.valueOf(LocalDateTime.parse(dateTimeFrom)));
        params.addValue("dateTimeTo", Timestamp.valueOf(LocalDateTime.parse(dateTimeTo)));
        params.addValue("timezone", timezone);

        return jdbcTemplate.queryForObject(sqlQuery, params, new StatisticsRowMapper());
    }

    private String readStringFromFile(File file) {
        String content = "";
        try {
            content = Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private File getResourceFile(String fileName) {
        File sqlQueryFile = null;
        try {
            sqlQueryFile = ResourceUtils.getFile("classpath:" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlQueryFile;
    }
}
