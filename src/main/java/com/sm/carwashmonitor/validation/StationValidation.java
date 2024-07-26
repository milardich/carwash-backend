package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.exception.GenericValidationException;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.model.Unit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import java.util.HashMap;


@Component
public class StationValidation {

    // todo: rename to validateRequest
    public void validate(StationRequestDto stationRequestDto) {
        HashMap<String, String> stationProperties = new HashMap<>();
        stationProperties.put("Station", stationRequestDto.getStationName());
        stationProperties.put("City", stationRequestDto.getCity());
        stationProperties.put("Street", stationRequestDto.getStreetName());
        stationProperties.put("Street number", stationRequestDto.getStreetNumber());
        stationProperties.put("Country", stationRequestDto.getCountry());

        stationProperties.keySet().forEach(key -> {
            if(stationProperties.get(key).isEmpty()) {
                throw new GenericValidationException(key + " property is empty.");
            }
        });
    }

    public void validateStationContainsUnit(Station station, Unit unit) {
        if(!station.getUnits().contains(unit)) {
            throw new EntityNotFoundException("Station does not contain unit");
        }
    }
}
