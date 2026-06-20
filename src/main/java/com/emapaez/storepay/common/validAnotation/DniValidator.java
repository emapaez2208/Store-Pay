package com.emapaez.storepay.common.validAnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DniValidator implements ConstraintValidator<ValidDni, String> {

    @Override
    public boolean isValid(String dni, ConstraintValidatorContext context) {

        if (dni == null || dni.isBlank()) {
            return false;
        }
        String clearDni = dni.replace(".", "")
                            .replace(" ", "");

        if (!clearDni.matches("\\d{7,8}")) {
            return false;
        }

        try {
            long numero = Long.parseLong(clearDni);

            return numero >= 1_000_000 && numero <= 99_999_999;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
