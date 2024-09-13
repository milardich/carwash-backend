package com.sm.carwashmonitor.util;

import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import com.sm.carwashmonitor.dto.StatisticsSummaryDTO;
import org.json.JSONObject;

public class CarwashResourceUtil {

    public static Float getWaterPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return Double.valueOf(obj.getDouble("waterCost")).floatValue();
    }

    public static Float getWaxPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return Double.valueOf(obj.getDouble("waxCost")).floatValue();
    }

    public static Float getDetergentPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return Double.valueOf(obj.getDouble("detergentCost")).floatValue();
    }

    public static void setCosts(StatisticsHighlightsDTO statisticsDTO) {
        // per liter price (eur)
        Float waterPrice = getWaterPrice();
        Float waxPrice = getWaxPrice();
        Float detergentPrice = getDetergentPrice();

        statisticsDTO.setTotalWaxCost(
                statisticsDTO.getTotalWaxConsumption() * waxPrice
        );
        statisticsDTO.setTotalWaterCost(
                statisticsDTO.getTotalWaterConsumption() * waterPrice
        );
        statisticsDTO.setTotalDetergentCost(
                statisticsDTO.getTotalDetergentConsumption() * detergentPrice
        );
    }

    public static void setCosts(StatisticsSummaryDTO statisticsSummary) {
        Float waterPrice = getWaterPrice();
        Float waxPrice = getWaxPrice();
        Float detergentPrice = getDetergentPrice();

        statisticsSummary.setTotalWaxCost(0.0F);
        statisticsSummary.setTotalWaterCost(0.0F);
        statisticsSummary.setTotalDetergentCost(0.0F);
        statisticsSummary.setTotalRevenue(0.0F);

        statisticsSummary.getAllStationStatistics().forEach(s -> {
            statisticsSummary.setTotalWaterCost(
                    statisticsSummary.getTotalWaterCost() + s.getWaterCost()
            );
            statisticsSummary.setTotalDetergentCost(
                    statisticsSummary.getTotalDetergentCost() + s.getDetergentCost()
            );
            statisticsSummary.setTotalWaxCost(
                    statisticsSummary.getTotalWaxCost() + s.getWaxCost()
            );
            statisticsSummary.setTotalRevenue(
                    statisticsSummary.getTotalRevenue() + s.getRevenue()
            );
        });
    }
}
