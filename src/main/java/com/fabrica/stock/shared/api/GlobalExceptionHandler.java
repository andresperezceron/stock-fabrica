package com.fabrica.stock.shared.api;

import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return ex.getMessage();
    }
}