package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsRowMapper implements RowMapper<StatisticsHighlightsDTO> {

    @Override
    public StatisticsHighlightsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        StatisticsHighlightsDTO statisticsDTO = new StatisticsHighlightsDTO();
        statisticsDTO.setTotalRevenue(rs.getFloat("total_coin_amount"));
        statisticsDTO.setBestStationName(rs.getString("best_station_name"));
        statisticsDTO.setBestStationRevenue(rs.getFloat("best_station_revenue"));
        statisticsDTO.setTotalWashCount(rs.getInt("total_wash_cycle_count"));
        statisticsDTO.setTotalWaterConsumption(rs.getFloat("total_water_consumption"));
        statisticsDTO.setTotalWaxConsumption(rs.getFloat("total_wax_consumption"));
        statisticsDTO.setTotalDetergentConsumption(rs.getFloat("total_detergent_consumption"));
        return statisticsDTO;
    }
}
