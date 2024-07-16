-- db name: carwash_db

--DROP DATABASE IF EXISTS carwash_db
--DROP TABLE IF EXISTS station;
--DROP TABLE IF EXISTS unit;
--DROP TABLE IF EXISTS wash_cycle;
--DROP SEQUENCE IF EXISTS station_sequence;
--DROP SEQUENCE IF EXISTS unit_sequence;
--DROP SEQUENCE IF EXISTS wash_cycle_sequence;

CREATE TABLE station (
	station_id INTEGER PRIMARY KEY,
	station_name VARCHAR(50),
	city VARCHAR(50),
	street_name VARCHAR(50),
	street_number VARCHAR(5),
	country VARCHAR(50)
);

CREATE TABLE unit (
	unit_id INTEGER PRIMARY KEY,
	station_id INTEGER,
	status VARCHAR(20),
	coin_tray_amount INTEGER,
	total_water_consumption FLOAT,
	total_detergent_consumption FLOAT,
	total_wax_consumption FLOAT
);

CREATE TABLE wash_cycle (
	wash_cycle_id INTEGER PRIMARY KEY,
	unit_id INTEGER,
	wash_cycle_date TIMESTAMP,
	water_consumption FLOAT,
	detergent_consumption FLOAT,
	wax_consumption FLOAT,
	coin_amount FLOAT
);

CREATE SEQUENCE station_sequence INCREMENT 1;
CREATE SEQUENCE unit_sequence INCREMENT 1;
CREATE SEQUENCE wash_cycle_sequence INCREMENT 1;