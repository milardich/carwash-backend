# CarWash Monitor

---

## About
- REST API for monitoring Car Wash stations

## Setup
- ### Database setup
  - run `docker-compose up` from the root directory of this project
  - run `init_db.cmd` *(windows)* **OR** `init_db.sh` *(unix)* 

---

## Test request
- method: `GET`
- endpoint: `localhost:8080/api/station`
##### Response body:
```json
[
  {
    "stationId": 1,
    "stationName": "ExampleName",
    "city": "Example City",
    "streetName": "Example street name",
    "streetNumber": "15",
    "country": "Croatia",
    "units": []
  }
]
```
##### Response status: `200 OK`

---

- ### Dev setup
  - ### Requirements
    - Java 17
    - Maven
    - PostgreSQL

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



# ***❓ TODO:*** Update station
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
       "coinTrayAmount": 259,
       "totalWaterConsumption": 4483.51,
       "totalDetergentConsumption": 674.77,
       "totalWaxConsumption": 351.12
     },
     {
       "unitId": 4,
       "status": "AVAILABLE",
       "coinTrayAmount": 123,
       "totalWaterConsumption": 2374.51,
       "totalDetergentConsumption": 542.2,
       "totalWaxConsumption": 121.66
     },
     {
       "unitId": 5,
       "status": "INACTIVE",
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



# Get unit's data
###### Fetches unit's data filtered by datetime
- method: `GET`
- endpoint: `/api/station/{stationId}/unit/{unitId}`
- request params:
  - `dateTimeFrom`, example: *2023-06-11T21:00:00.000*, **(NOT REQUIRED)**
  - `dateTimeTo`, example: *2023-06-23T22:30:00.000*, **(NOT REQUIRED)**
##### Request body: `EMPTY`
##### Response body:
```json
{
    "unitId": 5,
    "status": "IN_USE",
    "coinTrayAmount": 259,
    "totalWaterConsumption": 4483.21,
    "totalDetergentConsumption": 674.11,
    "totalWaxConsumption": 351.87
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
  "status": "IN_USE"
}
```
##### Response body:
```json
{
  "unitId": 11,
  "status": "IN_USE",
  "coinTrayAmount": 0,
  "totalWaterConsumption": 421.5,
  "totalDetergentConsumption": 51.21,
  "totalWaxConsumption": 61.2
}
```
##### Response status: `200 OK`

---



# Create new wash cycle
###### Creates a new wash cycle. A wash cycle is completed when a car finishes the entire washing process, tracking the amount of resources used and number of coins used. One unit can have multiple wash cycles.
- method: `POST`
- endpoint: `/api/station/{stationId}/unit/{unitId}/wash-cycle`
##### Request body:
```json
{
  "waterConsumption": 9.66,
  "detergentConsumption": 1.92,
  "waxConsumption": 5.61,
  "coinAmount": 3
}
```
##### Response body:
```json
{
  "washCycleId": 7,
  "stationId": 2,
  "unitId": 9,
  "washCycleDate": "2023-06-11T22:12:35.685576",
  "coinAmount": 7,
  "waterConsumption": 9.66,
  "detergentConsumption": 1.92,
  "waxConsumption": 5.61
}
```
##### Response status: `200 OK`

---



# Get wash cycle
###### Fetch wash cycle by id
- method: `GET`
- endpoint: `/api/station/{stationId}/unit/{unitId}/wash-cycle/{washCycleId}`
##### Request body: `EMPTY`
##### Response body:
```json
{
  "washCycleId": 7,
  "stationId": 2,
  "unitId": 9,
  "washCycleDate": "2023-06-11T22:12:35.685576",
  "coinAmount": 7,
  "waterConsumption": 9.66,
  "detergentConsumption": 1.92,
  "waxConsumption": 5.61
}
```
##### Response status: `200 OK`

---



# Get unit's wash cycles
###### Fetches unit's wash cycles filtered by datetime
- method: `GET`
- endpoint: `/api/station/1/unit/2/wash-cycle?dateTimeFrom={dateTimeFrom}&dateTimeTo={dateTimeTo}`
- request params:
    - `dateTimeFrom`, example: *2022-06-11T21:00:00.000*, **(REQUIRED)**
    - `dateTimeTo`, example: *2023-06-23T22:30:00.000*, **(REQUIRED)**
##### Request body: `EMPTY`
##### Response body:
```json
{
  "washCycles": [
    {
      "washCycleId": 7,
      "stationId": 2,
      "unitId": 9,
      "washCycleDate": "2023-06-11T22:12:35.685576",
      "coinAmount": 7,
      "waterConsumption": 9.66,
      "detergentConsumption": 1.92,
      "waxConsumption": 5.61
    },
    {
      "washCycleId": 8,
      "stationId": 2,
      "unitId": 9,
      "washCycleDate": "2023-06-11T22:12:35.685576",
      "coinAmount": 7,
      "waterConsumption": 9.66,
      "detergentConsumption": 1.92,
      "waxConsumption": 5.61
    }
  ]
}
```
##### Response status: `200 OK`

---



# Get station's resource consumptions
###### Fetches station's resources consumptions filtered by datetime
- method: `GET`
- endpoint: `/api/resources/station/{stationId}/resource-consumption`
- request params:
  - `dateTimeFrom`, example: *2023-06-11T21:00:00.000*, **(NOT REQUIRED)**
  - `dateTimeTo`, example: *2023-06-23T22:30:00.000*, **(NOT REQUIRED)**
##### Request body: `EMPTY`
##### Response body:
```json
{
  "totalWaterConsumption": 69.979996,
  "totalDetergentConsumption": 40.760002,
  "totalWaxConsumption": 77.83,
  "waterConsumptions": [
    {
      "dateTime": "2023-06-11T22:14:35.685",
      "consumption": 7.66
    },
    {
      "dateTime": "2023-06-11T22:12:32.536697",
      "consumption": 6.66
    },
    {
      "dateTime": "2023-06-11T21:52:16.097",
      "consumption": 55.66
    }
  ],
  "detergentConsumptions": [
    {
      "dateTime": "2023-06-11T22:14:35.685",
      "consumption": 17.92
    },
    {
      "dateTime": "2023-06-11T22:12:32.536697",
      "consumption": 16.92
    },
    {
      "dateTime": "2023-06-11T21:52:16.097",
      "consumption": 5.92
    }
  ],
  "waxConsumptions": [
    {
      "dateTime": "2023-06-11T22:14:35.685",
      "consumption": 3.6100001
    },
    {
      "dateTime": "2023-06-11T22:12:32.536697",
      "consumption": 41.61
    },
    {
      "dateTime": "2023-06-11T21:52:16.097",
      "consumption": 32.61
    }
  ]
}
```
##### Response status: `200 OK`

---

##### pg_dump -E UTF8 -U postgres -h localhost carwash_db > dumped_data_carwash.sql