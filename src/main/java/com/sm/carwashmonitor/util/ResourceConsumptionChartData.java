package com.sm.carwashmonitor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ResourceConsumptionChartData {
    /*
    example: Last 3 days response:

    {
        "data": [
            {
                "label": "01.07.",
                "waterData": "124.54",
                "waxData": "53.32",
                "detergentData": "98.42"
            },
            {
                "label": "02.07.",
                "waterData": "124.54",
                "waxData": "53.32",
                "detergentData": "98.42"
            },
            {
                "label": "03.07.",
                "waterData": "124.54",
                "waxData": "53.32",
                "detergentData": "98.42"
            }
        ]
    }
     */

    // request:
    // /api/station/5/resource-consumption?range=today
    // /api/station/5/resource-consumption?range=last-24-hours
    // /api/station/5/resource-consumption?range=this-week
    // /api/station/5/resource-consumption?range=this-month
    // /api/station/5/resource-consumption?range=this-year

    // service:
    // today - 24 data points: point 0 {00:00:00 - 00:59:59}, point 1 {01:00:00 - 01:59:59} etc. only hours that had passed are filled
    // last-24-hours - 24 data points: -||-, all points filled (currentDateTime - 24 hours)
    // this-week - 7 data points: point 0 {01.07.2024T00:00:00 - 01.07.2024T23:59:59} etc.
    // this-month - daysInMonth data points ...
    // this-year - 12 dataPoints

    // data: sum of resource consumptions between data points



    private ChartDataSetResponseDTO getChartData(/*@PathVariable range*/) {
        /*
        * if range ...
        * then return 7 data points
        *
        * etc
        * */
        return new ChartDataSetResponseDTO();
    }

    protected class ChartData {
        String label; // hour (12:00) / day (01.07) / month (January)
        String waterConsumption;
        String waxConsumption;
        String detergentConsumption;
    }

    protected class ChartDataSetResponseDTO {
        List<ChartData> dataSet;
    }

    private void test() {
        HashMap<String, Float> data = new HashMap<>();
        // water
        data.put("Mon", 5832.43F);
        data.put("Tue", 5832.43F);
        data.put("Wed", 3423.2F);
        data.put("Thu", 5832.43F);
        data.put("Fri", 3423.2F);
        data.put("Sat", 5832.43F);
        data.put("Sun", 3423.2F);

        // wax
        data.put("Mon", 5832.43F);
        data.put("Tue", 5832.43F);
        data.put("Wed", 3423.2F);
        data.put("Thu", 5832.43F);
        data.put("Fri", 3423.2F);
        data.put("Sat", 5832.43F);
        data.put("Sun", 3423.2F);

        // detergent
        data.put("Mon", 5832.43F);
        data.put("Tue", 5832.43F);
        data.put("Wed", 3423.2F);
        data.put("Thu", 5832.43F);
        data.put("Fri", 3423.2F);
        data.put("Sat", 5832.43F);
        data.put("Sun", 3423.2F);

        data.get("Mon"); //5832.34
        List<String> lista = new ArrayList<>();
        data.keySet().forEach(k -> {
            lista.add(k);
        });
    }
}
