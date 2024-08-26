WITH revenue_by_station AS (
    SELECT
        s.station_id,
        s.station_name,
        SUM(wc.coin_amount) AS station_total_coin_amount
    FROM
        wash_cycle wc
        JOIN unit u ON wc.unit_id = u.unit_id
        JOIN station s ON u.station_id = s.station_id
    WHERE
    	wc.wash_cycle_date AT TIME ZONE :timezone >= :dateTimeFrom AT TIME ZONE :timezone
    	AND
    	wc.wash_cycle_date AT TIME ZONE :timezone < :dateTimeTo AT TIME ZONE :timezone
    GROUP BY
        s.station_id,
        s.station_name
)
SELECT
    rbs.station_id AS best_station_id,
    rbs.station_name AS best_station_name,
    rbs.station_total_coin_amount AS best_station_revenue,
    (
    	SELECT SUM(wc.coin_amount)
    	FROM wash_cycle wc
    	WHERE wc.wash_cycle_date AT TIME ZONE :timezone >= :dateTimeFrom AT TIME ZONE :timezone
    	AND wc.wash_cycle_date AT TIME ZONE :timezone < :dateTimeTo AT TIME ZONE :timezone
    ) AS total_coin_amount,
    (
    	SELECT COUNT(wc.wash_cycle_id)
    	FROM wash_cycle wc
    	WHERE wc.wash_cycle_date AT TIME ZONE :timezone >= :dateTimeFrom AT TIME ZONE :timezone
    	AND wc.wash_cycle_date AT TIME ZONE :timezone < :dateTimeTo AT TIME ZONE :timezone
   	) AS total_wash_cycle_count,
    (
    	SELECT SUM(wc.water_consumption)
    	FROM wash_cycle wc
    	WHERE wc.wash_cycle_date AT TIME ZONE :timezone >= :dateTimeFrom AT TIME ZONE :timezone
    	AND wc.wash_cycle_date AT TIME ZONE :timezone < :dateTimeTo AT TIME ZONE :timezone
    ) AS total_water_consumption,
    (
    	SELECT SUM(wc.wax_consumption)
    	FROM wash_cycle wc
    	WHERE wc.wash_cycle_date AT TIME ZONE :timezone >= :dateTimeFrom AT TIME ZONE :timezone
    	AND wc.wash_cycle_date AT TIME ZONE :timezone < :dateTimeTo AT TIME ZONE :timezone
    ) AS total_wax_consumption,
    (
    	SELECT SUM(wc.detergent_consumption)
    	FROM wash_cycle wc
    	WHERE wc.wash_cycle_date AT TIME ZONE :timezone >= :dateTimeFrom AT TIME ZONE :timezone
    	AND wc.wash_cycle_date AT TIME ZONE :timezone < :dateTimeTo AT TIME ZONE :timezone
    ) AS total_detergent_consumption
FROM
    revenue_by_station rbs
WHERE
    rbs.station_total_coin_amount = (SELECT MAX(station_total_coin_amount) FROM revenue_by_station);
