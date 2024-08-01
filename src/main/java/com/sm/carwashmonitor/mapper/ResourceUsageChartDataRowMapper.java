package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.ResourceUsageChartDataDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ResourceUsageChartDataRowMapper implements RowMapper<ResourceUsageChartDataDTO> {

    @Override
    public ResourceUsageChartDataDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResourceUsageChartDataDTO resourceUsageChartDataDTO = new ResourceUsageChartDataDTO();
        resourceUsageChartDataDTO.setWashCycleDate(rs.getString("wash_cycle_date"));
        resourceUsageChartDataDTO.setTotalWaterConsumption(rs.getFloat("total_water_consumption"));
        resourceUsageChartDataDTO.setTotalWaxConsumption(rs.getFloat("total_wax_consumption"));
        resourceUsageChartDataDTO.setTotalDetergentConsumption(rs.getFloat("total_detergent_consumption"));
        return resourceUsageChartDataDTO;
    }
}
