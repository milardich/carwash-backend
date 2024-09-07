package com.sm.carwashmonitor.util;

import org.json.JSONObject;

public class CarwashResourceUtil {

    public static Float getWaterPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return obj.getFloat("waterCost");
    }

    public static Float getWaxPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return obj.getFloat("waxCost");
    }

    public static Float getDetergentPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return obj.getFloat("detergentCost");
    }
}
