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

    @ExceptionHandler(LoteMateriaPrimaDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DomainErrorResponse manejarLoteMateriaPrimaDuplicado(LoteMateriaPrimaDuplicadoException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }
}