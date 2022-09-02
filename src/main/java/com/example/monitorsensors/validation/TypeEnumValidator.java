package com.example.monitorsensors.validation;

import com.example.monitorsensors.model.enums.Types;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class TypeEnumValidator implements ConstraintValidator<TypeEnumCheck, Types> {

    private Types[] subset;

    @Override
    public void initialize(TypeEnumCheck constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Types value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
