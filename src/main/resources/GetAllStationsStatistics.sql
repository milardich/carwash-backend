SELECT
    s.station_id,
    s.station_name,
    COALESCE(SUM(wc.coin_amount), 0) AS revenue,
    COALESCE(SUM(wc.water_consumption) * :waterPrice, 0) AS water_cost,
    COALESCE(SUM(wc.wax_consumption) * :waxPrice, 0) AS wax_cost,
    COALESCE(SUM(wc.detergent_consumption) * :detergentPrice, 0) AS detergent_cost
FROM
    station s
    LEFT JOIN unit u ON s.station_id = u.station_id
    LEFT JOIN wash_cycle wc ON u.unit_id = wc.unit_id
        AND wc.wash_cycle_date AT TIME ZONE :timezone >= :dateTimeFrom AT TIME ZONE :timezone
        AND wc.wash_cycle_date AT TIME ZONE :timezone < :dateTimeTo AT TIME ZONE :timezone
GROUP BY
    s.station_id,
    s.station_name;
