package com.fabrica.stock.ubicacion.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UbicacionExceptionHandler {

    @ExceptionHandler(UbicacionNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DomainErrorResponse manejarUbicacionNoEncontradaException(UbicacionNoEncontradaException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(NombreUbicacionDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DomainErrorResponse manejarNombreUbicacionDuplicado(NombreUbicacionDuplicadoException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(NombreUbicacionSistemaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DomainErrorResponse manejarNombreUbicacionSistema(NombreUbicacionSistemaException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }
}