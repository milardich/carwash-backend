package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.StatisticsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsRowMapper implements RowMapper<StatisticsDTO> {

    @Override
    public StatisticsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setTotalRevenue(rs.getFloat("total_coin_amount"));
        // TODO: finish this
        return statisticsDTO;
    }
}
