package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.ResourceUsageChartDataDTO;
import com.sm.carwashmonitor.repository.ResourceRepository;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.repository.WashCycleRepository;
import com.sm.carwashmonitor.service.ResourceService;
import com.sm.carwashmonitor.validation.DateTimeValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final StationRepository stationRepository;
    private final ResourceRepository resourceRepository;
    private final DateTimeValidation dateTimeValidation;

    /*
     *   TODO: REFACTOR
     */
    @Override
    public List<ResourceUsageChartDataDTO> getResourceUsageChartData(Long stationId, String pgTimeInterval) {
        stationRepository.findById(stationId).orElseThrow(
                () -> new EntityNotFoundException("Station not found"));

        dateTimeValidation.validate(pgTimeInterval);

        String pgTimeIntervalSuffix = pgTimeInterval.split("\\s+")[1].replace("\"", ""); // "1 day" -> "day"

        List<ResourceUsageChartDataDTO> usages = resourceRepository.getResourceUsageChartData(stationId, pgTimeInterval);

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
