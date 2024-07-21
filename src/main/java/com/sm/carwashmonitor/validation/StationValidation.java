package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.exception.GenericValidationException;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.repository.StationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StationValidation {
    private final StationRepository stationRepository;

    private List<String> stationProperties = List.of(
        "Station",
        "City",
        "Street",
        "Street number",
        "Country"
    );

    public void validate(StationRequestDto stationRequestDto) {
        stationProperties.forEach(stationProperty -> {
            if(stationProperty.isEmpty()) {
                throw new GenericValidationException(stationProperty + " property is empty.");
            }
        });
    }

    public void exists(Long id) {
        if(!stationRepository.existsById(id)) {
            throw new EntityNotFoundException("Station with id " + id + " does not exist");
        }
    }

    public void isEmpty(List<Station> stations) {
        if(stations.isEmpty()) {
            throw new EntityNotFoundException("Failed to load stations");
        }
    }
}
