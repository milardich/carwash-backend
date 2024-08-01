package com.sm.carwashmonitor.validation;

import com.mysql.cj.util.StringUtils;
import com.sm.carwashmonitor.exception.GenericValidationException;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class DateTimeValidation {

    public void validate(String dateTimeRange) {
        dateTimeRange = dateTimeRange.replace("\"", "");
        String numberPrefix = dateTimeRange.split("\\s+")[0].replace("\"", "");

        try {
            Integer.parseInt(numberPrefix);
        }
        catch (NumberFormatException e) {
            throw new GenericValidationException("Invalid number prefix: " + e.getMessage() + " (should be '1 day', '2 months', etc.)");
        }


        List<String> validDateTimeRanges = List.of(
                numberPrefix + " hour",
                numberPrefix + " day",
                numberPrefix + " days",
                numberPrefix + " month",
                numberPrefix + " months",
                numberPrefix + " year",
                numberPrefix + " years"
        );

        if(!validDateTimeRanges.contains(dateTimeRange)) {
            throw new GenericValidationException("Invalid dateTimeRange");
        }
    }
}
