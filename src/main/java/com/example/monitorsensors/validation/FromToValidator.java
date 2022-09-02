package com.example.monitorsensors.validation;

import com.example.monitorsensors.model.RangeEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FromToValidator implements ConstraintValidator<FromToCheck, RangeEntity> {

    @Override
    public void initialize(FromToCheck constraint) {
    }

    @Override
    public boolean isValid(RangeEntity entity, ConstraintValidatorContext context) {
        return entity.getFrom() < entity.getTo();
    }
}
