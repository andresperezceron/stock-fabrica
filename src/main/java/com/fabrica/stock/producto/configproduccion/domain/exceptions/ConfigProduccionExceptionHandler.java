package com.fabrica.stock.producto.configproduccion.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigProduccionExceptionHandler {
    @ExceptionHandler(ConfigProduccionDuplicadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DomainErrorResponse manejarConfigProduccionDuplicada(ConfigProduccionDuplicadaException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(ConfigProduccionNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DomainErrorResponse manejarConfigProduccionNoEncontrada(ConfigProduccionNoEncontradaException exception) {
        return new DomainErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(LoteMateriaPrimaDupicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DomainErrorResponse manejarLoteMateriaPrimaDuplicado(LoteMateriaPrimaDupicadoException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }
}