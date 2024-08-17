package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.ResourceChartDataDTO;
import com.sm.carwashmonitor.repository.ChartDataRepository;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.service.ChartDataService;
import com.sm.carwashmonitor.validation.DateTimeValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChartDataServiceImpl implements ChartDataService {

    private final StationRepository stationRepository;
    private final ChartDataRepository chartDataRepository;
    private final DateTimeValidation dateTimeValidation;

    /*
     *   TODO:
     *      - REFACTOR
     *      - validate timezone
     */
    @Override
    public List<ResourceChartDataDTO> getResourceUsageChartData(Long stationId, String pgTimeInterval, String timezone) {
        stationRepository.findById(stationId).orElseThrow(
                () -> new EntityNotFoundException("Station not found"));

        dateTimeValidation.validate(pgTimeInterval);

        String pgTimeIntervalSuffix = pgTimeInterval.split("\\s+")[1].replace("\"", ""); // "1 day" -> "day"

        List<ResourceChartDataDTO> usages = chartDataRepository.getResourceUsageChartData(stationId, pgTimeInterval, timezone);

        usages.forEach(u -> {

            LocalDateTime dateTime = LocalDateTime.parse(u.getWashCycleDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Integer year = dateTime.getYear();
            Integer month = dateTime.getMonthValue();
            Integer day = dateTime.getDayOfMonth();
            Integer hour = dateTime.getHour();
            Integer minute = dateTime.getMinute();

            if(pgTimeIntervalSuffix.equals("hours")) {
                u.setWashCycleDate(hour + ":" + minute + "0");
            } else if (pgTimeIntervalSuffix.equals("days")) {
                u.setWashCycleDate(day + "." + month + ".");
            } else if (pgTimeIntervalSuffix.equals("months")) {
                u.setWashCycleDate(month.toString());
            } else if (pgTimeIntervalSuffix.equals("years")) {
                u.setWashCycleDate(year.toString());
            }

        });

        return usages;
    }
}
