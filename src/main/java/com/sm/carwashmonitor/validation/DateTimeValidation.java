package com.sm.carwashmonitor.validation;

import com.mysql.cj.util.StringUtils;
import com.sm.carwashmonitor.exception.GenericValidationException;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class DateTimeValidation {

    public void validate(String dateTimeRange) {
        dateTimeRange = dateTimeRange.replace("\"", "");
        List<String> validDateTimeRanges = List.of(
                "1 hour",
                "1 day",
                "7 days",
                "1 month",
                "6 months",
                "1 year"
        );

        if(!validDateTimeRanges.contains(dateTimeRange)) {
            throw new GenericValidationException("Invalid dateTimeRange");
        }
    }
}
