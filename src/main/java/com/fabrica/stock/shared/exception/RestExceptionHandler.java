package com.fabrica.stock.shared.exception;

import com.fabrica.stock.shared.response.RequestValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RequestValidationErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
        return new RequestValidationErrorResponse(errores);
    }
}
