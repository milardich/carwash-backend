package com.sm.carwashmonitor.mapper;

import com.sm.carwashmonitor.dto.UnitInfoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitInfoRowMapper implements RowMapper<UnitInfoDTO> {
    @Override
    public UnitInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UnitInfoDTO unitInfoDTO = new UnitInfoDTO();
        unitInfoDTO.setWashCycleCount(rs.getInt("wash_cycle_count"));
        unitInfoDTO.setTotalCoinAmount(rs.getInt("total_coin_amount"));
        unitInfoDTO.setTotalWaterConsumption(rs.getFloat("total_water_consumption"));
        unitInfoDTO.setTotalWaxConsumption(rs.getFloat("total_wax_consumption"));
        unitInfoDTO.setTotalDetergentConsumption(rs.getFloat("total_detergent_consumption"));
        return unitInfoDTO;
    }
}
