package com.example.monitorsensors.validation;

import com.example.monitorsensors.model.enums.Units;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class UnitEnumValidator implements ConstraintValidator<UnitEnumCheck, Units> {

    private Units[] subset;

    @Override
    public void initialize(UnitEnumCheck constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Units value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
