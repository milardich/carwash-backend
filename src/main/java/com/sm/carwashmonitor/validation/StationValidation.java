package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.exception.GenericValidationException;
import com.sm.carwashmonitor.repository.StationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.HashMap;


@Component
@RequiredArgsConstructor
public class StationValidation {
    private final StationRepository stationRepository;

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

    public void exists(Long id) {
        if(!stationRepository.existsById(id)) {
            throw new EntityNotFoundException("Station does not exist");
        }
    }
}
