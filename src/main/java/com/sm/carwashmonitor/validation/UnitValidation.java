package com.sm.carwashmonitor.validation;

import com.sm.carwashmonitor.exception.GenericValidationException;
import com.sm.carwashmonitor.model.Unit;
import com.sm.carwashmonitor.model.WashCycle;
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

    public void validateUnitCointainsWashCycle(Unit unit, WashCycle washCycle) {
        if(!unit.getWashCycles().contains(washCycle)) {
            throw new EntityNotFoundException("Unit does not contain wash cycle");
        }
    }

    public void checkStatusInUse(Unit unit) {
        if(!unit.getStatus().equals(UnitStatus.IN_USE.name())) {
            throw new GenericValidationException("Unit is not IN_USE");
        }
    }
}
