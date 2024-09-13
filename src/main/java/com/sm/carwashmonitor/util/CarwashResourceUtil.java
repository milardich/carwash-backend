package com.sm.carwashmonitor.util;

import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import com.sm.carwashmonitor.dto.StatisticsSummaryDTO;
import org.json.JSONObject;

public class CarwashResourceUtil {

    public static Double getWaterPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return (Double) obj.get("waterCost");
//        return obj.getFloat("waterCost");
    }

    public static Double getWaxPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return (Double) obj.get("waxCost");
    }

    public static Double getDetergentPrice() {
        String prices = FileReaderUtil.readStringFromFile("CarwashResourcePrices.json");
        JSONObject obj = new JSONObject(prices);
        return (Double) obj.get("detergentCost");
    }

    public static void setCosts(StatisticsHighlightsDTO statisticsDTO) {
        // per liter price (eur)
        Double waterPrice = getWaterPrice();
        Double waxPrice = getWaxPrice();
        Double detergentPrice = getDetergentPrice();

        statisticsDTO.setTotalWaxCost(
                statisticsDTO.getTotalWaxConsumption() * waxPrice.floatValue()
        );
        statisticsDTO.setTotalWaterCost(
                statisticsDTO.getTotalWaterConsumption() * waterPrice.floatValue()
        );
        statisticsDTO.setTotalDetergentCost(
                statisticsDTO.getTotalDetergentConsumption() * detergentPrice.floatValue()
        );
    }

    public static void setCosts(StatisticsSummaryDTO statisticsSummary) {
        Double waterPrice = getWaterPrice();
        Double waxPrice = getWaxPrice();
        Double detergentPrice = getDetergentPrice();

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
