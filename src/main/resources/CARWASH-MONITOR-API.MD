# CarWash Monitor API
---


> # Create new station
> ###### Creates a new car wash station.
> - method: `POST`
> - endpoint: `api/station`
> ##### Request body: 
> ```json
> {
>   "stationName": "Osijek-sjenjak",
>   "city": "Osijek",
>   "streetName": "Vukovarska cesta",
>   "streetNumber": 54,
>   "country": "Croatia"
> }
> ```
> ##### Response body: 
> ```json
> {
>   "stationName": "Osijek-sjenjak",
>   "city": "Osijek",
>   "streetName": "Vukovarska cesta",
>   "streetNumber": 54,
>   "country": "Croatia"
> }
> ```
> ##### Response status: `200 OK`
--- 




> # Get station
> ###### Fetches a single station from the database by station id.
> - method: `GET`
> - endpoint: `api/station/{stationId}`
> ##### Request body: `EMPTY`
> ##### Response body: 
> ```json
> {
>   "stationName": "Osijek-sjenjak",
>   "city": "Osijek",
>   "streetName": "Vukovarska cesta",
>   "streetNumber": 54,
>   "country": "Croatia",
>   "units": [
>     {
>       "unitId": 3,
>       "status": "IN_USE",
>       "numberOfWashes": 46,
>       "coinTrayAmount": 259,
>       "totalWaterConsumption": 4483,
>       "totalDetergentConsumption": 674,
>       "totalWaxConsumption": 351
>     },
>     {
>       "unitId": 4,
>       "status": "AVAILABLE",
>       "numberOfWashes": 23,
>       "coinTrayAmount": 123,
>       "totalWaterConsumption": 2374,
>       "totalDetergentConsumption": 542,
>       "totalWaxConsumption": 121
>     },
>     {
>       "unitId": 5,
>       "status": "INACTIVE",
>       "numberOfWashes": 12,
>       "coinTrayAmount": 67,
>       "totalWaterConsumption": 443,
>       "totalDetergentConsumption": 142,
>       "totalWaxConsumption": 19
>     }
>   ]
> }
> ```
> ##### Response status: `200 OK`
---



> # Get all stations
> ###### Fetches all stations from the database
> - method: `GET`
> - endpoint: `api/station`
> ##### Request body: `EMPTY`
> ##### Response body:
> ```json
> {
>   "stations":[
>     {
>       "stationName": "Osijek-sjenjak",
>       "city": "Osijek",
>       "streetName": "Vukovarska cesta",
>       "streetNumber": 54,
>       "country": "Croatia",
>       "units": [
>         {
>           "unitId": 3,
>           "status": "IN_USE",
>           "numberOfWashes" :46,
>           "coinTrayAmount" :259,
>           "totalWaterConsumption": 4483,
>           "totalDetergentConsumption": 674,
>           "totalWaxConsumption": 351
>         }
>       ]
>     },
>     {
>       "stationName": "Vinkovci-bosut",
>       "city": "Vinkovci",
>       "streetName": "Vukovarska cesta",
>       "streetNumber": 54,
>       "country": "Croatia",
>       "units":[
>         {
>           "unitId": 7,
>           "status": "AVAILABLE",
>           "numberOfWashes": 2,
>           "coinTrayAmount": 532,
>           "totalWaterConsumption": 5231,
>           "totalDetergentConsumption": 312,
>           "totalWaxConsumption": 442
>         }
>       ]
>     }
>   ]
> }
> ```
> ##### Response status: `200 OK`