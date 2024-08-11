package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.dto.WashCycleRequestDTO;
import com.sm.carwashmonitor.exception.GenericValidationException;
import org.springframework.stereotype.Component;

@Component
public class WashCycleValidation {
    public void validateWashCycleRequest(WashCycleRequestDTO washCycleRequestDto) {
        if (washCycleRequestDto.getWaterConsumption() < 0) {
            throw new GenericValidationException("Invalid water consumption amount");
        }
        if(washCycleRequestDto.getWaxConsumption() < 0) {
            throw new GenericValidationException("Invalid wax consumption amount");
        }
        if(washCycleRequestDto.getDetergentConsumption() < 0) {
            throw new GenericValidationException("Invalid detergent consumption amount");
        }
        if(washCycleRequestDto.getCoinAmount() < 0) {
            throw new GenericValidationException("Invalid coin amount");
        }
    }
}
