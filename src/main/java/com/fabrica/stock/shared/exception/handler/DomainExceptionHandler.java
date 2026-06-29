package com.fabrica.stock.shared.exception.handler;

import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import com.fabrica.stock.ubicacion.domain.exceptions.NombreUbicacionDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DomainExceptionHandler {
    /* Recurso no encontrado */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) { return ex.getMessage(); }

    @ExceptionHandler(NombreUbicacionDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionDuplicado(NombreUbicacionDuplicadoException ex) { return ex.getMessage(); }
}
