package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.UnitInfoDTO;
import com.sm.carwashmonitor.mapper.UnitInfoRowMapper;
import com.sm.carwashmonitor.repository.UnitInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UnitInfoRepositoryImpl implements UnitInfoRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public UnitInfoDTO getUnitInfo(Long unitId, String dateTimeFrom, String dateTimeTo) {
        // TODO: validate dateTimes

        String sql =
            "select " +
                "count(wc.wash_cycle_id) as wash_cycle_count, " +
                "sum(wc.coin_amount) as total_coin_amount, " +
                "sum(wc.water_consumption) as total_water_consumption, " +
                "sum(wc.wax_consumption) as total_wax_consumption, " +
                "sum(wc.detergent_consumption) as total_detergent_consumption " +
            "from " +
                "wash_cycle wc " +
            "where " +
                "wc.unit_id = '" + unitId + "'" +
                " and " +
                "wc.wash_cycle_date >= '" + dateTimeFrom + "'" +
                " and " +
                "wc.wash_cycle_date < '" + dateTimeTo + "'";
        return jdbcTemplate.queryForObject(sql, new UnitInfoRowMapper());
    }
}
