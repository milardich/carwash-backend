INSERT INTO
	wash_cycle (
		wash_cycle_id,
		unit_id,
		wash_cycle_date,
		water_consumption,
		detergent_consumption,
		wax_consumption,
		coin_amount
	)
	VALUES (
		:washCycleId,
		:unitId,
		:washCycleDate,
		:waterConsumption,
		:detergentConsumption,
		:waxConsumption,
		:coinAmount
	);
