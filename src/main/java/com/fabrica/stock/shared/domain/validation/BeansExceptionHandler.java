package com.fabrica.stock.shared.domain.validation;

import com.fabrica.stock.shared.domain.BeansErrorResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class BeansExceptionHandler {
    /* @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BeansErrorResponse> manejarMessageNotReadableYJsonParse(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if(cause instanceof InvalidFormatException invalid && invalid.getTargetType().isEnum()) {
            return ResponseEntity.badRequest().body(new BeansErrorResponse(
                    "error: Valor para el Enum no valido.",
                    "campo: " +  invalid.getPath().get(0).getFieldName(),
                    "valores permitidos: " + Arrays.toString(invalid.getTargetType().getEnumConstants())
            ));
        }
        if(cause instanceof JsonParseException jsonParse) {
            return ResponseEntity.badRequest().body(new BeansErrorResponse(
                    "error:  Tipo de datos no valido.",
                    "campo" + jsonParse.getLocalizedMessage(),
                    "info: " + jsonParse.getOriginalMessage()));
        }

        return ResponseEntity.badRequest().body(new BeansErrorResponse(
                "cause: Excepción no capturada.",
                "campo: " +  cause.getLocalizedMessage(),
                "info: " + cause.getMessage()));
    }*/

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<BeansErrorResponse> manejarInvalidFormat(InvalidFormatException ex) {
        if(ex.getTargetType().isEnum()) {
            return ResponseEntity.badRequest().body(new BeansErrorResponse(
                    "error: Valor para el Enum: " + ex.getValue(),
                    "campo: " +  ex.getPath().get(0).getFieldName(),
                    "valores permitidos: " + Arrays.toString(ex.getTargetType().getEnumConstants())
            ));
        } else return ResponseEntity.badRequest().body(new BeansErrorResponse(
                "cause: Excepción no capturada.",
                "campo: " +  ex.getCause().getLocalizedMessage(),
                "info: " + ex.getCause().getMessage()));
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<BeansErrorResponse> manejarJsonParse(JsonParseException ex) {
        return ResponseEntity.badRequest().body(new BeansErrorResponse(
                "error:  Tipo de datos no valido.",
                "campo" + ex.getLocalizedMessage(),
                "info: " + ex.getOriginalMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BeansErrorResponse> manejarErrorBeans(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        if(error == null) {
            return ResponseEntity.badRequest().body(new BeansErrorResponse(
                    ex.getMessage(),
                    ex.getLocalizedMessage(),
                    ex.getObjectName()));
        }

        return ResponseEntity.badRequest().body(
                new BeansErrorResponse(
                        "Beans Error en: " + ex.getObjectName(),
                        "campo: " + error.getField(),
                        "info: " + error.getDefaultMessage()
                ));
    }
}