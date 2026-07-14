package com.fabrica.stock.familia.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FamiliaExceptionHandler {
    @ExceptionHandler(FamiliaNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DomainErrorResponse manejarFamiliaNoEncontrada(FamiliaNoEncontradaException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(NombreFamiliaDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DomainErrorResponse manejarNombreFamiliaDuplicado(NombreFamiliaDuplicadoException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }
}