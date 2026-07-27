package com.fabrica.stock.shared.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidarEnum, Enum<?>> {
    private Object[] enumValues;

    @Override
    public void initialize(ValidarEnum annotation) {
        enumValues = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if(value == null) return true; // Deja la validación de nulos a @NotNull

        return Arrays.stream(enumValues)
                .anyMatch(e -> e.toString().equals(value.toString()));
    }
}