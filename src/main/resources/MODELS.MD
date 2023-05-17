# Models / entities

---

### STATION
- station_id
- number_of_washes
- station_name (Osijek-1)
- city
- street
- number
- country

- ###### List of units
> One station has many washing units. 

---

### UNIT
- unit_id
- station_id
- status (in_use, available, inactive (broken))
- number_of_washes
- coin_tray_amount
- total_water_consumption
- total_detergent_consumption
- total_wax_consumption
> Unit is where a car goes into and gets washed. Multiple units can be in one station.

---

### WASH_CYCLE
- unit_id
- date
- water_consumption
- detergent_consumption
- wax_consumption
- coin_amount
> Wash cycle refers to the process of washing a single vehicle from start to finish.  *This is the data that would be read by sensors and used in statistics.*

---

### RESOURCES
- resource_id
- resource_name (water, wax, detergent)
- resource_amount (50657 liters)
> Resources available to the station (water, wax, detergent)


---
