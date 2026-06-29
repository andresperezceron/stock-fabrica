package com.fabrica.stock.ubicacion.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UbicacionExceptionHandler {

    @ExceptionHandler(UbicacionNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarUbicacionNoEncontradaException(UbicacionNoEncontradaException ex) { return ex.getMessage(); }

    @ExceptionHandler(NombreUbicacionDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String manejarNombreUbicacionDuplicado(NombreUbicacionDuplicadoException ex) { return ex.getMessage(); }
}