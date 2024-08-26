package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.StatisticsDTO;
import com.sm.carwashmonitor.mapper.StatisticsRowMapper;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepository {

//    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public StatisticsDTO getStatistics(String dateTimeFrom, String dateTimeTo, String timeZone) {

        /*
        * TODO: figure out how to load query from file, mby use NamedParameterJdbcTemplate
         */
        File sqlQueryFile = getResourceFile("GetStatistics.sql");
        String sqlQuery = readStringFromFile(sqlQueryFile);

        Map<String, String> sqlParameterMap = new HashMap<>();
        sqlParameterMap.put("dateTimeFrom", "'" + dateTimeFrom + "'");
        sqlParameterMap.put("dateTimeTo", "'" + dateTimeTo + "'");
        sqlParameterMap.put("timezone", "'" + timeZone + "'");


        return jdbcTemplate.queryForObject(sqlQuery, sqlParameterMap, new StatisticsRowMapper());
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
            sqlQueryFile = ResourceUtils.getFile("classpath:GetStatistics.sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlQueryFile;
    }
}
