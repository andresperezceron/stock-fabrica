package com.fabrica.stock.palet.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaletExceptionHandler {
    @ExceptionHandler(PaletNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DomainErrorResponse manejarPaletNoEncontrado(DomainErrorResponse response) {
        return new DomainErrorResponse(response.getMessage());
    }

    @ExceptionHandler(EstadoPaletInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DomainErrorResponse manejarEstadoPaletInvalido(DomainErrorResponse response) {
        return new DomainErrorResponse(response.getMessage());
    }
}
