package com.fabrica.stock.shared.exception.handler;

import com.fabrica.stock.ubicacion.domain.exceptions.UbicacionNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(UbicacionNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarRecursoNoEncontrado(UbicacionNoEncontradaException ex) { return ex.getMessage(); }
}
