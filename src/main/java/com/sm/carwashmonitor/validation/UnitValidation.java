package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.model.enumeration.UnitStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UnitValidation {


    public void validateUnitStatus(String unitStatus) {
        if(!unitStatusExists(unitStatus)) {
            throw new EntityNotFoundException("Status of type '" + unitStatus + "' does not exist");
        }
    }

    public boolean unitStatusExists(String unitStatus){
        for (UnitStatus status : UnitStatus.values()) {
            if (status.name().equals(unitStatus)) {
                return true;
            }
        }
        return false;
    }
}
