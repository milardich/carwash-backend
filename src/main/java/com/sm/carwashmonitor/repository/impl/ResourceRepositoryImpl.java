package com.sm.carwashmonitor.repository.impl;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;
import com.sm.carwashmonitor.mapper.ResourceChartDataRowMapper;
import com.sm.carwashmonitor.repository.ResourceRepository;
import com.sm.carwashmonitor.validation.DateTimeValidation;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class ResourceRepositoryImpl implements ResourceRepository {

    private JdbcTemplate jdbcTemplate;
    private DateTimeValidation dateTimeValidation;

    @Override
    public List<ResourceChartDataDTO> getResourceUsageChartData(Long stationId, String pgTimeInterval) {
        dateTimeValidation.validate(pgTimeInterval);
        /*
            day/days -> number of data points = number of days
            month -> number of data points = number of months
            year/years -> number of data points = number of years
         */
        String range = pgTimeInterval.split("\\s+")[1].replace("\"", ""); // "1 day" -> "day"
        String sql =
            "SELECT " +
                "date_trunc('" + range + "', wc.wash_cycle_date) AS wash_cycle_date, " +
                "SUM(wc.water_consumption) AS total_water_consumption, " +
                "SUM(wc.wax_consumption) AS total_wax_consumption, " +
                "SUM(wc.detergent_consumption) AS total_detergent_consumption " +
            "FROM wash_cycle wc, unit u, station s " +
            "WHERE " +
                "wc.wash_cycle_date > current_date - '" + pgTimeInterval + "'::interval " +
                "AND " +
                "wc.unit_id = u.unit_id " +
                "AND " +
                "u.station_id = s.station_id " +
                "AND " +
                "s.station_id = " + stationId + " " +
            "GROUP BY date_trunc('" + range + "', wc.wash_cycle_date) " +
            "ORDER BY date_trunc('" + range + "', wc.wash_cycle_date) ";

        return jdbcTemplate.query(sql, new ResourceChartDataRowMapper());
    }
}
