package com.emapaez.storepay.common.validAnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CuitValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCuit {

    String message() default "CUIT/CUIL invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
