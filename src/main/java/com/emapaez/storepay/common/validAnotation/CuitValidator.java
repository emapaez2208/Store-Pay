package com.emapaez.storepay.common.validAnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CuitValidator implements ConstraintValidator<ValidCuit, String> {

    private static final int[] WEIGHT = {
            5, 4, 3, 2,
            7, 6, 5, 4, 3, 2
    };

    @Override
    public boolean isValid(String cuit, ConstraintValidatorContext context) {

        if (cuit == null || cuit.isBlank()) {
            return false;
        }

        String number = cuit.replaceAll("[^0-9]", "");

        if (!number.matches("\\d{11}")) {
            return false;
        }

        int suma = 0;

        for (int i = 0; i < 10; i++) {
            suma += Character.getNumericValue(number.charAt(i))
                    * WEIGHT[i];
        }

        int resto = suma % 11;
        int digitoCalculado = 11 - resto;

        if (digitoCalculado == 11) {
            digitoCalculado = 0;
        } else if (digitoCalculado == 10) {
            digitoCalculado = 9;
        }

        int digitReal = Character.getNumericValue(number.charAt(10));

        return digitoCalculado == digitReal;
    }
}
