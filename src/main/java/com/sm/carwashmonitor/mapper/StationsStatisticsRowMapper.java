package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationsStatisticsRowMapper implements RowMapper<StationStatisticsDTO> {
    @Override
    public StationStatisticsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        StationStatisticsDTO statisticsDTO = new StationStatisticsDTO();
        statisticsDTO.setStationName(rs.getString("station_name"));
        statisticsDTO.setRevenue(rs.getFloat("revenue"));
        statisticsDTO.setWaterCost(rs.getFloat("water_cost"));
        statisticsDTO.setWaxCost(rs.getFloat("wax_cost"));
        statisticsDTO.setDetergentCost(rs.getFloat("detergent_cost"));
        return statisticsDTO;
    }
}
