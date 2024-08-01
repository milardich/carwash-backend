package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.TotalResourceUsageDTO;
import com.sm.carwashmonitor.mapper.TotalResourceUsageRowMapper;
import com.sm.carwashmonitor.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class ResourceRepositoryImpl implements ResourceRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TotalResourceUsageDTO> getResourcesUsageByDateTimeRange(Long stationId, String dateTimeRange) {
        String sql =
            "SELECT wc.wash_cycle_date::date AS wash_cycle_date, " +
                "SUM(wc.water_consumption) AS total_water_consumption, " +
                "SUM(wc.wax_consumption) AS total_wax_consumption, " +
                "SUM(wc.detergent_consumption) AS total_detergent_consumption " +
            "FROM wash_cycle wc, unit u, station s " +
            "WHERE " +
                "wc.wash_cycle_date > current_date - '" + dateTimeRange + "'::interval " +
                "AND " +
                "wc.unit_id = u.unit_id " +
                "AND " +
                "u.station_id = s.station_id " +
                "AND " +
                "s.station_id = " + stationId + " " +
            "GROUP BY wc.wash_cycle_date::date " +
            "ORDER BY wc.wash_cycle_date::date";

        return jdbcTemplate.query(sql, new TotalResourceUsageRowMapper());
    }
}
