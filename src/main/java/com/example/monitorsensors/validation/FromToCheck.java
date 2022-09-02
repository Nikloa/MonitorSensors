package com.example.monitorsensors.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FromToValidator.class})
public @interface FromToCheck {

    String message() default "Value \"To\" must be greater than value \"From\"";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
