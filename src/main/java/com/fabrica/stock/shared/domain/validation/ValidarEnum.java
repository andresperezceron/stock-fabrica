package com.fabrica.stock.shared.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface ValidarEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Valor no permitido para este enum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}