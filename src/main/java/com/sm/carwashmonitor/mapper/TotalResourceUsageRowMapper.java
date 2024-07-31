package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.TotalResourceUsageDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TotalResourceUsageRowMapper implements RowMapper<TotalResourceUsageDTO> {

    @Override
    public TotalResourceUsageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TotalResourceUsageDTO totalResourceUsageDTO = new TotalResourceUsageDTO();
        totalResourceUsageDTO.setWashCycleDate(rs.getString("wash_cycle_date"));
        totalResourceUsageDTO.setTotalWaterConsumption(rs.getFloat("total_water_consumption"));
        totalResourceUsageDTO.setTotalWaxConsumption(rs.getFloat("total_wax_consumption"));
        totalResourceUsageDTO.setTotalDetergentConsumption(rs.getFloat("total_detergent_consumption"));
        return totalResourceUsageDTO;
    }
}
