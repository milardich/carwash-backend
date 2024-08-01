package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.exception.GenericValidationException;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class DateTimeValidation {

    public void validate(String pgTimeInterval) {
        pgTimeInterval = pgTimeInterval.replace("\"", "");
        String numberPrefix = pgTimeInterval.split("\\s+")[0].replace("\"", "");

        try {
            Integer.parseInt(numberPrefix);
        }
        catch (NumberFormatException e) {
            throw new GenericValidationException("Invalid number prefix: " + e.getMessage() + " (should be '1 day', '2 months', etc.)");
        }

        List<String> validPgTimeIntervals = List.of(
                numberPrefix + " hour",
                numberPrefix + " day",
                numberPrefix + " days",
                numberPrefix + " month",
                numberPrefix + " months",
                numberPrefix + " year",
                numberPrefix + " years"
        );

        if(!validPgTimeIntervals.contains(pgTimeInterval)) {
            throw new GenericValidationException("Invalid pgTimeInterval");
        }
    }
}
