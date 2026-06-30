package com.fabrica.stock.lote.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoteExceptionHandler {

    @ExceptionHandler(LoteNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DomainErrorResponse manjarLoteNoEncontrado(DomainErrorResponse response) {
        return new DomainErrorResponse(response.getMessage());
    }
}