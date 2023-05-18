# CarWash Monitor API documentation

---



 # Create new station
 ###### Creates a new car wash station.
- method: `POST`
- endpoint: `/api/station`
##### Request body:
 ```json
 {
   "stationName": "Osijek-sjenjak",
   "city": "Osijek",
   "streetName": "Vukovarska cesta",
   "streetNumber": 54,
   "country": "Croatia"
 }
 ```
 ##### Response body:
```json
 {
   "stationName": "Osijek-sjenjak",
   "city": "Osijek",
   "streetName": "Vukovarska cesta",
   "streetNumber": 54,
   "country": "Croatia",
   "units": []
 }
```
 ##### Response status: `200 OK`

--- 



# Update station
###### Updates single station by id
- method: `UPDATE`
- endpoint: `/api/station/{stationId}`
##### Request body:
```json
{
  "stationName": "Osijek-Edited name",
  "city": "Osijek edited",
  "streetName": "Divaltova",
  "streetNumber": 12,
  "country": "Croatia"
}
```
##### Response body:
```json
{
  "stationId": 5,
  "stationName": "Osijek-Edited name",
  "city": "Osijek edited",
  "streetName": "Divaltova",
  "streetNumber": 12,
  "country": "Croatia",
  "units": []
}
```

---



 # Get station
 ###### Fetch a single station from the database by station id.
- method: `GET`
- endpoint: `/api/station/{stationId}`
 ##### Request body: `EMPTY`
 ##### Response body:
 ```json
 {
   "stationId": 1,
   "stationName": "Osijek-sjenjak",
   "city": "Osijek",
   "streetName": "Vukovarska cesta",
   "streetNumber": 54,
   "country": "Croatia",
   "units": [
     {
       "unitId": 3,
       "status": "IN_USE",
       "numberOfWashes": 46,
       "coinTrayAmount": 259,
       "totalWaterConsumption": 4483.51,
       "totalDetergentConsumption": 674.77,
       "totalWaxConsumption": 351.12
     },
     {
       "unitId": 4,
       "status": "AVAILABLE",
       "numberOfWashes": 23,
       "coinTrayAmount": 123,
       "totalWaterConsumption": 2374.51,
       "totalDetergentConsumption": 542.2,
       "totalWaxConsumption": 121.66
     },
     {
       "unitId": 5,
       "status": "INACTIVE",
       "numberOfWashes": 12,
       "coinTrayAmount": 67,
       "totalWaterConsumption": 443.51,
       "totalDetergentConsumption": 142.77,
       "totalWaxConsumption": 19.89
     }
   ]
 }
 ```
 ##### Response status: `200 OK`

---



# Get all stations
###### Fetch all stations from the database
- method: `GET`
- endpoint: `/api/station`
##### Request body: `EMPTY`
##### Response body:
```json
{
  "stations": [
    {
      "stationId": 1,
      "stationName": "Osijek-sjenjak",
      "city": "Osijek",
      "streetName": "Vukovarska cesta",
      "streetNumber": 54,
      "country": "Croatia",
      "units": [
        {
          "unitId": 3,
          "status": "IN_USE",
          "numberOfWashes": 46,
          "coinTrayAmount": 259,
          "totalWaterConsumption": 4483.53,
          "totalDetergentConsumption": 674.56,
          "totalWaxConsumption": 351.19
        }
      ]
    },
    {
      "stationId": 2,
      "stationName": "Vinkovci-bosut",
      "city": "Vinkovci",
      "streetName": "Vukovarska cesta",
      "streetNumber": 54,
      "country": "Croatia",
      "units": [
        {
          "unitId": 7,
          "status": "AVAILABLE",
          "numberOfWashes": 2,
          "coinTrayAmount": 532,
          "totalWaterConsumption": 5231.55,
          "totalDetergentConsumption": 312.21,
          "totalWaxConsumption": 442.56
        }
      ]
    }
  ]
}
```
##### Response status: `200 OK`

---



# Create new unit
###### Creates a new car washing unit. Multiple units can be in one station. Returns station with newly created unit. Newly created unit is INACTIVE and to be used, must be set to AVAILABLE.
- method: `POST`
- endpoint: `/api/station/{stationId}/unit`
##### Request body: `EMPTY`
##### Response body:
```json
{
   "stationId": 5,
   "stationName": "Osijek-sjenjak",
   "city": "Osijek",
   "streetName": "Vukovarska cesta",
   "streetNumber": 54,
   "country": "Croatia",
   "units": [
     {
       "unitId": 5,
       "status": "IN_USE",
       "numberOfWashes": 46,
       "coinTrayAmount": 259,
       "totalWaterConsumption": 4483.21,
       "totalDetergentConsumption": 674.11,
       "totalWaxConsumption": 351.87
     },
     {
       "unitId": 6,
       "status": "INACTIVE",
       "numberOfWashes": 0,
       "coinTrayAmount": 0,
       "totalWaterConsumption": 0.00,
       "totalDetergentConsumption": 0.00,
       "totalWaxConsumption": 0.00
     }
   ]
 }
```
##### Response status: `200 OK`

---



# Update unit status
###### Updates status of a single unit. Status can be IN_USE, AVAILABLE, INACTIVE
- method: `PATCH`
- endpoint: `/api/station/{stationId}/unit/{unitId}/status`
##### Request body:
```json
{
  "status": "INACTIVE"
}
```
##### Response body:
```json
{
  "unitId": 6,
  "status": "INACTIVE",
  "numberOfWashes": 55,
  "coinTrayAmount": 331,
  "totalWaterConsumption": 6123.51,
  "totalDetergentConsumption": 123.75,
  "totalWaxConsumption": 231.53
}
```
##### Response status: `200 OK`

---



# Create new wash cycle
###### Creates a new wash cycle. A wash cycle is completed when a car finishes the entire washing process, tracking the amount of resources used and the number of coins required. One unit can have multiple wash cycles.
- method: `POST`
- endpoint: `/api/station/{stationId}/unit/{unitId}/wash-cycle`
##### Request body:
```json
{
  "water_consumption": 10,
  "detergent_consumption": 1,
  "wax_consumption": 2,
  "coin_amount": 3
}
```
##### Response body:
```json
{
  "stationId": 5,
  "unitId": 2,
  "wash_cycle_date": "2023-5-18 15:43:51",
  "coin_amount": 3,
  "water_consumption": 10.56,
  "detergent_consumption": 1.14,
  "wax_consumption": 2.41
}
```
##### Response status: `200 OK`

---


