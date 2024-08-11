package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ResourceChartDataRowMapper implements RowMapper<ResourceChartDataDTO> {

    @Override
    public ResourceChartDataDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResourceChartDataDTO resourceChartDataDTO = new ResourceChartDataDTO();
        resourceChartDataDTO.setWashCycleDate(rs.getString("wash_cycle_date"));
        resourceChartDataDTO.setTotalWaterConsumption(rs.getFloat("total_water_consumption"));
        resourceChartDataDTO.setTotalWaxConsumption(rs.getFloat("total_wax_consumption"));
        resourceChartDataDTO.setTotalDetergentConsumption(rs.getFloat("total_detergent_consumption"));
        return resourceChartDataDTO;
    }
}
