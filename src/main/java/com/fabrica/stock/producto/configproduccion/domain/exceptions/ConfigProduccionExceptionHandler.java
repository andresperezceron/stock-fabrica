package com.fabrica.stock.producto.configproduccion.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigProduccionExceptionHandler {
    @ExceptionHandler(ConfigProduccionNoEncontradaException.class)
    public ResponseEntity<DomainErrorResponse>
    manejarConfigProduccionNoEncontrada(ConfigProduccionNoEncontradaException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(LoteMateriaPrimaDupicadoException.class)
    public ResponseEntity<DomainErrorResponse> manejarLoteMateriaPrimaDuplicado(LoteMateriaPrimaDupicadoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    public ResponseEntity<DomainErrorResponse> manejarNoAdmiteConfigProduccion(NoAdmiteConfigProduccionException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}