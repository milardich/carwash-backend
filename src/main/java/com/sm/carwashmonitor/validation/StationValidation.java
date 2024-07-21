package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.exception.GenericValidationException;
import com.sm.carwashmonitor.repository.StationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StationValidation {
    private final StationRepository stationRepository;

    private final List<String> stationProperties = List.of(
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
            throw new EntityNotFoundException("Station does not exist");
        }
    }
}
