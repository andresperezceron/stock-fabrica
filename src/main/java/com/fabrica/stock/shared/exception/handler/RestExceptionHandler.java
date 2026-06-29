package com.fabrica.stock.shared.exception.handler;

import com.fabrica.stock.producto.domain.exceptions.CodigoProductoDuplicadoException;
import com.fabrica.stock.shared.domain.*;
import com.fabrica.stock.shared.exception.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    /* movimiento stockNegativo */
    @ExceptionHandler(MovimientoStockNegativoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarStockNegativo(MovimientoStockNegativoException ex) { return ex.getMessage(); }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }


        return new ValidationErrorResponse(errores);
    }
}

